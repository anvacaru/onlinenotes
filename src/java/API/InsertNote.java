package API;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.* ;  // for standard JDBC programs
import java.security.SecureRandom;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import javax.sql.DataSource;
import javax.annotation.Resource;
import org.json.JSONObject;

/**
 *
 * @author Andrei
 */
@WebServlet(urlPatterns = {"/insert"})
public class InsertNote extends HttpServlet {

    @Resource(name ="jdbc/notes")
    private DataSource dbRes;
        
    public static String randomString( int length) {
        Random random = new SecureRandom();
        char[] characterSet="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
        char[] result = new char[length];
        for (int i = 0; i < result.length; i++) {
            // picks a random index out of character set > random character
            int randomCharIndex = random.nextInt(characterSet.length);
            result[i] = characterSet[randomCharIndex];
        }
        return new String(result);
    }
 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        String content = request.getParameter("textarea_content");
        String epwd = request.getParameter("epwd");
        String apwd = request.getParameter("apwd");
        String url;
        Map<String,String> responseMap = new LinkedHashMap<>();
         ResultSet rs = null;
        if(epwd != ""){
            responseMap.put("isEditable","true");
        }
        else{
            responseMap.put("isEditable","false");
        }
        if(apwd != ""){
            responseMap.put("isPrivate","true");
        }
        else{
            responseMap.put("isPrivate","false");
        }
        
        try ( Connection conn = dbRes.getConnection();
              PreparedStatement ps = conn.prepareStatement("INSERT INTO notes (content,aPwd,ePwd,url) VALUE(?,?,?,?)");
                ) {
            ps.setString(1, content);
            ps.setString(2, apwd);
            ps.setString(3, epwd);
            //Check if url key is already generated
            
            try(PreparedStatement aux = conn.prepareStatement("SELECT * FROM notes WHERE url = ?");){
                url = randomString(8);
                aux.setString(1, url);

                rs = aux.executeQuery();
                    while(rs.next()){
                        url = randomString(8);
                        aux.setString(1, url);
                        rs = aux.executeQuery();
                    }
                
                aux.close();

                ps.setString(4, url);
                responseMap.put("url", url);

                int n = ps.executeUpdate();

                responseMap.put("mode","view");
                 
            }
           
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally{
            try {
                if(rs != null){
                    rs.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
       
        
        JSONObject json = new JSONObject(responseMap);
        response.getWriter().write(json.toString());
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendError(405,"Invalid Method");
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

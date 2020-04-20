package API;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import org.json.JSONObject;

/**
 *
 * @author Andrei
 */
@WebServlet(urlPatterns = {"/update"})
public class UpdateNote extends HttpServlet {
    @Resource(name ="jdbc/notes")
    private DataSource dbRes;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String content = request.getParameter("textarea_content");
        String apwd = request.getParameter("apwd");
        String epwd = request.getParameter("epwd");
        String url = request.getParameter("url");
        
        Map<String,String> responseMap = new LinkedHashMap<>();
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
        
        try (Connection conn = dbRes.getConnection();
             PreparedStatement ps = conn.prepareStatement("UPDATE notes SET content= ?, aPwd= ?, ePwd= ? WHERE url=?");
                ) {
             ps.setString(1, content);
             ps.setString(2, apwd);
             ps.setString(3, epwd);
             ps.setString(4, url);
             
             int n = ps.executeUpdate();
             
            responseMap.put("mode","view");
            
        } catch (SQLException ex) {
           ex.printStackTrace();
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

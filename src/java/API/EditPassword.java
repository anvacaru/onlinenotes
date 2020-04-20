package API;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
@WebServlet(urlPatterns = {"/EditPassword"})
public class EditPassword extends HttpServlet {
    @Resource(name ="jdbc/notes")
    private DataSource dbRes;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String url = request.getParameter("url");
        String epwd = request.getParameter("epwd");
        Map<String,String> responseMap = new LinkedHashMap<>();

        responseMap.put("url",url);
        responseMap.put("status","error");
        responseMap.put("description", "Invalid Access Password");
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(404);
        
        try (Connection conn = dbRes.getConnection();
             PreparedStatement ps = conn.prepareStatement(" SELECT * FROM notes WHERE url = ? and ePwd =?");
                ) {
            ps.setString(1, url);
            ps.setString(2,epwd);
            try(ResultSet rs = ps.executeQuery();){
                while(rs.next()){
                    response.setStatus(200);
                    responseMap.replace("status", "error", "ok");
                    responseMap.remove("description");
                    responseMap.put("content",rs.getString("content"));
                    responseMap.put("mode","edit");
                }
         
            }    
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        JSONObject responseJson = new JSONObject(responseMap);
        response.getWriter().write(responseJson.toString());
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

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
import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 *
 * @author Andrei
 */
@WebServlet(urlPatterns = {"/view"})
public class GetNote extends HttpServlet {
    @Resource(name ="jdbc/notes")
    private DataSource dbRes;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setStatus(404);
        RequestDispatcher view;
        String url = request.getParameter("v");
        HttpSession session =request.getSession(true);
        try (Connection conn = dbRes.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM notes WHERE url = ?");
                ) {
            ps.setString(1,url);
            try(ResultSet rs = ps.executeQuery();){
                while(rs.next()){
                    response.setStatus(200);
                    if(rs.getString("aPwd")==""){//If the note is public
                        session.setAttribute("isPrivate", "false");
                        session.setAttribute("content",rs.getString("content"));
                        session.setAttribute("isLogged","true");
                    }
                    else{//If the note is private
                        session.setAttribute("isPrivate", "true");
                        session.setAttribute("isLogged","false");
                        
                    }
                    if(rs.getString("ePwd")==""){//The note can't be edited
                        session.setAttribute("isEditable","false");
                    }
                    else{//The note can be edited
                        session.setAttribute("isEditable","true");
                    }

                }
            
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        if(response.getStatus()==200){
            view = request.getRequestDispatcher("index.jsp");
            view.forward(request,response);
        }
         else{
            response.sendError(404,"Invalid Note ID");
        }
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
        processRequest(request, response);
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
        response.sendError(405,"Invalid Method");

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

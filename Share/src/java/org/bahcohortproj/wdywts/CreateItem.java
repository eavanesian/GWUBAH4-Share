/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bahcohortproj.wdywts;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author jay
 */
@WebServlet(name = "CreateItem", urlPatterns = {"/lend"})
public class CreateItem extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CreateItem</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreateItem at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        
        String _itemName, _itemDescription, _itemCreator;
        _itemName = request.getParameter("itemName");
        _itemDescription = request.getParameter("itemDescription");
        _itemCreator = request.getParameter("user");
        
        ItemDetail _item = new ItemDetail();
        _item.setItemName(_itemName);
        _item.setItemDescription(_itemDescription);
        _item.setCategoryId(1);
        _item.setUserName(_itemCreator);
        
        
        CreateItemService itemService = new CreateItemService();
        boolean itemCreated = itemService.createItem(_item);
        
        if (itemCreated){
            //item was created, auto dispatch to landing page
            //RequestDispatcher dispatcher = request.getRequestDispatcher("success"); //Need to add landing page
            //dispatcher.forward(request, response);
            response.sendRedirect("./itemList.jsp"); //remove this once landing page built.
            
        } else {
            response.sendRedirect("");
            return;
        }
        
        
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

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
@WebServlet(name = "Edit", urlPatterns = {"/editItem"})
public class EditItemsServlet extends HttpServlet {

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
        //processRequest(request, response);
        try {
            if(request.getParameter("itemId") != null){
                int _itemId = Integer.parseInt(request.getParameter("itemId"));
                String _action = request.getParameter("action");
                EditItemsService edit = new EditItemsService();
                
                if("delete".equals(_action)){
                    if(edit.deleteItem(_itemId)){
                        response.sendRedirect("./deleteSuccess.jsp");
                    }
                }
                
                if("edit".equals(_action)){
                    response.sendRedirect("./editItem.jsp?itemId="+_itemId);
                }
               
            }
            
        } catch (Exception e){
 
        }
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
       // processRequest(request, response);
         try {
                String _action = request.getParameter("action");
                int _itemId = Integer.parseInt(request.getParameter("itemId"));
                String _itemName = request.getParameter("itemName");
                String _itemDescription = request.getParameter("itemDescription");
                String _available = request.getParameter("available");
                ItemDetail _item = new ItemDetail();
                EditItemsService edit = new EditItemsService();
                
                _item.setItemName(_itemName);
                _item.setItemDescription(_itemDescription);
                _item.setItemId(_itemId);
                
                if("on".equals(_available)){
                    _item.setAvailable(true);
                } else if(_available == null){
                    _item.setAvailable(false);
                }
                                
                if(edit.editItem(_item)){
                    response.sendRedirect("./editSuccess.jsp");
                }
            
        } catch (Exception e){
 
        }
        
    }// end
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

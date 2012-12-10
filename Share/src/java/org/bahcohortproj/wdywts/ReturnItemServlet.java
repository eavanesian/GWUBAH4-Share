/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bahcohortproj.wdywts;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author matt
 */
@WebServlet(name = "ReturnItemServlet", urlPatterns = {"/return"})
public class ReturnItemServlet extends HttpServlet {

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
            out.println("<title>Servlet ReturnItemServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ReturnItemServlet at " + request.getContextPath() + "</h1>");
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
        try {
                if("true".equals(request.getParameter("doFeedback"))){
                    UserItems _transaction = new UserItems();
                    int _transactionId; //ID of the transaction from UserItems table

                    _transactionId = Integer.parseInt(request.getParameter("userItem"));

                    String _lenderComments = request.getParameter("lenderComments");
                    int _lenderRating = Integer.parseInt(request.getParameter("lenderRatingOfBorrower"));

                    ReturnItemService edit = new ReturnItemService();

                    _transaction.setLenderComments(_lenderComments);
                    _transaction.setLenderRatingOfBorrower(_lenderRating);
                    _transaction.setUserItemsId(_transactionId);

                    if(edit.leaveFeedback(_transaction)){
                        response.sendRedirect("./feedbackSuccess.jsp");
                    }
                }
                if("true".equals(request.getParameter("returnItem"))){
                    UserItems _transaction = new UserItems();
                    int _transactionId; //ID of the transaction from UserItems table

                    _transactionId = Integer.parseInt(request.getParameter("userItem"));

                    String _borrowerComments = request.getParameter("borrowerComments");
                    int _borrowerRating = Integer.parseInt(request.getParameter("borrowerRatingOfLender"));

                    ReturnItemService edit = new ReturnItemService();

                    _transaction.setBorrowerComments(_borrowerComments);
                    _transaction.setBorrowerRatingOfLender(_borrowerRating);
                    _transaction.setUserItemsId(_transactionId);

                    if(edit.returnItem(_transaction)){
                        response.sendRedirect("./returnSuccess.jsp");
                    }
                }
            
        } catch (Exception e){
 
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

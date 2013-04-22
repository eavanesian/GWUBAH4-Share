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
                    Transaction _transaction = new Transaction();
                    int _transactionID; //ID of the transaction from UserItems table

                    _transactionID = Integer.parseInt(request.getParameter("transactionID"));
                    _transaction.setTransactionID(_transactionID);

                    int _transactionSetID; //ID of the transaction from UserItems table
                    _transactionSetID = Integer.parseInt(request.getParameter("transactionSetID"));
                    
                    String _lenderComments = request.getParameter("lenderComments");
                    int _lenderRating = Integer.parseInt(request.getParameter("lenderRatingOfBorrower"));

                    ReturnItemService edit = new ReturnItemService();
                    
                    int _giverID = Integer.parseInt(request.getParameter("giverID"));
                    int _receiverID = Integer.parseInt(request.getParameter("receiverID"));

                    
                    Feedback _feedback = new Feedback();
                    _feedback.setComments(_lenderComments);
                    _feedback.setRating(_lenderRating);
                    _feedback.setTransactionSetID(_transactionSetID);
                    _feedback.setGiverID(_giverID);
                    _feedback.setReceiverID(_receiverID);
                    _feedback.setTransactionTypeID(4);

                    //_transaction.setLenderComments(_lenderComments);
                    //_transaction.setLenderRatingOfBorrower(_lenderRating);
                    //_transaction.setUserItemsID(_transactionID);

                    if(edit.acknowledgeReturn(_transaction) && edit.leaveFeedback(_feedback)){
                        response.sendRedirect("./feedbackSuccess.jsp");
                    }
                }
                if("true".equals(request.getParameter("returnItem"))){
                    
                    int _transactionID; //ID of the transaction from UserItems table
                    _transactionID = Integer.parseInt(request.getParameter("transactionID"));
                    
                    int _transactionSetID; //ID of the transaction from UserItems table
                    _transactionSetID = Integer.parseInt(request.getParameter("transactionSetID"));
                    
                    Transaction _transaction = new Transaction();
                    _transaction.setTransactionID(_transactionID);
                    
                    int _giverID = Integer.parseInt(request.getParameter("giverID"));
                    int _receiverID = Integer.parseInt(request.getParameter("receiverID"));

                    String _borrowerComments = request.getParameter("borrowerComments");
                    int _borrowerRating = Integer.parseInt(request.getParameter("borrowerRatingOfLender"));

                    Feedback _feedback = new Feedback();
                    _feedback.setComments(_borrowerComments);
                    _feedback.setRating(_borrowerRating);
                    _feedback.setTransactionSetID(_transactionSetID);
                    _feedback.setGiverID(_giverID);
                    _feedback.setReceiverID(_receiverID);
                    _feedback.setTransactionTypeID(3);
                    
                    //_transaction.setBorrowerComments(_borrowerComments);
                    //_transaction.setBorrowerRatingOfLender(_borrowerRating);
                    //_transaction.setUserItemsID(_transactionID);

                    ReturnItemService edit = new ReturnItemService();

                    if(edit.returnItem(_transaction) && edit.leaveFeedback(_feedback)){
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

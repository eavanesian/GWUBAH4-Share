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
public class LendServlet extends HttpServlet {

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
        //do Get code here, to secure the request, then sent to processRequest,
        // TODO: secure input to prevent URL spoofing.
        if("approve".equals(request.getParameter("action"))){
            LendService lend = new LendService();
            
            int _transactionId;
            _transactionId = Integer.parseInt(request.getParameter("transaction"));
            if(lend.approveRequest(_transactionId)){
                //send to confirmation page?
                response.sendRedirect("./reviewRequests.jsp");
                return;
            } else {response.sendRedirect("./reviewRequests.jsp");}
        }
        else if("decline".equals(request.getParameter("action"))){
            LendService lend = new LendService();
            
            int _transactionId;
            _transactionId = Integer.parseInt(request.getParameter("transaction"));
            if(lend.declineRequest(_transactionId)){
                //send to confirmation page?
                response.sendRedirect("./reviewRequests.jsp");
                return;
            } else {response.sendRedirect("./reviewRequests.jsp");}
        }
        
        else {
            response.sendRedirect("./reviewRequests.jsp");
        }
        //processRequest(request, response);
        
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
        LendService lendService = new LendService();
        
        String _itemName, _itemDescription, newCategoryName, newSubCategoryName;
        int _itemCategory, _itemSubCategory, _itemCreator;
        switch(Integer.parseInt(request.getParameter("lendFunction"))){
            case(1):{            
                ItemDetail _item = new ItemDetail();
                boolean validItemEntry = lendService.validEntry(request.getParameter("itemName"));
                boolean validCategoryEntry = lendService.validEntry(request.getParameter("itemCategory"));
        
               if(validItemEntry){
                _itemName = request.getParameter("itemName");
                _item.setItemName(_itemName);
               }
               else {
                   response.sendRedirect("./lend.jsp");
                   return;
               }

               if(validCategoryEntry){
                   try {
                       _itemCategory = Integer.parseInt(request.getParameter("itemCategory"));
                   }
                   catch (NumberFormatException ex){
                       response.sendRedirect("./lend.jsp");
                       return;
                   }
               }
               else {
                   response.sendRedirect("./lend.jsp");
                   return;
               }

                _itemDescription = request.getParameter("itemDescription");
                _itemCreator = Integer.parseInt(request.getParameter("userId"));
                _item.setItemDescription(_itemDescription);
                _item.setUserId(_itemCreator);
                _item.setCategoryId(_itemCategory);
                _item.setAvailable(true);

                boolean itemCreated = lendService.createItem(_item);

                if (itemCreated){
                    //item was created, redirect to landing page
                    response.sendRedirect("./itemList.jsp");
                    return;
                } else {
                    response.sendRedirect("./lend.jsp");
                }
               } // end case newItem
            case(2):{ // new Category functionality
                Category _category = new Category();
                boolean validCategoryEntry = lendService.validEntry(request.getParameter("categoryName"));
                
                if(validCategoryEntry){
                    newCategoryName = request.getParameter("categoryName");
                    _category.setName(newCategoryName);
                    
                    boolean categoryCreated = lendService.createCategory(_category);
                    
                    if (categoryCreated){
                        //category was created, redirect to lend page
                        response.sendRedirect("./lend.jsp");
                        return;
                    } else {
                            response.sendRedirect("./lend.jsp");
                    }
                } else {
                    response.sendRedirect("./lend.jsp");
                    return;
                }
            }
                
            case(3):{ // new Sub Category functionality
                Category _subCategory = new Category();
                boolean validCategoryEntry = lendService.validEntry(request.getParameter("categoryList"));
                boolean validSubCategoryEntry = lendService.validEntry(request.getParameter("subCategoryName"));
                
                if(validCategoryEntry){
                    try {
                       _itemSubCategory = Integer.parseInt(request.getParameter("categoryList"));
                   }
                   catch (NumberFormatException ex){
                       response.sendRedirect("./lend.jsp");
                       return;
                   }
                
                    if(validSubCategoryEntry){
                        newSubCategoryName = request.getParameter("subCategoryName");
                        _subCategory.setName(newSubCategoryName);
                        _subCategory.setParentCategoryId(_itemSubCategory);                        
                        
                        boolean subCategoryCreated = lendService.createCategory(_subCategory);

                        if (subCategoryCreated){
                            //subcategory was created, redirect to lend page
                            response.sendRedirect("./lend.jsp");
                            return;
                        } else {
                                response.sendRedirect("./lend.jsp");
                        }
                    } else {
                        response.sendRedirect("./lend.jsp");
                        return;
                    }
                }
                else {
                    response.sendRedirect("./lend.jsp");
                }
            }// end case 3
     } // end switch
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

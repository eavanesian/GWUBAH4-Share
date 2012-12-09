<%-- 
    Document   : reviewRequests
    Created on : Dec 3, 2012, 12:09:10 AM
    Author     : jay
--%>

<%@page import="org.bahcohortproj.wdywts.ItemDetail"%>
<%@page import="java.util.List"%>
<%@page import="org.hibernate.Query"%>
<%@page import="org.bahcohortproj.wdywts.UserItems"%>
<%@page import="org.hibernate.Session"%>
<%@page import="org.hibernate.SessionFactory"%>
<%@page import="org.bahcohortproj.wdywts.HibernateUtil"%>
<%@page import="org.bahcohortproj.wdywts.UserDetail"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
UserDetail loggedInUser = (UserDetail) session.getAttribute("sUsrName");

if ((loggedInUser == null) || (loggedInUser.getUserId() == 0)) {
    session.removeAttribute("sUsrName");
    response.sendRedirect("./");
    return;
} %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>What do you want to share?</title>
    </head>
    <body>
        <jsp:include page="includes.jsp"></jsp:include>
        
        <div class="mainContainer">
            <jsp:include page="topNav.jsp"></jsp:include>
            <div class="mainContentContainer">    
                <div class="leftMenuContainer">
        [<a href="./lend.jsp">Create Listing</a>] <BR><BR>
        [<a href="./itemList.jsp">View/Edit Available Items</a>] <BR><BR> 
        [<a href="./reviewRequests.jsp">Review Requests to Borrow</a>]<BR><BR>
            </div>
            <div class="content">
                <div class="itemForm"><div class="itemBox"><B>Current Requests to Borrow:</B><BR>
                        <% //Read items from the db and list
                        UserItems transaction = new UserItems();
   
                        SessionFactory sf = new HibernateUtil().getSessionFactory();
                        Session hSession = sf.openSession();
                        hSession.beginTransaction();
                        
                        UserItems userItems = new UserItems();
                        ItemDetail _item = new ItemDetail();
                        UserDetail _borrower = new UserDetail();
                        
                        String hql = "FROM UserItems WHERE lenderID = :userId AND status = 0";
                        Query query = hSession.createQuery(hql);
                        query.setParameter("userId", loggedInUser.getUserId());
                        List<UserItems> requestedItems = (List<UserItems>) query.list();
                        //out.println("<div align='center'>");
                        for (UserItems u : requestedItems) {
                            
                            _item = (ItemDetail) hSession.get(ItemDetail.class, u.getItemId());
                            _borrower = (UserDetail) hSession.get(UserDetail.class, u.getLenderId());
                
                            out.println("<table><tr><td>Borrower:</td><td>"+_borrower.getUserName() +"</td></tr>");
                            out.println("<tr><td>Item:</td><td>"+ _item.getItemName() +"</td></tr>");
                            out.println("<input type='hidden' name='itemId' value='"+u.getItemId()+"'>");
                            out.println("<tr><td>Date Requested:</td><td>"+u.getRequestedDate()+"</td></tr>");
                                                       
                            //out.println("<tr><td>Description:</td><td>"+_itemDetail.getItemDescription()+"</td></tr></table>");
                           
                            out.println("<hr>");
                         }
                                 
                      
                            %>
                            </table>
                    </div></div>
            </div></div>
        <jsp:include page="footer.jsp"></jsp:include>
        </div>
    </body>
</html>
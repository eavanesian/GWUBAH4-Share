<%-- 
    Document   : leaveFeedback
    Created on : Dec 10, 2012, 12:49:28 AM
    Author     : jay
--%>

<%@page import="org.bahcohortproj.wdywts.ItemDetail"%>
<%@page import="java.util.List"%>
<%@page import="org.hibernate.Query"%>
<%@page import="org.bahcohortproj.wdywts.Transaction"%>
<%@page import="org.hibernate.Session"%>
<%@page import="org.hibernate.SessionFactory"%>
<%@page import="org.bahcohortproj.wdywts.HibernateUtil"%>
<%@page import="org.bahcohortproj.wdywts.UserDetail"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
UserDetail loggedInUser = (UserDetail) session.getAttribute("sUsrName");

if ((loggedInUser == null) || (loggedInUser.getUserID() == 0)) {
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
        [<a href="./leaveFeedback.jsp">Leave Feedback on Returned Items</a>]<BR><BR>
            </div>
            <div class="content">
                <div class="itemForm"><div class="itemBox"><B>Returned Items for Your Feedback:</B><BR>
                        <% //Read items from the db and list                
   
                        SessionFactory sf = new HibernateUtil().getSessionFactory();
                        Session hSession = sf.openSession();
                        hSession.beginTransaction();
               
                        //ItemDetail _item = new ItemDetail();
                        //UserDetail _borrower = new UserDetail();
                        
                        String hql = "FROM Transaction WHERE lenderID = :userID GROUP BY transactionSetID HAVING max(transactionTypeID) = 3";
                        Query query = hSession.createQuery(hql);
                        query.setParameter("userID", loggedInUser.getUserID());
                        List<Transaction> returnedItems = (List<Transaction>) query.list();
                       
                        for (Transaction u : returnedItems) {
                            
                            ItemDetail _itemDetail = new ItemDetail();
                            _itemDetail = (ItemDetail) hSession.get(ItemDetail.class, u.getItemID()); 
                            UserDetail _borrower = new UserDetail();
                            _borrower = (UserDetail) hSession.get(UserDetail.class, u.getBorrowerID());
                            %>
                            
                            
                            Name: <%= _itemDetail.getItemName() %><br>
                            Description: <%= _itemDetail.getItemDescription() %><br>
                            Returned Date: <%=u.getTransactionDate() %><br>
                            Borrower: <%=_borrower.getUserName()%>
                            <br>
                            <br>
                            <form name="leaveFeedback" action="return" method="post" class="normal">
                                <table>
                                    <tr><td>Comments: </td><td><input type="text" name="lenderComments"></td></tr>
                                    <tr><td>Rating of Borrower: </td><td><select name="lenderRatingOfBorrower">
                                    <option value="1">1</option>
                                    <option value="2">2</option>
                                    <option value="3">3</option>
                                    <option value="4">4</option>
                                    <option value="5" selected>5</option></select>
                                        </td></tr>
                                </table>
                            <input type="hidden" name="transactionID" value="<%=u.getTransactionID() %>"> 
                            <input type="hidden" name="transactionSetID" value="<%=u.getTransactionSetID() %>"> 
                            <input type="hidden" name="giverID" value="<%=loggedInUser.getUserID() %>">
                            <input type="hidden" name="receiverID" value="<%=u.getBorrowerID() %>">
                            <input type="hidden" name="doFeedback" value="true">
                            <input type="submit" id="submitButton" class="submitButton" value="leave feedback">
                            </form>
                            <hr>
                        <% }
                        hSession.close();
                    %>
                    </div></div>
            </div></div>
        <jsp:include page="footer.jsp"></jsp:include>
        </div>
    </body>
</html>

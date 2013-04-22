<%-- 
    Document   : itemList
    Created on : Nov 25, 2012, 10:07:25 PM
    Author     : jay
--%>

<%@page import="org.bahcohortproj.wdywts.Transaction"%>
<%@page import="org.bahcohortproj.wdywts.UserItems"%>
<%@page import="org.hibernate.criterion.Order"%>
<%@page import="org.hibernate.criterion.Projections"%>
<%@page import="org.hibernate.criterion.Restrictions"%>
<%@page import="org.hibernate.Criteria"%>
<%@page import="org.hibernate.HibernateException"%>
<%@page import="java.util.List"%>
<%@page import="org.hibernate.transform.Transformers"%>
<%@page import="org.hibernate.Query"%>
<%@page import="org.hibernate.Session"%>
<%@page import="org.hibernate.cfg.Configuration"%>
<%@page import="org.hibernate.SessionFactory"%>
<%@page import="org.eclipse.jdt.internal.compiler.ast.AssertStatement"%>
<%@page import="java.sql.*" %> 
<%@page import="java.io.*" %> 
<%@page import="org.bahcohortproj.wdywts.UserDetail" %>
<%@page import="org.bahcohortproj.wdywts.HibernateUtil" %>
<%@page import="org.bahcohortproj.wdywts.ItemDetail" %>
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
        <title>Items</title>
    </head>
    <body>
        <jsp:include page="includes.jsp"></jsp:include>
        <div class="mainContainer">
            <jsp:include page="topNav.jsp"></jsp:include>
            <div class="mainContentContainer">            
            <div class="leftMenuContainer">
                [<a href="./borrow.jsp">Find Items To Borrow</a>] <br><br>
                    [<a href="./itemsRequested.jsp">Review Items Requested To Borrow</a>] <br><br>
                    [<a href="./itemsBorrowed.jsp">Review Items Currently Borrowing</a>] <BR><BR>
            </div>
                <div class="content">
                <div class="itemForm"><div class="itemList">
                    <H3>Items you are borrowing:<br><span style="color:#336699;font-size:14px;">(not yet returned to lender)</span><br></H3>
                    <div style="text-align:left;font-size:16px;">
                    <% //Read items from the db and list
                        SessionFactory sf = new HibernateUtil().getSessionFactory();        
                        Session hSession = sf.openSession();

                        hSession.beginTransaction();

                        //Criteria c = hSession.createCriteria(Transaction.class);
                        ////c.add(Restrictions.eq("userName", loggedInUser.getUserName()));
                        //c.add(Restrictions.eq("borrowerID", loggedInUser.getUserID()));
                        //c.add(Restrictions.eq("transactionTypeID", 2));
                        
                        String hql = "FROM Transaction WHERE borrowerID = :userID GROUP BY transactionSetID HAVING max(transactionTypeID) = 2";
                        Query query = hSession.createQuery(hql);
                        query.setParameter("userID", loggedInUser.getUserID());
                        List<Transaction> items = (List<Transaction>) query.list();
                        
                        // TODO: = format the following output as a table
                        for (Transaction u : items) {
                            
                            ItemDetail _itemDetail = new ItemDetail();
                            _itemDetail = (ItemDetail) hSession.get(ItemDetail.class, u.getItemID()); 
                            UserDetail _lender = new UserDetail();
                            _lender = (UserDetail) hSession.get(UserDetail.class, _itemDetail.getUserID());
                            %>
                            
                            
                            Name: <%= _itemDetail.getItemName() %><br>
                            Description: <%= _itemDetail.getItemDescription() %><br>
                            Requested Date: <%=u.getTransactionDate() %>
                            Lender: <%=_lender.getUserName()%>
                            <br>
                            <br>
                            <form name="returnItem" action="return" method="post" class="normal">
                                <table>
                                    <tr><td>Comments: </td><td><input type="text" name="borrowerComments"></td></tr>
                                    <tr><td>Rating of Lender: </td><td><select name="borrowerRatingOfLender">
                                    <option value="1">1</option>
                                    <option value="2">2</option>
                                    <option value="3">3</option>
                                    <option value="4">4</option>
                                    <option value="5" selected>5</option></select> &nbsp; &nbsp; <span style="font-size:11px">[1-low, 5-high]</span>
                                        </td></tr>
                                </table>
                            <input type="hidden" name="transactionID" value="<%=u.getTransactionID() %>">
                            <input type="hidden" name="transactionSetID" value="<%=u.getTransactionSetID() %>">
                            <input type="hidden" name="giverID" value="<%=loggedInUser.getUserID() %>">
                            <input type="hidden" name="receiverID" value="<%=_itemDetail.getUserID() %>">
                            <input type="hidden" name="returnItem" value="true">
                            <input type="submit" id="submitButton" class="submitButton" value="return item">
                            </form>
                            <hr>
                            <hr>
                        <% }
                        hSession.close();
                    %>
                    </div></div></div></div></div>
            <jsp:include page="footer.jsp"></jsp:include>
        </div>
    </body>
</html>

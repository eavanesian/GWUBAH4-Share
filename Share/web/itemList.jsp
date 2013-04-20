<%-- 
    Document   : itemList
    Created on : Nov 25, 2012, 10:07:25 PM
    Author     : jay
--%>

<%@page import="org.bahcohortproj.wdywts.EditItemsService"%>
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
        [<a href="./lend.jsp">Create Listing</a>] <BR><BR>
        [<a href="./itemList.jsp">View/Edit Available Items</a>] <BR><BR> 
        [<a href="./reviewRequests.jsp">Review Requests to Borrow</a>]<BR><BR>
        [<a href="./leaveFeedback.jsp">Leave Feedback on Returned Items</a>]<BR><BR>
            </div>
                <div class="content">
                <div class="itemForm"><div class="itemList">
                    <H3>Items you have listed:</H3>
                    <% //Read items from the db and list
                        EditItemsService editItems = new EditItemsService();
                        
                         List<ItemDetail> items = editItems.getItemList(loggedInUser.getUserID());
                        
                        ItemDetail item = new ItemDetail();%>
                        <table class="normal"><tr style="font-weight: bold"><td>Item Name</td><td>Item Description</td><td>Available?</td>
                                <td>Edit?</td><td>Delete?</td></tr>
                            
                        <%
                        for (ItemDetail u : items) {
                        item = u; %>
                        <tr><td colspan="5"><hr></td></tr>
                        <tr><td><%=u.getItemName()%></td><td><%=u.getItemDescription()%></td>
                                <td><%
                                if(u.isAvailable()){
                                    %>Yes<%
                                } else {
                                    %>No<%
                                }%></td>
                                <td><a href="editItem?itemID=<%=u.getItemID()%>&action=edit">Edit</a></td>
                                <td><a href="editItem?itemID=<%=u.getItemID()%>&action=delete">Delete</a></td></tr>
                        <%
                        }
                        %>
                            </table>
                </div></div></div></div>
            <jsp:include page="footer.jsp"></jsp:include>
        </div>
    </body>
</html>

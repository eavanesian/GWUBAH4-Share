<%-- 
    Document   : editItem
    Created on : Dec 9, 2012, 10:21:07 PM
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
            </div>
                <div class="content">
                <div class="itemForm"><div class="itemList">
                    <H3>Edit Item:</H3>
                    <% //Read item from the db and list
                    EditItemsService edit = new EditItemsService();
                    ItemDetail item = new ItemDetail();
                    if(request.getParameter("itemID") != null){
                        int _itemID = Integer.parseInt(request.getParameter("itemID"));
                      
                        SessionFactory sf = new HibernateUtil().getSessionFactory();        
                        Session hSession = sf.openSession();
                        hSession.beginTransaction();
                        item = (ItemDetail) hSession.get(ItemDetail.class, _itemID);
                    }
                    %>
                    <form name="Edit" action="editItem" method="post">
                        <table class="normal"><tr style="font-weight: bold"><td>Item Name</td><td>Item Description</td><td>Available?</td></tr>
                        <tr><td colspan="3"><hr></td></tr>
                        <tr><td><input type="text" name ="itemName"value="<%=item.getItemName()%>"></td>
                            <td><input type="text" name ="itemDescription"value="<%=item.getItemDescription()%>"></td>
                            <td><input type="checkbox" name="available"<%
                                if(item.isAvailable()){
                                    %>checked<%
                                } else {
                                    %><%
                                }%>></td></tr>
                            </table>
                            <input type="submit" value="edit" class="submitButton"">
                            <input type="hidden" name="itemID" value="<%=item.getItemID()%>">
                    </form>
                </div></div></div></div>
            <jsp:include page="footer.jsp"></jsp:include>
        </div>
    </body>
</html>
<%
         try {
            
            
        } catch (Exception e){
 
                   }
                
        %>
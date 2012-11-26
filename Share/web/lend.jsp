<%-- 
    Document   : lend
    Created on : Nov 24, 2012, 7:29:52 PM
    Author     : ed, jay
--%>
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
        <title>Lend</title>
    </head>
    <body>
        <jsp:include page="includes.jsp"></jsp:include>
        <div class="mainContainer">
            <jsp:include page="topNav.jsp"></jsp:include>
        
        <div class="itemForm"><div class="itemBox"><h2>Create an item to lend</h2>
                <span class="normal">Please fill in the form below to create an item to lend.</span><br><br>
        
                <form name="newItemListing" action="lend" method="post" class="normal">
            Item Name: <input type="text" name="itemName"><br>
            Description: <input type="text" name="itemDescription"><br>
            Category: 
                <select>
                <option value="1">Purse</option>
                <option value="2">Tool</option>
                </select><br><br>
            <input type="submit" value="list item" class="submitButton">
            <input type="button" value="cancel" class="submitButton" onclick="window.location.href='./';">
        </form></div></div></div>
            
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
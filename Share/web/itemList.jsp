<%-- 
    Document   : itemList
    Created on : Nov 25, 2012, 10:07:25 PM
    Author     : jay
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
<%@page import="org.bahcohortproj.wdywts.ItemDetail" %>
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
        <title>Items</title>
    </head>
    <body>
        <jsp:include page="includes.jsp"></jsp:include>
        <div class="mainContainer">
            <jsp:include page="topNav.jsp"></jsp:include>
            <div class="itemList">
                <H3>Items you have listed:</H3>
                <% //Read items from the db and list
                    ItemDetail item = new ItemDetail();
                    SessionFactory sf = new HibernateUtil().getSessionFactory();        
                    Session hSession = sf.openSession();

                    hSession.beginTransaction();

                    Criteria c = hSession.createCriteria(ItemDetail.class);
                    c.add(Restrictions.eq("userName", loggedInUser.getUserName()));
                    
                    List<ItemDetail> items = (List<ItemDetail>) c.list();
                    // TODO: = format the following output as a table
                    for (ItemDetail u : items) {
                        item = u; %>
                        <%=u.getItemName()%><BR>
                    <%
                                       }
                    hSession.close();
                %>
            </div>
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>

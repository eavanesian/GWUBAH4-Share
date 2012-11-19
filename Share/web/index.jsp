<%--
    Document   : index
    Created on : Oct 2, 2012, 10:42:48 PM
    Author     : test
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
if (request.getParameter("logout") != null) {
    session.removeAttribute("sUsrName");
    response.sendRedirect("./");
    return;
}%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="includes.jsp"></jsp:include>
        
<%
    UserDetail loggedInUser = (UserDetail) session.getAttribute("sUsrName");

    if ((loggedInUser != null) && (loggedInUser.getUserId() != 0)) {
        out.println("Welcome " + loggedInUser.getFullName() + "<br>");
        out.println("<a href='index.jsp?logout' class='logOut'>Log out</a><br><br>");
    }
%>
        <%--= getServletConfig().getInitParameter("defaultUser") --%>
        
        <form name="userLogin" action="login" method="post">
            <input type="text" name="userName">
            <input type="submit" value="submit" >
        </form>
        
<%
        String _userName = "ea";
        
        UserDetail ed = new UserDetail();
        
        ed.setfName("Ed");
        ed.setlName("A");
        ed.setUserName("ea");
        
        UserDetail j = new UserDetail();
        
        j.setfName("Jay");
        j.setlName("S");
        j.setUserName("js");
        
        UserDetail m = new UserDetail();
        
        m.setfName("Matt");
        m.setlName("S");
        m.setUserName("ms");
        
        SessionFactory sf = new HibernateUtil().getSessionFactory();        
        Session hSession = sf.openSession();
        
        hSession.beginTransaction();
        
        //hSession.saveOrUpdate(ed);
        //hSession.saveOrUpdate(j);
        //hSession.saveOrUpdate(m);
        
        
        hSession.getTransaction().commit();
        hSession.close();
        
       
        %>
    </body>
</html>
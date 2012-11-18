<%--
    Document   : index
    Created on : Oct 2, 2012, 10:42:48 PM
    Author     : test
--%>

<%@page import="org.hibernate.Session"%>
<%@page import="org.hibernate.cfg.Configuration"%>
<%@page import="org.hibernate.SessionFactory"%>
<%@page import="org.eclipse.jdt.internal.compiler.ast.AssertStatement"%>
<%@page import="java.sql.*" %> 
<%@page import="java.io.*" %> 
<%@page import="org.bahcohortproj.wdywts.UserDetail" %>
<%@page import="org.bahcohortproj.wdywts.SessionFactoryHelper" %>
<%@page import="org.bahcohortproj.wdywts.HibernateUtil" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        Testing update
        Matt's Test
        <%
        UserDetail m = new UserDetail();
        
        m.setfName("F");
        m.setlName("L");
        
        SessionFactory sf = new HibernateUtil().getSessionFactory();
        
        //SessionFactory sf = new Configuration().configure().buildSessionFactory();
        Session hSession = sf.openSession();
        
        hSession.beginTransaction();
        hSession.save(m);
        hSession.getTransaction().commit();
        
        hSession.close();
        
        
        out.println("<br><br>");
        out.println(m.getfName());
        out.println("<br>");
        out.println(m.getlName());
        out.println("<br>");
        out.println(m.getFullName());
        %>
        <br>
        
        <% 
/*try {
/* Create string of connection url within specified format with machine name, 
port number and database name. Here machine name id localhost and 
database name is usermaster. * / 
String connectionURL = "jdbc:mysql://localhost:3306/test"; 

// declare a connection by using Connection interface 
Connection connection = null; 

// Load JBBC driver "com.mysql.jdbc.Driver" 
Class.forName("com.mysql.jdbc.Driver").newInstance(); 

/* Create a connection by using getConnection() method that takes parameters of 
string type connection url, user name and password to connect to database. * / 
connection = DriverManager.getConnection(connectionURL, "root", "test");

// check weather connection is established or not by isClosed() method 
if(!connection.isClosed()) 
% >
<font size="+3" color="green"></b>
<% 
out.println("Successfully connected to " + "MySQL server using TCP/IP...");
connection.close();
}
catch(Exception ex){
% >
</font>
<font size="+3" color="red"></b>
<%
out.println("Unable to connect to database.<br>");
out.println(ex.getMessage());
}
% >
</font>
  */ %>      
    </body>
</html>
<%-- 
    Document   : success
    Created on : Nov 18, 2012, 6:04:28 PM
    Author     : ed
--%>

<%@page import="org.bahcohortproj.wdywts.UserDetail"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Logged in successfully!</h1>
        <!-- the following 2 lines are identical, first in block script, the second in JSTL -->
        <!-- % UserDetail user =e (UserDetail) session.getAttribute("user"); % -->
        <jsp:useBean id="sUsrName" class="org.bahcohortproj.wdywts.UserDetail" scope="session">
            <!-- if value is not found in scope, it will create this default for it -->
            <jsp:setProperty name="sUsrName" property="fName" value="test"></jsp:setProperty>            
        </jsp:useBean> 
        
        
        <!-- the following 2 lines are identical, first in block script, the second in JSTL -->
        <!-- %=user.getFullName()% -->        
        <div style="color:red;"><jsp:getProperty name="sUsrName" property="fullName"></jsp:getProperty></div>
        
        <a href="./">Click here to go back to home</a>
        
    </body>
</html>

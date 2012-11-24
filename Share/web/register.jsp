<%-- 
    Document   : register
    Created on : Nov 18, 2012, 9:36:52 PM
    Author     : ed
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
    </head>
    <body>
        <jsp:include page="includes.jsp"></jsp:include>
        
        
        <div class="registerForm"><div class="registerBox"><h2>Register as a new user</h2>
                <span class="normal">Sorry, that username does not seem to exist.<br>Please fill in the form below to register.</span><br><br>
        
                <form name="newUserRegister" action="register" method="post" class="normal">
            Username: <input type="text" name="userName" readonly value="<%=request.getSession().getAttribute("newUserName")%>" class="readOnlyInput"><br>
            First Name: <input type="text" name="fName"><br>
            Last Name: <input type="text" name="lName"><br><br>
            <input type="submit" value="register" class="submitButton">
            <input type="button" value="cancel" class="submitButton" onclick="window.location.href='./';">
        </form></div></div>
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>

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
        <h2>Register as a new user</h2>
        
        Sorry, that username does not seem to exist. Please fill in the form below to register.<br><br>
        
        <form name="newUserRegister" action="register" method="post">
            Username: <input type="text" name="userName" readonly value="<%=request.getSession().getAttribute("newUserName")%>"><br>
            First Name: <input type="text" name="fName"><br>
            Last Name: <input type="text" name="lName"><br><br>
            <input type="submit" value="Register" class="submitButton">
        </form>
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>

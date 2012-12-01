<%-- 
    Document   : admin
    Created on : Nov 27, 2012, 7:32:23 PM
    Author     : jay
--%>

<%@page import="org.bahcohortproj.wdywts.UserDetail"%>
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
        <title>What do you want to share?</title>
    </head>
    <body>
        <jsp:include page="includes.jsp"></jsp:include>
        
        <div class="mainContainer">
            <jsp:include page="topNav.jsp"></jsp:include>
            
            <div class="content">
            
            <% 
                UserDetail loggedInUser = (UserDetail) session.getAttribute("sUsrName");
                if ((loggedInUser != null) && (loggedInUser.isAdmin())) { %>    
 
                <h1>Administrator Functions</h1>
        
            
    <% } %>
            </div>
        <jsp:include page="footer.jsp"></jsp:include>
        </div>
    </body>
</html>
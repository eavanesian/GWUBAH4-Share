<%-- 
    Document   : reviewRequests
    Created on : Dec 3, 2012, 12:09:10 AM
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
            <div class="mainContentContainer">    
                <div class="leftMenuContainer">
        [<a href="./lend.jsp">Create Listing</a>] <BR><BR>
        [<a href="./itemList.jsp">View/Edit Available Items</a>] <BR><BR> 
        [<a href="./reviewRequests.jsp">Review Requests to Borrow</a>]<BR><BR>
            </div>
            <div class="content">
                <div class="itemForm"><div class="itemBox"><B>Placeholder for Request Review</B><BR>
                    </div></div>
            </div></div>
        <jsp:include page="footer.jsp"></jsp:include>
        </div>
    </body>
</html>
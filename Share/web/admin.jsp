<%-- 
    Document   : admin
    Created on : Nov 27, 2012, 7:32:23 PM
    Author     : jay
--%>

<%@page import="sun.security.x509.OIDMap"%>
<%@page import="org.bahcohortproj.wdywts.UserDetail"%>
<%@page import="java.util.List"%>
<%@page import="org.hibernate.Query"%>
<%@page import="org.hibernate.Session"%>
<%@page import="org.hibernate.SessionFactory"%>
<%@page import="org.bahcohortproj.wdywts.HibernateUtil"%>

<%
UserDetail loggedInUser = (UserDetail) session.getAttribute("sUsrName");
if (loggedInUser.isAdmin()) {
    if(request.getParameter("userId") != null){
            int userId = Integer.parseInt(request.getParameter("userId"));
            String userName = request.getParameter("userName");
            String admin = request.getParameter("admin");
            UserDetail _user = new UserDetail(); // user to be updated
            SessionFactory sf = new HibernateUtil().getSessionFactory();        
            Session hSession = sf.openSession();
            hSession.beginTransaction();
            _user = (UserDetail) hSession.get(UserDetail.class, userId);
            
            _user.setUserName(userName);
            if("on".equals(admin)){
                    _user.setAdmin(true);
                } else if(admin == null){
                    _user.setAdmin(false);
                }
            
               
       
            hSession.update(_user);
            
            hSession.getTransaction().commit();
            hSession.close();
            response.sendRedirect("admin.jsp");
}

%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lend</title>
    </head>
    <body>
        <jsp:include page="includes.jsp"></jsp:include>
        <div class="mainContainer">
            <jsp:include page="topNav.jsp"></jsp:include>
            
            <div class="mainContentContainer">
                <div class="leftMenuContainer">
        [<a href="admin.jsp">Edit Users</a>] <BR><BR>
        [Edit Categories]<BR><BR> 
            </div>
            <div class="content">        
                                
                <div class="itemForm"><div class="itemBox"><B>Edit Users</B><BR>
                        <% //Read users from the db and list
                    UserDetail user = new UserDetail();

                    SessionFactory sf = new HibernateUtil().getSessionFactory();        
                    Session hSession = sf.openSession();
                    hSession.beginTransaction();
                    String hql = "FROM UserDetail";
                    Query query = hSession.createQuery(hql);
                    List<UserDetail> users = (List<UserDetail>) query.list();
                    %>
                    <table class="normal"><tr style="font-weight: bold"><td style="width: 200">User Name</td>
                            <td style="width: 100">Admin?</td><td>Edit User</td></tr>
                        <%
                        for(UserDetail u : users){ %>
                        <form name="editUsers" action="admin.jsp" method="post">
                            <tr><td><input type="text" name ="userName" value="<%=u.getUserName()%>"></td>
                            <td><input type="checkbox" name="admin"<% 
                                if(u.isAdmin()){
                                    %>checked<%
                                } else {
                                    %><%
                                }%>></td>
                            <td><input type="submit" value="edit" class="submitButton"></td></tr>
                            <input type="hidden" name="userId" value="<%=u.getUserId()%>">
                        </form>
                            <tr><td colspan="3"><hr></td></tr>
                        <%
                        }

                        %>
                    
           
                    </table>
                        
               
                    </div></div></div>
            </div>    
        <jsp:include page="footer.jsp"></jsp:include>
 
        </div>
    </body>
</html>
<%}
else {%>
<html>
    <head>
        You do not have permission to access this resource.
    </head>
    <br>
    <br>
<a href="index.jsp">Click here to return to the home page.</a>
</html>
<% } %>
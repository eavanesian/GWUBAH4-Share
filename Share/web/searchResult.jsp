<%-- 
    Document   : search
    Created on : Nov 24, 2012, 7:29:52 PM
    Author     : ed
--%>
<%@page import="org.bahcohortproj.wdywts.SearchService"%>
<%@page import="org.hibernate.cfg.search.SearchConfiguration"%>
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
<%@page import="org.bahcohortproj.wdywts.ItemDetail" %>
<%@page import="org.bahcohortproj.wdywts.HibernateUtil" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    UserDetail loggedInUser = (UserDetail) session.getAttribute("sUsrName");

    if ((loggedInUser == null) || (loggedInUser.getUserId() == 0)) {
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
                <H1>SEARCH RESULTS</h1>
            <% //Read items from the db and list
                        int _category;
                        String _userSearch;
                        ItemDetail item = new ItemDetail();
                        SearchService search = new SearchService();
                        if(request.getParameter("category") != null){
                            _category = Integer.parseInt(request.getParameter("category"));
                            List<ItemDetail> items = search.getItems(_category);
                             // TODO: = format the following output as a table
                            for (ItemDetail u : items) {
                              item = u; %>
                              <a href="showItemDetails.jsp?itemId=<%=u.getItemId()%>">[<!--%u.getUserName()%-->] - <%=u.getItemName()%></a><BR><%
                            }
                           
                        } else if(request.getParameter("userSearch") != null){
                            _userSearch = request.getParameter("userSearch");
                            List<ItemDetail> items = search.getItems(_userSearch);
                                 // TODO: = format the following output as a table
                            for (ItemDetail u : items) {
                              item = u; %>
                              <a href="showItemDetails.jsp?itemId=<%=u.getItemId()%>">[<!--%=u.getUserName()%-->] - <%=u.getItemName()%></a><BR><%
                            }
                        }  %>

                       

            </div>

            <jsp:include page="footer.jsp"></jsp:include>
        </div>
    </body>
</html>
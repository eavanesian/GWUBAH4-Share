<%-- 
    Document   : search
    Created on : Nov 24, 2012, 7:29:52 PM
    Author     : ed
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
                    <%
                        String searchValue = request.getParameter("userSearch");
                        //out.println(searchValue);

                        SessionFactory sf = new HibernateUtil().getSessionFactory();
                        Session hSession = sf.openSession();
                        hSession.beginTransaction();

                        Query query = hSession.createQuery("from ItemDetail where itemName = :code ");
                        query.setParameter("code", searchValue);
                        List list = query.list();
                        
                        //need to modify the following to print search results:
                        for (int i = 0; i < list.size(); i++) {
                            out.println(list.get(i));
                        }

                    %>

            </div>

            <jsp:include page="footer.jsp"></jsp:include>
        </div>
    </body>
</html>
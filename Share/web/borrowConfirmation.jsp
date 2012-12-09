<%-- 
    Document   : borrowConfirmation
    Created on : Dec 9, 2012, 1:01:15 AM
    Author     : ed
--%>

<%@page import="org.bahcohortproj.wdywts.ItemDetail"%>
<%@page import="org.bahcohortproj.wdywts.UserItems"%>
<%@page import="org.bahcohortproj.wdywts.Category"%>
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
UserDetail loggedInUser = (UserDetail) session.getAttribute("sUsrName");

if ((loggedInUser == null) || (loggedInUser.getUserId() == 0)) {
    session.removeAttribute("sUsrName");
    response.sendRedirect("./");
    return;
} %>
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
                <H1>BORROW</h1><div style="font-size:18px;">Request Borrow Confirmation</div><br>
                <%
                    UserItems userItems = (UserItems) request.getSession().getAttribute("requestedItem");
                    //session.removeAttribute("requestedItem");
                
                SessionFactory sf = new HibernateUtil().getSessionFactory();
                Session hSession = sf.openSession();

                hSession.beginTransaction();
        
                ItemDetail _itemDetail = new ItemDetail();
                _itemDetail = (ItemDetail) hSession.get(ItemDetail.class, userItems.getItemId());
                
                UserDetail _lender = new UserDetail();
                _lender = (UserDetail) hSession.get(UserDetail.class, _itemDetail.getUserId());
                
                hSession.getTransaction().commit();
                hSession.close();
                %>
                
                You have requested the following item to borrow:<br><br><br>
                
                Name: <%= _itemDetail.getItemName()%> <br>
                Lender: <%= _lender.getUserName() %> <br>
            </div>
        
        <jsp:include page="footer.jsp"></jsp:include>
        </div>
    </body>
</html>
<%-- 
    Document   : showItemDetails
    Created on : Dec 2, 2012, 10:37:44 PM
    Author     : matt
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
                <H1>ITEM DETAILS</h1>
                <form name="borrowItem" action="borrow" method="post">
            <%

                        SessionFactory sf = new HibernateUtil().getSessionFactory();
                        Session hSession = sf.openSession();
                        hSession.beginTransaction();
                                          
                       
                        Query itemQuery = hSession.createQuery("from ItemDetail where itemId =" +request.getParameter("itemId"));
                        List<ItemDetail> detailList = (List<ItemDetail>) itemQuery.list();
                        
                                              
                        hSession.getTransaction().commit();
                        hSession.close();
                        
                       out.println("<div align='center'>");
                       for (ItemDetail id : detailList){
                           
                           out.println("<input type='hidden' name='itemId' value='"+id.getItemId()+"'>");
                           out.println("<table><tr><td>Item ID:</td><td>"+id.getItemId()+"</td></tr>");
                           //out.println("<tr><td>User:</td><td>"+id.getUserName()+"</td></tr>");
                           out.println("<tr><td>User:</td><td>"+id.getUserId()+"</td></tr>");
                           out.println("<tr><td>Item:</td><td>"+id.getItemName()+"</td></tr>");
                           out.println("<tr><td>Description:</td><td>"+id.getItemDescription()+"</td></tr></table>");
                           
                        }  
                       out.println("</div>");                                       
                    %>

                <br>
                
                <input type="submit" id="submitButton" class="submitButton" value="borrow item">
                
                </form>

            </div>
                    

            <jsp:include page="footer.jsp"></jsp:include>
        </div>
    </body>
</html>

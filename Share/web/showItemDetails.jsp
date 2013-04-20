<%-- 
    Document   : showItemDetails
    Created on : Dec 2, 2012, 10:37:44 PM
    Author     : matt
--%>

<%@page import="org.hibernate.annotations.Parent"%>
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

    if ((loggedInUser == null) || (loggedInUser.getUserID() == 0)) {
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
                                          
                       
                        /*Query itemQuery = hSession.createQuery("from ItemDetail where itemID =" +request.getParameter("itemId"));
                        List<ItemDetail> detailList = (List<ItemDetail>) itemQuery.list();
                                              
                       out.println("<div align='center'>");
                       for (ItemDetail ID : detailList){
                           
                           out.println("<input type='hidden' name='itemID' value='"+ID.getItemID()+"'>");
                           out.println("<table><tr><td>Item ID:</td><td>"+ID.getItemID()+"</td></tr>");
                           //out.println("<tr><td>User:</td><td>"+ID.getUserName()+"</td></tr>");
                           out.println("<tr><td>User:</td><td>"+ID.getUserID()+"</td></tr>");
                           out.println("<tr><td>Item:</td><td>"+ID.getItemName()+"</td></tr>");
                           out.println("<tr><td>Description:</td><td>"+ID.getItemDescription()+"</td></tr></table>");
                           
                        }  
                       out.println("</div>");
                       */
        

                                                             
                       //Short-hand to pull item by itemID primary key
                        ItemDetail _itemDetail = new ItemDetail();
                        _itemDetail = (ItemDetail) hSession.get(ItemDetail.class, Integer.parseInt(request.getParameter("itemId")));
                        
                        String hql = "SELECT userName FROM UserDetail U WHERE U.userID = :userID";
                        Query query = hSession.createQuery(hql);
                        query.setParameter("userID", _itemDetail.getUserID());
                        List itemOwner = query.list();
                       
                        out.println("<div align='center'>");
                        out.println("<input type='hidden' name='itemID' value='"+_itemDetail.getItemID()+"'>");
                        out.println("<table><tr><td>Item ID:</td><td>"+_itemDetail.getItemID()+"</td></tr>");
                        //out.println("<tr><td>User:</td><td>"+ID.getUserName()+"</td></tr>");
                        out.println("<tr><td>User:</td><td>"+itemOwner+"</td></tr>");
                        out.println("<tr><td>Item:</td><td>"+_itemDetail.getItemName()+"</td></tr>");
                        out.println("<tr><td>Description:</td><td>"+_itemDetail.getItemDescription()+"</td></tr></table>");
                           
                        out.println("</div>");
                        
                        hSession.getTransaction().commit();
                        hSession.close();
                        
                    %>

                <br>
                
                <% if(_itemDetail.isAvailable()) { %>
                    <input type="submit" id="submitButton" class="submitButton" value="borrow item">
                <% } else { %>
                <span style="font-size:12px;color:red;">This item is not available to be borrowed at this time</span>
                <% } %>
                </form>

            </div>
                    

            <jsp:include page="footer.jsp"></jsp:include>
        </div>
    </body>
</html>

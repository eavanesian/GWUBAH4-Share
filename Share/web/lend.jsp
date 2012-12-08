<%-- 
    Document   : lend
    Created on : Nov 24, 2012, 7:29:52 PM
    Author     : ed, jay
--%>
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
        <title>Lend</title>
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
                <div class="itemForm"><div class="itemBox"><B>Create an item to lend</B><BR>
                <span class="normal">Please fill in the form below to create an item to lend.
                    <br>If your category is not listed, create a new category at the bottom of the form.</span><br><br>
        
                <form name="newItem" action="lend" method="post" class="normal">
            Item Name: <input type="text" name="itemName"><br>
            Description: <input type="text" name="itemDescription"><br>
            Category: 
                <select name="itemCategory">
                    <option value ="">-Select sub category below-</option>
                <% //setup session to pull categories
                    SessionFactory sf = new HibernateUtil().getSessionFactory();
                    Session hSession = sf.openSession();
                    hSession.beginTransaction();
                    
                    Criteria c = hSession.createCriteria(Category.class);
                    c.add(Restrictions.eq("parentCategoryId", new Integer(0)));
                    c.addOrder(Order.asc("name"));
                    List<Category> categories = (List<Category>) c.list();
                    
                    for (Category cat : categories) { 
                        %>
                        <option disabled="disabled" value=""><%=cat.getName()%></option>
                        <%
                        Criteria sc = hSession.createCriteria(Category.class);
                        sc.add(Restrictions.eq("parentCategoryId", new Integer(cat.getCategoryId())));
                        sc.addOrder(Order.asc("name"));
                        List<Category> subcategories = (List<Category>) sc.list();
                        for (Category subcat : subcategories) {
                        %>
                        <option value="<%=subcat.getCategoryId()%>">--<%=subcat.getName()%></option>
                        <% } %>
                    <% } %>
                </select><br><br>
                <input type="hidden" name ="userId" value="<%=loggedInUser.getUserId()%>"> 
            <input type="submit" value="list item" class="submitButton">
            <input type="button" value="cancel" class="submitButton" onclick="window.location.href='./';">
            <input type="hidden" name="lendFunction" value=1>
        </form>
            <hr>
            <form name="newCategory" action="lend" method="post" class="normal">
                <input type="text" name="categoryName">
                <input type="submit" value="create category" class="submitButton"><br>
                <input type="hidden" name="lendFunction" value=2>
            </form>
            <hr>
            <form name="newSubCategory" action="lend" method="post" class="normal"> 
                Create sub category by selecting the main category in the pulldown below, typing your sub category name, and clicking "create sub category."
                <br>
                <select name="categoryList">
                    <option value="">-Select category below-</option>
                     <%                    
                    for (Category cat : categories) { 
                        %>
                        <option value="<%=cat.getCategoryId()%>"><%=cat.getName()%></option>
                    <% } %>
                </select>
                  <input type="text" name="subCategoryName">
                  <input type="submit" value="create sub category" class="submitButton"><br> 
                  <input type="hidden" name="lendFunction" value=3>
            </form>
                    </div></div></div>
            </div>    
        <jsp:include page="footer.jsp"></jsp:include>
 
        </div>
    </body>
</html>
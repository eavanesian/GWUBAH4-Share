<%--
    Document   : index
    Created on : Oct 2, 2012, 10:42:48 PM
    Author     : test
--%>
<%@page import="org.bahcohortproj.wdywts.UserItems"%>
<%@page import="org.bahcohortproj.wdywts.ItemDetail"%>
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
<%@page import="java.util.Date"%>
<%@page import="org.bahcohortproj.wdywts.UserDetail" %>
<%@page import="org.bahcohortproj.wdywts.HibernateUtil" %>
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
        
        <%--= getServletConfig().getInitParameter("defaultUser") --%>
        
<%
    UserDetail loggedInUser = (UserDetail) session.getAttribute("sUsrName");

    if ((loggedInUser != null) && (loggedInUser.getUserID() != 0)) { %>
    
        <div class="mainContainer">
            <jsp:include page="topNav.jsp"></jsp:include>
            
            <div class="content">
                <h1>What do you want to do?</h1><br><br>
                <div class="descriptionContainer"><a id="navButton" href="./search.jsp" onmouseover="showDescription('search');" onmouseout="showDescription('');">search</a>
                    <a id="navButton" href="./borrow.jsp" onmouseover="showDescription('borrow');" onmouseout="showDescription('');">borrow</a>
                    <a id="navButton" href="./lend.jsp" onmouseover="showDescription('lend');" onmouseout="showDescription('');">lend</a>
                    
                </div>
                <div id="description" class="fade"></div>
            </div>
        
        
        
    <% } else { %>
    <div class="loginForm"><div class="loginBox">What Do You Want To Share?<br><br>
            <form name="userLogin" action="login" method="post">
                <input type="text" id="username" name="userName" value="username" class="username" tabindex="1"
                       onblur="usernameInputCheck(this,'username');" 
                       onclick="checkFocus(this,'username');" 
                       onkeypress="checkFocus(this,'username');">
                <input type="submit" id="submitButton" class="submitButton" value="sign in">
            </form></div>
        </div>
    <% } %>
    
   
    
<%
        /*String _userName = "ea";
        
        UserDetail ed = new UserDetail();
        
        ed.setfName("Ed");
        ed.setlName("A");
        ed.setUserName("ea");
        ed.setAdmin(true);
        
        UserDetail j = new UserDetail();
        
        j.setfName("Jay");
        j.setlName("S");
        j.setUserName("js");
        j.setAdmin(false);
        
        UserDetail m = new UserDetail();
        
        m.setfName("Matt");
        m.setlName("S");
        m.setUserName("ms");
        m.setAdmin(false);
        
        SessionFactory sf = new HibernateUtil().getSessionFactory();        
        Session hSession = sf.openSession();
        
        hSession.beginTransaction();
        
        //hSession.saveOrUpdate(ed);
        //hSession.saveOrUpdate(j);
        //hSession.saveOrUpdate(m);
        
        
        hSession.getTransaction().commit();
        hSession.close();
        */
       
/*
        Category c1 = new Category();
        c1.setName("Tools");
        
        Category c2 = new Category();
        c2.setName("Purses");
        
        Category c3 = new Category();
        c3.setName("Electronics");
        
        Category sc1 = new Category();
        sc1.setName("Hammer");
        
        Category sc2 = new Category();
        sc2.setName("Snow blower");
        
        Category sc3 = new Category();
        sc3.setName("Shovel");
        
        Category sc4 = new Category();
        sc4.setName("Stereo");
        
        
        SessionFactory sf = new HibernateUtil().getSessionFactory();        
        Session hSession = sf.openSession();
        
        hSession.beginTransaction();
        
        hSession.save(c1);
        hSession.saveOrUpdate(c2);
        hSession.saveOrUpdate(c3);
        
        
        hSession.getTransaction().commit();
        
        
        sc1.setParentCategoryID(c1.getCategoryID());
        sc2.setParentCategoryID(c1.getCategoryID());
        sc3.setParentCategoryID(c1.getCategoryID());
        sc4.setParentCategoryID(c3.getCategoryID());

        
        hSession.saveOrUpdate(sc1);
        hSession.saveOrUpdate(sc2);
        hSession.saveOrUpdate(sc3);
        hSession.saveOrUpdate(sc4);
        
        hSession.close();*/



// the following is for test only, it will create new user, item and user_item association with some test data in it
/*
        SessionFactory sf = new HibernateUtil().getSessionFactory();        
        Session hSession = sf.openSession();
        hSession.beginTransaction();

         UserDetail userDetail = new UserDetail();
         userDetail.setUserName("test1");
         userDetail.setfName("Test m2m");
         userDetail.setlName("lname");
         
         ItemDetail itemDetail = new ItemDetail("name", "desc", 10);
         
         //new category, need save to get the ID first
         hSession.save(itemDetail);

         
         UserItems userItems = new UserItems();
         userItems.setLender(userDetail);
         userItems.setItem(itemDetail);
         
         userItems.setListedDate(new Date());
         userItems.setStauts(1);
         
         userDetail.getUserItems().add(userItems);
         
         
         
         hSession.save(userDetail);

         hSession.getTransaction().commit();
         */
         
        %>
        
        <jsp:include page="footer.jsp"></jsp:include>
    </div>
    </body>
</html>
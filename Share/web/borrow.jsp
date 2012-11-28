<%-- 
    Document   : search
    Created on : Nov 24, 2012, 7:29:52 PM
    Author     : ed
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
        <title>What do you want to share?</title>
    </head>
    <body>
        <jsp:include page="includes.jsp"></jsp:include>
        
        <div class="mainContainer">
            <jsp:include page="topNav.jsp"></jsp:include>
            
            <div class="content">
                <H1>BORROW</h1>
                <%
                
                SessionFactory sf = new HibernateUtil().getSessionFactory();
                Session hSession = sf.openSession();

                hSession.beginTransaction();
                //hSession = sf.openSession();
                //hSession.beginTransaction();


                //Query q = hSession.getNamedQuery("userDetail.byUserName");
                //q.setParameter("userName", _userName);

               /* Query q = hSession.createQuery("FROM UserDetail where userName = :userName ");
                //Query q = hSession.createQuery("SELECT lName FROM UserDetail where userName = :userName ");

                q.setParameter("userName", _userName);
                */

                Criteria c = hSession.createCriteria(Category.class);
                c.add(Restrictions.eq("parentCategoryId", new Integer(0)));
                c.addOrder(Order.asc("name"));

                List<Category> categories = (List<Category>) c.list();

                

                //for (String u : users) {
                for (Category cat : categories) {        
                    out.println("[" + cat.getName() +"]<br>");
                    
                    Criteria sc = hSession.createCriteria(Category.class);
                    sc.add(Restrictions.eq("parentCategoryId", new Integer(cat.getCategoryId())));
                    sc.addOrder(Order.asc("name"));
                    
                    List<Category> subcategories = (List<Category>) sc.list();
                    
                    for (Category subcat : subcategories) {
                        out.println("&nbsp;&nbsp{" + subcat.getName() +"}<br>");
                    }
                    out.println("<br>");
                    
                }
        
                hSession.close();
                
                %>
            </div>
        </div>
    
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
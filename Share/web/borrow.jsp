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
        <link rel="stylesheet" href="style/jquery-ui.css" type="text/css" />
        <script src="scripts/jquery-1.8.3.js"></script>
        <script src="scripts/jquery-ui.js"></script>
        <script type="text/javascript">
             $(function() {
                $( "#accordion" ).accordion({
                    active: false,
                    collapsible: true,
                    heightStyle: "content"
                });
            });
        </script>
        
        <div class="mainContainer">
            <jsp:include page="topNav.jsp"></jsp:include>
            
            <div class="content">
                <H1>BORROW</h1><div style="font-size:16px;">Pick a category:</div><br>
                <script type="text/javascript">
                    catSubcat = new Array();
                    catSubcat = {<%
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

                // fake a running for-loop counter since for loop is itterating through an array list of objects
                int runningCounter = 0;

                //for (String u : users) {
                for (Category cat : categories) { %>
                    
                    <%=cat.getName()%>: [<%
                    //out.println("[" + cat.getName() +"]<br>");
                    
                    Criteria sc = hSession.createCriteria(Category.class);
                    sc.add(Restrictions.eq("parentCategoryId", new Integer(cat.getCategoryId())));
                    sc.addOrder(Order.asc("name"));
                    
                    List<Category> subcategories = (List<Category>) sc.list();
                    
                    // fake a running for-loop counter since for loop is itterating through an array list of objects
                    int runningSubCounter = 0;
                    
                    for (Category subcat : subcategories) {
                        out.print("'" + subcat.getName() +"'");
                        if (runningSubCounter < subcategories.size()-1){
                            out.print(",");
                            runningSubCounter++;
                        }
                    }
                    out.print("]");
                    if (runningCounter < categories.size()-1){
                        out.print(",");
                        runningCounter++;
                    }
                    
                }
                hSession.close();
                %>};
                        
                        document.write("<div id='accordion' style='width:300px;margin-left:auto;margin-right:auto;text-align:left;'>");
                    
                        for (cat in catSubcat){
                            document.write ("<h3 style='padding:.5em 0 .5em .5em;font-size:18px;font-weight:bold;'>"+cat+"</h3><div style='padding:.5em 0 .5em .5em;font-size:14px;text-align:left;'>");
                            for (subcat in catSubcat[cat]){
                                document.write("-<a href='searchResult.jsp?userSearch="+catSubcat[cat][subcat]+"'>" + catSubcat[cat][subcat]+"</a><br>");
                            }
                            document.write("</div>");
                        }
                        
                </script>
            </div>
        
        <jsp:include page="footer.jsp"></jsp:include>
        </div>
    </body>
</html>
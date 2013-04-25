<%-- 
    Document   : admin
    Created on : Nov 27, 2012, 7:32:23 PM
    Author     : jay
--%>

<%@page import="sun.security.x509.OIDMap"%>
<%@page import="org.bahcohortproj.wdywts.Category"%>
<%@page import="org.bahcohortproj.wdywts.UserDetail"%>
<%@page import="java.util.List"%>
<%@page import="org.hibernate.Query"%>
<%@page import="org.hibernate.Session"%>
<%@page import="org.hibernate.SessionFactory"%>
<%@page import="org.bahcohortproj.wdywts.HibernateUtil"%>

<%
UserDetail loggedInUser = (UserDetail) session.getAttribute("sUsrName");
if (loggedInUser.isAdmin()) {
    if((request.getParameter("action") != null) && (request.getParameter("cID") != null) ){
            SessionFactory sf = new HibernateUtil().getSessionFactory();
            Session hSession = sf.openSession();
            hSession.beginTransaction();
            
            int _cID = Integer.parseInt(request.getParameter("cID"));
            Category _cDelete = new Category();
            _cDelete = (Category) hSession.get(Category.class, _cID);
                         
            hSession.delete(_cDelete);
            
            
            hSession.getTransaction().commit();
            hSession.close();
            response.sendRedirect("editCategories.jsp");
    }
    if(request.getParameter("categoryID") != null){
            int categoryID = Integer.parseInt(request.getParameter("categoryID"));
            String categoryName = request.getParameter("categoryName");
            int parentCategory = Integer.parseInt(request.getParameter("parentCategory"));
            
           
            
            Category _category = new Category(); // category to be updated
            SessionFactory sf = new HibernateUtil().getSessionFactory();        
            Session hSession = sf.openSession();
            hSession.beginTransaction();
            _category = (Category) hSession.get(Category.class, categoryID);
            
            _category.setName(categoryName);
            _category.setCategoryID(categoryID);
            _category.setParentCategoryID(parentCategory);
               
            hSession.update(_category);
            
            hSession.getTransaction().commit();
            hSession.close();
            response.sendRedirect("editCategories.jsp");
}

%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin</title>
    </head>
    <body>
        <jsp:include page="includes.jsp"></jsp:include>
        <div class="mainContainer">
            <jsp:include page="topNav.jsp"></jsp:include>
            
            <div class="mainContentContainer">
                <div class="leftMenuContainer">
        [<a href="admin.jsp">Edit Users</a>] <BR><BR>
        [<a href="editCategories.jsp">Edit Categories</a>]<BR><BR>
        
            </div>
            <div class="content">        
                                
                <div class="itemForm"><div class="itemBox" style="width: 500"><B>Edit Categories</B><BR>
                        <div style="color: red">WARNING - Don't edit ID or Parent ID unless you know what you are doing!</div>
                        <% //Read users from the db and list
                    Category category = new Category();

                    SessionFactory sf = new HibernateUtil().getSessionFactory();        
                    Session hSession = sf.openSession();
                    hSession.beginTransaction();
                    String hql = "FROM Category";
                    Query query = hSession.createQuery(hql);
                    List<Category> categories = (List<Category>) query.list();
                    %>
                    <table class="normal"><tr style="font-weight: bold"><td>Category Name</td>
                            <td>ID</td><td>Parent</td><td style="width: 75">Edit Category</td><td style="width: 75">Delete</td></tr>
                        <%
                        for(Category c : categories){ %>
                        <form name="editCategories" action="editCategories.jsp" method="post">
                            <tr><td><input type="text" name ="categoryName" value="<%=c.getName()%>"></td>
                                <td><input type="text" style ="width: 75" name ="categoryID" value="<%=c.getCategoryID()%>"></td>
                                <td><input type ="text" style ="width: 75" name="parentCategory" value="<%=c.getParentCategoryID()%>"></td>
                            
                            <td align="right"><input type="submit" name="editCat" value="edit" class="submitButton"></td>
                            <td align="right"><input type="button" name="deleteCat" value="delete" class="submitButton" onclick="document.location.href='editCategories.jsp?action=d&cID=<%=c.getCategoryID()%>'"></td></tr>
                            <input type="hidden" name="userID" value="<%=c.getCategoryID()%>">
                        </form>
                            <tr><td colspan="5"><hr></td></tr>
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
else 
response.sendRedirect("index.jsp");
%>
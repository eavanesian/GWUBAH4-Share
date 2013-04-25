<%@page import="org.bahcohortproj.wdywts.UserDetail" %>
<%@page import="org.bahcohortproj.wdywts.HibernateUtil" %>
<%@page import="org.bahcohortproj.wdywts.Feedback" %> 
<%@page import="org.hibernate.Session"%>
<%@page import="org.hibernate.SessionFactory"%>
<%@page import="java.util.List"%>
<%@page import="org.hibernate.Query"%>
<%@page import="org.bahcohortproj.wdywts.Transaction"%>
<%    
UserDetail loggedInUser = (UserDetail) session.getAttribute("sUsrName"); 
%>
<div class="header">
<div id='cssmenu'>
                <ul>
                   <li id="menu_home"><a href='./'><span>home</span></a></li>
                   <li id="menu_search"><a href='search.jsp'><span>search</span></a></li>
                   <li id="menu_borrow"><a href='borrow.jsp'><span>borrow</span></a></li>
                   <li id="menu_lend"><a href='lend.jsp'><span>lend</span></a></li>
                </ul>
                    <%
            if (!(loggedInUser == null)) {
            SessionFactory sf = new HibernateUtil().getSessionFactory();
            Session hSession = sf.openSession();
            hSession.beginTransaction();
            String hql = "FROM Transaction WHERE lenderID = :userID GROUP BY transactionSetID HAVING max(transactionTypeID) = 1";
            Query query = hSession.createQuery(hql);
            query.setParameter("userID", loggedInUser.getUserID());
            List<Transaction> returnedItems = (List<Transaction>) query.list();
            
            if (!returnedItems.isEmpty()){ %>
                <a href="/Share/reviewRequests.jsp" style="font-size:12px;color:orange;font-weight:bold;">[<%=returnedItems.size()%>] lend request<% if (returnedItems.size()>1){%>s<%}%></a>
            <% } 
            }%>
                
                <script type="text/javascript">activateMenu();</script>
                <% if (loggedInUser != null) { %>
                    <div class="signOutButton"><input type="button" onclick="window.location.href='index.jsp?logout'" class="submitButton" value="sign out"></div>
            </div>
            <div class="userGreeting">Welcome <%=loggedInUser.getFullName()%>
                <% if (loggedInUser.isAdmin()) { %>
                [<a href="./admin.jsp">Admin</a>]
                <% } %> 
                <br>
                <%
                Integer currentUserID = loggedInUser.getUserID();
                Double userRating = new Feedback().getAvgRating(currentUserID);                
                %>
                Your Current Rating: <%=userRating%>
             <% } %>
            
            </div>
            <br></div>
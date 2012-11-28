<%@page import="org.bahcohortproj.wdywts.UserDetail" %>
<%@page import="org.bahcohortproj.wdywts.HibernateUtil" %>
<%    
UserDetail loggedInUser = (UserDetail) session.getAttribute("sUsrName"); 
%>
<div id='cssmenu'>
                <ul>
                   <li id="menu_home"><a href='./'><span>home</span></a></li>
                   <li id="menu_search"><a href='search.jsp'><span>search</span></a></li>
                   <li id="menu_borrow"><a href='borrow.jsp'><span>borrow</span></a></li>
                   <li id="menu_lend"><a href='lend.jsp'><span>lend</span></a></li>                   
                </ul>
                <script type="text/javascript">activateMenu();</script>
                <% if (loggedInUser != null) { %>
                    <div class="signOutButton"><input type="button" onclick="window.location.href='index.jsp?logout'" class="submitButton" value="sign out"></div>
            </div>
            <div class="userGreeting">Welcome <%=loggedInUser.getFullName()%>
                <% if (loggedInUser.isAdmin()) { %>
                [<a href="./admin.jsp">Admin</a>]
                <% } %> 
            </div> <% } %>
            <br>
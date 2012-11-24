<%@page import="java.util.Date"%>
<%@page import="org.apache.catalina.connector.OutputBuffer"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<div class="footerContainer"><div class="footerLeft"><a href="./" class="footerLink">Home</a> | <a href="" class="footerLink">About</a></div>
    <div class="footerRight">GWU Cohort Project - Team Avanesian, Schweitzer &amp Sebastian 
        &copy<% DateFormat fmt = new SimpleDateFormat("yyyy"); String now = fmt.format(new Date()); out.println(now); %></div></div>
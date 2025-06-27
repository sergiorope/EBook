<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="./includes/Head.jsp" %>
 <style>

    footer {
      position: fixed;
      bottom: 0;
      width: 100%;
    }
  </style>
</head>
<body>
<%@include file="./includes/Navbar.jsp" %>

<%
    boolean esIndex = false; 
%>

<div class="ms-2">
<h2>La transacci�n se complet� con exito! </h2>
<a href="./" class="btn btn-primary">Volver al Cat�logo</a>
</div>


<%@include file="./includes/Footer.jsp" %>
</body>
</html>
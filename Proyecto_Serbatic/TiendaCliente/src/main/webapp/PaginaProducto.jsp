<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" import="java.util.List,java.util.ArrayList,ModeloVO.ProductoVO" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Insert title here</title>
</head>
<body>

<% ProductoVO p = (ProductoVO)request.getAttribute("producto");%>

    <h2>Descripción: <%= p.getDescripcion()  %></h2>
    <h2>Nombre: <%= p.getNombre() %></h2>
    <h2>Precio: <%= p.getPrecio() %></h2>
    <img src="<%= p.getImagen() %>">
 
</body>
</html>

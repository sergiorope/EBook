<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<div class="container">
    <div class="row justify-content-center mt-5">
        <div class="col-md-6">
                    <h2 class="card-title text-center">Iniciar Sesión</h2>
                    <form method="POST" action="./inicio">
                        <div class="form-group">
                            <label for="email">Correo:</label>
                            <input type="text" class="form-control" id="email" name="email">
                        </div>
                        <div class="form-group">
                            <label for="clave">Contraseña:</label>
                            <input type="password" class="form-control" id="clave" name="clave">
                        </div>
                        <div class="form-group text-center">
                            <a href="registro.jsp">¿Aún no tienes cuenta?</a>
                        </div>
                        <div class="form-group text-center">
                            <button type="submit" class="btn btn-primary">Entrar</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        
      <% if (request.getAttribute("ErrorInicio") != null) { %>
    <div style="color: red; font-weight: bold;">
        <%= request.getAttribute("ErrorInicio") %>
    </div>
    <% } %>
 <%@include file="./includes/Footer.jsp" %>
</body>
</html>






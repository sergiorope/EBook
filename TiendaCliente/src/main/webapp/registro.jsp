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
<div class="container">
    <div class="row justify-content-center mt-5">
        <div class="col-md-6">
            <h2 class="text-center">Registro</h2>
            <form method="" action="./urlRegistro">
                <div class="form-group">
                    <label for="nombreRegister">Nombre:</label>
                    <input type="text" class="form-control" id="nombreRegister" name="nombreRegister">
                </div>
                <div class="form-group">
                    <label for="apellidos">Apellidos:</label>
                    <input type="text" class="form-control" id="apellidos" name="apellidos">
                </div>
                <div class="form-group">
                    <label for="email">Correo:</label>
                    <input type="text" class="form-control" id="email" name="email">
                </div>
                <div class="form-group">
                    <label for="clave">Contraseña:</label>
                    <input type="password" class="form-control" id="clave" name="clave">
                </div>
                <div class="form-group">
                    <label for="repiteclave">Repite contraseña:</label>
                    <input type="password" class="form-control" id="repiteclave" name="repiteclave">
                    <br>
                </div>
                <button type="submit" class="btn btn-primary">Registrarse</button>
            </form>
            <% if (request.getAttribute("errorCampos") != null) { %>
                <div class="text-danger font-weight-bold mt-3">
                    <%= request.getAttribute("errorCampos") %>
                </div>
            <% } %>
            <% if (request.getAttribute("errorClave") != null) { %>
                <div class="text-danger font-weight-bold mt-3">
                    <%= request.getAttribute("errorClave") %>
                </div>
            <% } %>
            
            <% if (request.getAttribute("errorEmail") != null) { %>
                <div class="text-danger font-weight-bold mt-3">
                    <%= request.getAttribute("errorEmail") %>
                </div>
            <% } %>
        </div>
    </div>
</div>

 <%@include file="./includes/Footer.jsp" %>
</body>
</html>
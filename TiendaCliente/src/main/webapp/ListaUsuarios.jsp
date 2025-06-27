<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List, java.util.Map, ModeloVO.UsuarioVO"%>
<!DOCTYPE html>
<html>
<head>
  <%@include file="./includes/Head.jsp" %>
</head>
<body>

  <%@include file="./includes/Navbar.jsp" %>
  
<div class="container">
    <div class="card">
        <div class="card-header bg-primary text-white">
            <h5 class="card-title">Lista de Clientes</h5>
        </div>
        <ul class="list-group list-group-flush">
            <% 
                List<UsuarioVO> listausuarios = (List<UsuarioVO>) request.getAttribute("listaUsuarios");
                for (UsuarioVO usuario : listausuarios) {
            %>       
            <li class="list-group-item">
                <div class="row">
                    <div class="col-md-2">
                        <strong>Nombre:</strong> <%= usuario.getNombre() %>
                    </div>
                    <div class="col-md-2">
                        <strong>Apellidos:</strong> <%= usuario.getApellidos() %>
                    </div>                     
                    <div class="col-md-3">
                        <strong>Email:</strong> <%= usuario.getEmail() %>
                    </div>
                    <div class="col-md-2">
                        <strong>Contraseña:</strong> <%= usuario.getClave() %>
                    </div>
                    <div class="col-md-1">
                        <strong>Baja:</strong> <%= usuario.getBaja() %>
                    </div>
                    <div class="col-md-2">
                        <div class="btn-group" role="group" aria-label="Acciones">
                            <a class="btn btn-primary me-3" href="./RutaActualizacionUsuario?idUsuario=<%= usuario.getId() %>">Actualizar</a>
                            <a class="btn btn-danger" href="#">Dar de baja</a>
                        </div>
                    </div>
                </div>
            </li>
            <% 
                }
            %>
        </ul>
    </div>
</div>

<%@include file="./includes/Footer.jsp" %>

</body>
</html>

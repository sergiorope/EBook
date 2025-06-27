<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List, java.util.Map, ModeloVO.CategoriaVO"%>
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
            <h5 class="card-title">Lista de Categorías</h5>
        </div>
        <ul class="list-group list-group-flush">
            <% 
                List<CategoriaVO> categorias = (List<CategoriaVO>) request.getAttribute("listaCategorias");
                for (CategoriaVO categoria : categorias) {
            %>       
            <li class="list-group-item">
                <div class="row">
                    <div class="col-md-8">
                        <strong>Nombre:</strong> <%= categoria.getNombre() %>
                    </div>
                    <div class="col-md-4 text-end">
                        <div class="btn-group" role="group" aria-label="Acciones">
                            <a class="btn btn-primary me-3" href="./RutaActualizacionCategoria?idCategoria=<%= categoria.getId() %>">Actualizar</a>
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

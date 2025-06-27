<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List, java.util.Map, ModeloVO.ProductoVO"%>
<!DOCTYPE html>
<html>
<head>
  <%@include file="./includes/Head.jsp" %>
</head>
<body>

  <%@include file="./includes/Navbar.jsp" %>
  
<div class="container">
    <div class="card ">
        <div class="card-header bg-primary text-white">
            <h5 class="card-title">Lista de Productos</h5>
        </div>
        <ul class="list-group list-group-flush">
            <% 
                List<ProductoVO> catalogo = (List<ProductoVO>) request.getAttribute("listaProductos");
            Map<ProductoVO, String> categporproducto = (Map<ProductoVO, String>) request.getAttribute("categporproducto");
               
                for (ProductoVO producto : catalogo) {
                	
                	String categoria = categporproducto.get(producto);
            %>       
            <li class="list-group-item">
                <div class="row">
                    <div class="col-md-2">
                        <strong>Nombre:</strong> <%= producto.getNombre() %>
                    </div>
                     <div class="col-md-2">
                        <strong>Categoría:</strong> <span th:text="${productoporctaegoria[producto]}"></span>
                    </div>
                    <div class="col-md-2">
                        <strong>Descripción:</strong> <%= producto.getDescripcion() %>
                    </div>
                    <div class="col-md-1">
                        <strong>Stock:</strong> <%= producto.getStock() %>
                    </div>
                    <div class="col-md-1">
                        <strong>Impuesto:</strong> <%= producto.getImpuesto() %>
                    </div>
                    <div class="col-md-1">
                        <strong>Baja:</strong> <%= producto.getBaja() %>
                    </div>
                    <div class="col-md-1">
                        <strong>Precio:</strong> <%= producto.getPrecio()+"€" %>
                    </div>
                    <div class="col-md-2">
                        <a class="btn btn-primary me-3" href="./RutaActualizacionProducto?idProducto=<%= producto.getId() %>">Actualizar</a>
                        <a class="btn btn-danger" href="#">Dar de baja</a>
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

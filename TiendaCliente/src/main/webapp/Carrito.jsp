<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*, ModeloVO.ProductoVO" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <%@ include file="./includes/Head.jsp" %>
    
   
    <title>Carrito de Compra</title>
     <link rel="stylesheet" href="Carrito.css">
      <style>

    footer {
      position: fixed;
      bottom: 0;
      width: 100%;
    }
  </style>
</head>
<body>

<%@ include file="./includes/Navbar.jsp" %> 
<br>

<div class="container">
    <div class="row">
        <div class="col-md-7">
            <div class="row">
                <div class="col-md-12">
                    <% HashMap<ProductoVO, Integer> carrito = (HashMap<ProductoVO, Integer>) request.getSession().getAttribute("carrito");
                       if (!carrito.isEmpty()) {
                           for (Map.Entry<ProductoVO, Integer> entry : carrito.entrySet()) {
                               ProductoVO producto = entry.getKey();
                               int cantidad = entry.getValue();
                    %>
                   <div class="card mb-3">
            <div class="row no-gutters justify-content-start"> <!-- Agregamos justify-content-start para alinear a la izquierda -->
                <div class="col-md-2 ">
    <img src="<%= producto.getImagen() %>" class="card-img" alt="<%= producto.getNombre() %>">
                </div>
                <div class="col-md-9">
                    <div class="card-body">
                        <h5 class="card-title"><%= producto.getNombre() %></h5>
                        <p class="card-text">Cantidad: <%= cantidad %></p>
                        <p class="card-text">Precio total: <%= String.format(Locale.US, "%.2f", cantidad * producto.getPrecio() * (1 + producto.getImpuesto() / 100)) %>€</p>
                    </div>
                </div>
                <div class="col-md-1 d-flex justify-content-end align-items-center">
                    <div class="btn-group me-2" role="group">
                        <a href="./rutaCarrito?action=anadir&id=<%= producto.getId() %>&precio=<%= producto.getPrecio() %>&impuesto=<%= producto.getImpuesto() %>" class="btn btn-outline-primary">+</a>
                        <a href="./rutaCarrito?action=eliminar&id=<%= producto.getId()%>&nombre=<%= producto.getNombre()%>&imagen=<%= producto.getImagen() %>&precio=<%= producto.getPrecio() %>&impuesto=<%= producto.getImpuesto() %>" class="btn btn-outline-primary">-</a>
                    </div>
                </div>
            </div>
        </div>

                    <% 
                           }
                       } else {
                    %>
                   <div class="alert alert-info d-flex justify-content-center" role="alert">
					    El carrito está vacío.
					</div>
                    <%
                       }
                    %>
                </div>
            </div>
        </div>
        
        <%if(!carrito.isEmpty()){ %>
        <div class="col-md-2">
            <div class="card h-90">
                <div class="card-body ">
                    <% if(request.getSession().getAttribute("dinerofin") != null && !carrito.isEmpty()) { %>
                        <% double precio = (double) request.getSession().getAttribute("dinerofin"); %>
                        <h5 class="card-title text-center">Total</h5>
                        <p class="card-text text-center">Total a pagar: <%= precio %>€ (IVA incluido)</p>
                        <div class="text-center mt-3">
                            <% if (!carrito.isEmpty()) {%>
                                <a href="./CompraProducto" class="btn btn-success">Comprar</a><br><br><br>
                            <%}%>
                            <a href="./catalogo" class="btn btn-primary">Volver al Catálogo</a>
                        </div>
                    <% } %>
                    <% if (request.getAttribute("error") != null) { %>
                        <div class="alert alert-danger"><%= request.getAttribute("error") %></div>
                    <% } %>
                    
                    <% } %>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="./includes/Footer.jsp" %>



</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="ModeloVO.ProductoVO, ModeloVO.ValoracionVO, java.util.List, java.util.Map" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="./includes/Head.jsp" %>
    <link rel="stylesheet" type="text/css" href="PaginaDetalle.css">
</head>
<body>

<%@ include file="./includes/Navbar.jsp" %>

<%
    ProductoVO producto = (ProductoVO) request.getAttribute("productoDetalle");
    List<ValoracionVO> listavaloracion = (List<ValoracionVO>) request.getAttribute("listavaloraciones");
    Map<ValoracionVO, String> nombreporvaloracion = (Map<ValoracionVO, String>) request.getAttribute("nombreporvaloracion");
%>

<div class="container">
    <div class="row justify-content-center mt-5">
        <div class="col-md-6">
            <div class="card">
                <img src="<%= producto.getImagen() %>" class="card-img-top" alt="Producto">
                <div class="card-body">
                    <h5 class="card-title"><%= producto.getNombre() %></h5>
                    <p class="card-text"><%= producto.getDescripcion() %></p>
                    <p class="card-text"><b>Precio:</b> <%= producto.getPrecio() + "€" %></p>
                    <a href="./Añadir?action=anadir&id=<%= producto.getId() %>&precio=<%= producto.getPrecio() %>&impuesto=<%= producto.getImpuesto() %>"
                       class="btn btn-success">Comprar</a>
                </div>
            </div>
        </div>
        
        <div class="col-md-6">
            <div class="card" style="width: 110%;">
                <div class="card-body">
                    <h5 class="card-title text-center">Reseñas</h5>
                    <hr>
                    <% if (listavaloracion != null && !listavaloracion.isEmpty()) {
                        for (ValoracionVO valoracion : listavaloracion) {
                            String nombre = nombreporvaloracion.get(valoracion);
                    %>
                    <label class="card-text"><b><%= nombre + ":" %></b> <%= valoracion.getComentario() %></label>
                    
                    <% 
                        if (valoracion.getValoracion() != 0) {
                            for (int i = 0; i < valoracion.getValoracion(); i++) { %>
                                <i class="bi bi-star-fill text-warning"></i>
                            <% }
                            int estrellasRestantes = 5-valoracion.getValoracion();
                            for (int i = 0; i < estrellasRestantes; i++) { %>
                                <i class="bi bi-star"></i>
                            <% }
                            // Añade un salto de línea para separar las valoraciones
                            out.println("<br><br>");
                        } 
                  } 
                        } else { %>
                        <p>No hay valoraciones para este producto.</p>
                    <% } %>
                </div>
            </div>
        </div>
    </div>
</div>


<%@ include file="./includes/Footer.jsp" %>

</body>
</html>

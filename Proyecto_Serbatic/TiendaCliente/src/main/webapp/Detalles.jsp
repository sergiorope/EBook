<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.List, java.util.Map, ModeloVO.DetalleVO, ModeloVO.ProductoVO" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="./includes/Head.jsp" %>
   <link rel="stylesheet" type="text/css" href="LineasDetalles.css">
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
    <div class="container mt-5">
        <% 
        int cont=0;
            String idPedidoParam = request.getParameter("idPedido");
            int idPedido = 0; // Default value in case parsing fails or parameter is empty
            if (idPedidoParam != null && !idPedidoParam.isEmpty()) {
                try {
                    idPedido = Integer.parseInt(idPedidoParam);
                } catch (NumberFormatException e) {
                    // Handle parsing error (optional)
                }
            }
            Map<Integer, List<DetalleVO>> detallesPorPedido = (Map<Integer, List<DetalleVO>>) request.getSession().getAttribute("detallesPorPedido");
            Map<DetalleVO, ProductoVO> nombrePorDetalle = (Map<DetalleVO, ProductoVO>) request.getSession().getAttribute("listaProducto");
            Map<Integer, Boolean> valoracionporproducto = (Map<Integer, Boolean>) request.getSession().getAttribute("valoracionporproducto");
            String estado = request.getParameter("estado");

            List<DetalleVO> detalles = detallesPorPedido.get(idPedido);
            
          
            if (detalles != null) {
                for (DetalleVO detalle : detalles) { 
                	cont++;
                    ProductoVO producto = nombrePorDetalle.get(detalle);
        %>
        
        <% Boolean valorado = valoracionporproducto.get(producto.getId()); %>
        
        <div class="card mb-3">
            <div class="card-body">
                <h5 class="card-title"><%= producto != null ? producto.getNombre() : "" %></h5>
                <label><strong>Unidades:</strong> <%= detalle.getUnidades() %></label>
                <label><strong>Precio por unidad:</strong> <%= detalle.getPreciounidad() %></label>
                <label><strong>Impuesto:</strong> <%= detalle.getImpuesto() %></label>
                <label><strong>Total:</strong> <%= detalle.getTotal()+"€" %></label>
                <br><br>
                
                <%if(detalles.size() > 1 ){ %>
                 <% if (estado.equals("PE")) { %>
                 <a class="btn btn-outline-danger btn-sm" href="./CancelarLinea?idDetalle=<%= detalle.getId() %>">Cancelar Línea</a>
                 <% } %>
                 <% } %>
                 
               <% if (valorado != null && valorado && estado != null && estado.equals("E")) { %>

			       <a class="btn btn-primary btn-sm" href="./RutaPublicacionValoracion?idProducto=<%= producto.getId() %> ">Escribir Reseña</a>
			      

			       <% } %>
                <div class="mt-3">                             
                </div>
            </div>
        </div>
        <% } %>
        <% } %>

    </div>
    
    <%@ include file="./includes/Footer.jsp" %>

<script>
    document.addEventListener("DOMContentLoaded", function() {
        var stars = document.querySelectorAll('.star');

        stars.forEach(function(star) {
            star.addEventListener('mouseover', function() {
                var rating = this.getAttribute('data-rating');
                markStars(rating);
            });

            star.addEventListener('mouseleave', function() {
                var rating = document.getElementById('rating').value;
                resetStars(rating);
            });

            star.addEventListener('click', function() {
                var rating = this.getAttribute('data-rating');
                document.getElementById('rating').value = rating;
            });
        });

        function markStars(rating) {
            stars.forEach(function(star) {
                if (star.getAttribute('data-rating') <= rating) {
                    star.style.color = 'gold';
                }
            });
        }

        function resetStars(rating) {
            stars.forEach(function(star) {
                if (star.getAttribute('data-rating') > rating) {
                    star.style.color = '';
                }
            });
        }
    });
</script>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.List, ModeloVO.PedidoVO" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="./includes/Head.jsp" %>
    <link rel="stylesheet" href="MisPedidos.css">
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
    <div class="container mr-2">
        <% 
            List<PedidoVO> pedidos = (List<PedidoVO>) request.getSession().getAttribute("pedidos");
            List<PedidoVO> pedidosFiltrados = (List<PedidoVO>) request.getSession().getAttribute("pedidosFiltrados");

            if (pedidosFiltrados != null && !pedidosFiltrados.isEmpty()) {
                for (PedidoVO pedido : pedidosFiltrados) {
        %>
                    <div class="pedido">
                        <h1>Pedido:</h1>
                        <a href="./CancelarPedido?idPedidoCancelar=<%= pedido.getId() %>" class="btn btn-outline-danger">Cancelar pedido</a>
                        <hr>
                        <label><b>Fecha:</b> <%= pedido.getFecha() %></label>
                        <label><b>Método de pago:</b>  <%= pedido.getMetodopago() %></label>
                        <% if (pedido.getNumfactura() != null) { %>
                            <label><b>Número de Factura:</b>  <%= pedido.getNumfactura() %></label>
                        <% } %>
                         <label><b>Estado:</b>  <%= pedido.getEstado() %></label>
                        <% if (pedido.getNumfactura() != null) { %>
                            <label><b>Total pedido:</b> <%= pedido.getTotal() %>€</label>
                        <% } %>
                       
                        <div style="text-align: right;">
                            <a class="btn btn-primary me-3" href="./RutaDetalles?idPedido=<%= pedido.getId() %>&numfact=<%=pedido.getNumfactura() %>&estado=<%=pedido.getEstado() %>">Ver líneas</a>
                            
                          <% if(!pedido.getEstado().equals("PE") && !pedido.getEstado().equals("C")) { %>
                                <a class="btn btn-danger" href="./FacturaPedidos?idPedidoFactura=<%=pedido.getId() %>">Generar Factura</a>
                            <% } %>
                        </div>
                    </div>
        <% 
                } 
            } else if (pedidos != null && !pedidos.isEmpty()) {
                for (PedidoVO pedido : pedidos) {
        %>
                    <div class="pedido">
                        <h1>Pedido:</h1>
                        <%if (pedido.getEstado().equals("PE")){ %>
                        <a href="./CancelarPedido?idPedidoCancelar=<%= pedido.getId() %>" class="btn btn-outline-danger">Cancelar pedido</a>
                        <%} %>
                        <hr>
                        <label><b>Fecha:</b> <%= pedido.getFecha() %></label>
                        <label><b>Método de pago:</b>  <%= pedido.getMetodopago() %></label>
                        <% if (pedido.getNumfactura() != null) { %>
                            <label><b>Número de Factura:</b>  <%= pedido.getNumfactura() %></label>
                        <% } %>
                        <% if (pedido.getNumfactura() != null) { %>
                            <label><b>Total pedido:</b> <%= String.format("%.2f", pedido.getTotal()) %>€</label>
                        <% } %>
                        <label><b>Estado:</b>  <%= pedido.getEstado() %></label>
                        <div style="text-align: right;">
                             <a class="btn btn-primary me-3" href="./RutaDetalles?idPedido=<%= pedido.getId() %>&numfact=<%=pedido.getNumfactura() %>&estado=<%=pedido.getEstado() %>">Ver líneas</a>
                            
                            <% if(!pedido.getEstado().equals("PE") && !pedido.getEstado().equals("C") && !pedido.getEstado().equals("PC")) { %>
                                <a class="btn btn-danger" href="./FacturaPedidos?idPedidoFactura=<%=pedido.getId() %>">Generar Factura</a>
                            <% } %>
                        </div>
                    </div>
        <% 
                }
            } else {
        %>
            <h2>Aún no tienes pedidos realizados</h2>
        <% 
            } 
        %>
    </div>
    
    
</body>
</html>

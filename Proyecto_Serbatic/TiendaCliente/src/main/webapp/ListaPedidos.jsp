<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List, java.util.Map, ModeloVO.PedidoVO"%>
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
            <h5 class="card-title">Lista de Pedidos</h5>
        </div>
        <ul class="list-group list-group-flush">
            <% 
                List<PedidoVO> pedidos = (List<PedidoVO>) request.getAttribute("listaPedidos");
                Map<PedidoVO, String> nombreporpedido = (Map<PedidoVO, String>) request.getAttribute("nombreporpedido");

                for (PedidoVO pedido : pedidos) {
                    String nombreusuario = nombreporpedido.get(pedido);
            %>       
            <li class="list-group-item">
                <div class="row">
                    <div class="col-md-2">
                        <strong>Cliente:</strong> <%= nombreusuario %>
                    </div>
                    <div class="col-md-2">
                        <strong>Fecha:</strong> <%= pedido.getFecha() %>
                    </div>
                    <div class="col-md-2">
                        <strong>Método de pago:</strong> <%= pedido.getMetodopago() %>
                    </div>
                    <% if (pedido.getNumfactura() != null) { %>
                        <div class="col-md-2">
                            <strong>Número Factura:</strong> <%= pedido.getNumfactura() %>
                        </div>
                    <% } %>
                    <div class="col-md-1">
                        <strong>Estado:</strong> <%= pedido.getEstado() %>
                    </div>
                    <% if (pedido.getNumfactura() != null) { %>
                        <div class="col-md-1">
                            <strong>Total:</strong> <%= String.format("%.2f", pedido.getTotal()) %>€
                        </div>
                    <% } %>
                    <div class="col-md-2">
                        <% if (pedido.getEstado().equals("PE")) { %>
                            <a class="btn btn-primary me-3" href="./ConfirmarEnvio?idPedido=<%= pedido.getId() %>">Confirmar envío</a>
                        <% } %>
                        <% if (pedido.getEstado().equals("PC")) { %>
                            <a class="btn btn-danger" href="#">Confirmar cancelación</a>
                        <% } %>
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

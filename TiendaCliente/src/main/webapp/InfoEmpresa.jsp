<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import=" Servicio.ConfiguracionServicio"%>
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
String cif = ConfiguracionServicio.ObtenerValor("cif");
		String direccion = ConfiguracionServicio.ObtenerValor("direccion");
		String provincia = ConfiguracionServicio.ObtenerValor("provincia");
		String telefono = ConfiguracionServicio.ObtenerValor("telefono");
%>

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card">
                <div class="card-header bg-primary text-center text-white">
                    <h5>Información de la Empresa</h5>
                </div>
                <div class="card-body">
                    <div class="info-item">
                        <i class="fas fa-id-card me-2"></i>
                        <strong>CIF:</strong> <%= cif %>
                    </div>
                    <div class="info-item">
                        <i class="fas fa-map-marker-alt me-2"></i>
                        <strong>Dirección:</strong> <%= direccion %>
                    </div>
                    <div class="info-item">
                        <i class="fas fa-map me-2"></i>
                        <strong>Provincia:</strong> <%= provincia %>
                    </div>
                    <div class="info-item">
                        <i class="fas fa-phone me-2"></i>
                        <strong>Teléfono:</strong> <%= telefono %>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="./includes/Footer.jsp" %> 
</body>
</html>
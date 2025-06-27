<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import=" ModeloVO.UsuarioVO"%>
<!DOCTYPE html>
<html lang="en">
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

<% UsuarioVO usuario = (UsuarioVO) request.getAttribute("datosUsuario"); %>

<div class="container mt-5">
  <h2>Perfil de <%=request.getSession().getAttribute("nombre") %></h2>
  <form id="formulario"  method="POST" action="./CamposUsuario">
    <div class="form-group">
      <label for="nombre">Nombre:</label>
      <input type="text" class="form-control" name="nombref" id="nombre" value="<%=usuario.getNombre() %>"><br>
    </div>
    <div class="form-group">
      <label for="nombre">Apellidos:</label>
      <input type="text" class="form-control" name="apellidosf" id="apellidos" value="<%=usuario.getApellidos() %>"><br>
    </div>
    <div class="form-group">
      <label for="email">Correo Electrónico:</label> 
      <input type="email" class="form-control" name="emailf" id="email" value="<%=usuario.getEmail() %>"><br>
    </div>
    
  <br>
    <button type="submit" class="btn btn-primary">Enviar</button>
  </form>
</div>

<!-- Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<script>
  $(document).ready(function() {
    $('.btn-enable').click(function() {
      var targetId = $(this).data('target');
      var targetInput = $('#' + targetId);
      targetInput.prop('readonly', function(i, v) { return !v; });
      targetInput.focus(); 
    });
  });
</script>

<%@include file="./includes/Footer.jsp" %>
</body>
</html>
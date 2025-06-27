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

<div class="container mt-5">
  <div class="row justify-content-center">
    <div class="col-md-8">
      <div class="card">
        <div class="card-body">
          <h5 class="card-title">Cambio de Contraseña</h5>
          <p class="card-text">Por favor, después del cambio de contraseña, cierre sesión y vuelva a entrar para que los cambios se efectúen correctamente.</p>

          <% if (request.getAttribute("CoConAnti") == null) { %>
            <form id="formularioAntig" method="POST" action="./ComproAntig">
              <div class="form-group">
                <label for="claveAntigua">Contraseña antigua</label>
                <input type="password" class="form-control" name="claveAntigua" id="claveAntigua" required>
              </div>
              <br>
              <button type="submit" class="btn btn-primary">Enviar</button>
            </form>
          <% } %>
          
          <% if (request.getAttribute("ErrorConAnti") != null) { %>
            <div class="alert alert-danger mt-3" role="alert">
              <%= request.getAttribute("ErrorConAnti") %>
            </div>
          <% } %>

          <% if (request.getAttribute("CoConAnti") != null) { %>
            <form id="formulario" method="POST" action="./CambioPass">
              <div class="form-group">
                <label for="claveNueva">Nueva contraseña</label>
                <input type="password" class="form-control" name="claveNueva" id="claveNueva" required>
              </div>
              <div class="form-group">
                <label for="claveNuevaRep">Repita la nueva contraseña</label>
                <input type="password" class="form-control" name="claveNuevaRep" id="claveNuevaRep" required>
              </div>
              <button type="submit" class="btn btn-primary">Enviar</button>
            </form>
          <% } %>

          <% if (request.getAttribute("ErrorCoinci") != null) { %>
            <div class="alert alert-danger mt-3" role="alert">
              <%= request.getAttribute("ErrorCoinci") %>
            </div>
          <% } %>

          <% if (request.getAttribute("Completada") != null) { %>
            <div class="alert alert-success mt-3" role="alert">
              <%= request.getAttribute("Completada") %>
            </div>
          <% } %>
          
        </div>
      </div>
    </div>
  </div>
</div>

<%@include file="./includes/Footer.jsp" %>

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
</body>
</html>

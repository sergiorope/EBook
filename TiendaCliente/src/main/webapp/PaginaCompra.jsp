<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Pasarela de Pago</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
</head>
<body>
  <div class="container mt-5">
    <h1 class="mb-4">Pasarela de Pago</h1>
    <form id="paymentForm" method="post" action="./Pago" onsubmit="return validarPago()">
      <div class="form-group">
        <label for="metodoPago">Selecciona el método de pago:</label>
        <select class="form-control" id="metodoPago" onchange="mostrarFormulario()">
          <option value="tarjeta">Tarjeta de Crédito</option>
          <option value="paypal">PayPal</option>
        </select>
      </div>
      
      <div id="formularioTarjeta" style="display:none;">
        <input type="hidden" name="tipoPago" id="tipoPago" value="tarjeta">
        <h2 class="mb-3">Datos de la Tarjeta de Crédito <i class="far fa-credit-card"></i></h2>
        <div class="form-group">
          <label for="numeroTarjeta">Número de Tarjeta:</label>
          <input type="text" class="form-control" id="numeroTarjeta" placeholder="Número de Tarjeta">
          <small class="form-text text-muted">Formato: XXXX-XXXX-XXXX-XXXX</small>
          <div id="numeroTarjetaError" class="invalid-feedback"></div>
        </div>
        <div class="form-row">
          <div class="col-md-6">
            <label for="cvc">CVC:</label>
            <input type="text" class="form-control" id="cvc" placeholder="CVC">
            <div id="cvcError" class="invalid-feedback"></div>
          </div>
          <div class="col-md-6">
            <label for="fechaCaducidad">Fecha de Caducidad:</label>
            <input type="text" class="form-control" id="fechaCaducidad" placeholder="MM/YY">
            <div id="fechaCaducidadError" class="invalid-feedback"></div>
          </div>
        </div>
        <div class="form-group mt-3">
          <label for="nombreTarjeta">Nombre en la Tarjeta:</label>
          <input type="text" class="form-control" id="nombreTarjeta" placeholder="Nombre en la Tarjeta">
          <div id="nombreTarjetaError" class="invalid-feedback"></div>
        </div>
      </div>
      
      <div id="formularioPayPal" style="display:none;">
        <input type="hidden" name="tipoPago" id="tipoPago" value="paypal">
        <h2 class="mb-3">Datos de PayPal <i class="fab fa-paypal"></i></h2>
        <div class="form-group">
          <label for="emailPayPal">Correo Electrónico de PayPal:</label>
          <input type="email" class="form-control" id="emailPayPal" placeholder="Correo Electrónico">
          <div id="emailPayPalError" class="invalid-feedback"></div>
        </div>
      </div>
      
      <button type="submit" class="btn btn-lg btn-primary btn-block mt-4">Pagar <i class="fas fa-arrow-right"></i></button>
    </form>
  </div>
  
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
  <script>
    function mostrarFormulario() {
      var metodoPago = $("#metodoPago").val();
      if (metodoPago === "tarjeta") {
        $("#formularioTarjeta").show();
        $("#formularioPayPal").hide();
      } else if (metodoPago === "paypal") {
        $("#formularioTarjeta").hide();
        $("#formularioPayPal").show();
      }
    }

    function validarPago() {
      var metodoPago = $("#metodoPago").val();
      if (metodoPago === "tarjeta") {
        var numeroTarjeta = $("#numeroTarjeta").val();
        var cvc = $("#cvc").val();
        var fechaCaducidad = $("#fechaCaducidad").val();
        var nombreTarjeta = $("#nombreTarjeta").val();

        if (!numeroTarjeta) {
          $("#numeroTarjetaError").text("Por favor introduce el número de tarjeta.");
          return false;
        } else {
          $("#numeroTarjetaError").text("");
        }

        if (!cvc) {
          $("#cvcError").text("Por favor introduce el CVC.");
          return false;
        } else {
          $("#cvcError").text("");
        }

        if (!fechaCaducidad) {
          $("#fechaCaducidadError").text("Por favor introduce la fecha de caducidad.");
          return false;
        } else {
          $("#fechaCaducidadError").text("");
        }

        if (!nombreTarjeta) {
          $("#nombreTarjetaError").text("Por favor introduce el nombre en la tarjeta.");
          return false;
        } else {
          $("#nombreTarjetaError").text("");
        }
      } else if (metodoPago === "paypal") {
        var emailPayPal = $("#emailPayPal").val();
        if (!emailPayPal) {
          $("#emailPayPalError").text("Por favor introduce tu correo electrónico de PayPal.");
          return false;
        } else {
          $("#emailPayPalError").text("");
        }
      }
      return true;
    }
  </script>
</body>
</html>

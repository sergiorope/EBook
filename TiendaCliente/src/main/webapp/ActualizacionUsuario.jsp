<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List, java.util.Map, ModeloVO.UsuarioVO"%>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="./includes/Head.jsp" %>
</head>
<body>
    <%@ include file="./includes/Navbar.jsp" %>
    <% 
        UsuarioVO usuario = (UsuarioVO) request.getAttribute("usuarioActualizar");
    %>

    <div class="container mt-5">
        <div class="card">
            <div class="card-header bg-primary text-white">
                <h2 class="mb-0">Actualización de Cliente</h2>
            </div>
            <div class="card-body">
                <form action="./ActualizarUsuario?idUsuario=<%=usuario.getId() %>" method="POST">
                    <div class="form-group">
                        <label for="nombre">Nombre:</label>
                        <input type="text" value ="<%=usuario.getNombre() %>" class="form-control" id="nombre" name="nombre" required>
                    </div>
                    <div class="form-group">
                        <label for="apellidos">Apellidos:</label>
                        <textarea class="form-control" id="apellidos" name="apellidos" rows="3" required><%=usuario.getApellidos() %></textarea>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="email">Email:</label>
                            <input type="email" value ="<%=usuario.getEmail() %>" class="form-control" id="email" name="email" required>
                        </div>
                        <div class="form-group col-md-6">
                            <label for="clave">Contraseña:</label>
                            <input type="password" value ="<%=usuario.getClave() %>" class="form-control" id="clave" name="clave" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="baja">Baja:</label>
                        <input type="number" value ="<%=usuario.getBaja() %>" class="form-control" id="baja" name="baja" required>
                    </div>
                    <br>
                    <button type="submit" class="btn btn-primary">Guardar</button>
                </form>
            </div>
        </div>
    </div>

    <%@ include file="./includes/Footer.jsp" %>

</body>
</html>

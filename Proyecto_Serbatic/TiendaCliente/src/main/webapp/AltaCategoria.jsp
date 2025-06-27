<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="./includes/Head.jsp" %>
</head>
<body>
    <%@ include file="./includes/Navbar.jsp" %>

    <div class="container mt-5">
        <div class="card">
            <div class="card-header bg-primary text-white">
                <h2 class="mb-0">Alta de Categoria</h2>
            </div>
            <div class="card-body">
                <form action="./AltaCategoria" method="POST">                 
                    <div class="form-group">
                        <label for="nombre">Nombre categoría:</label>
                        <input type="text" class="form-control" id="nombre" name="nombre" required>
                    </div>
                    <button type="submit" class="btn btn-primary">Guardar</button>
                </form>
            </div>
        </div>
    </div>

    <%@ include file="./includes/Footer.jsp" %>
</body>
</html>

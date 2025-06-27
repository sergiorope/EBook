<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.List, ModeloVO.CategoriaVO" %>
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
                <h2 class="mb-0">Alta de Producto</h2>
            </div>
            <div class="card-body">
                <form action="./AltaProducto" method="POST">
                    <div class="form-group">
                        <label for="categoria">Categoría:</label>
                        <select class="form-control" id="categoria" name="categoria" required>
                            <% 
                           
                            List<CategoriaVO> categorias = (List<CategoriaVO>) request.getSession().getAttribute("categorias");
                            
                            for (CategoriaVO categoria : categorias) {
                            %>
                                <option value="<%= categoria.getId() %>"><%= categoria.getNombre() %></option>
                            <% 
                            } 
                            %>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="nombre">Nombre:</label>
                        <input type="text" class="form-control" id="nombre" name="nombre" required>
                    </div>
                    <div class="form-group">
                        <label for="descripcion">Descripción:</label>
                        <textarea class="form-control" id="descripcion" name="descripcion" rows="3" required></textarea>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="precio">Precio:</label>
                            <div class="input-group">
                                <div class="input-group-prepend">
                                    <span class="input-group-text">$</span>
                                </div>
                                <input type="number" step="0.01" class="form-control" id="precio" name="precio" required>
                            </div>
                        </div>
                        <div class="form-group col-md-6">
                            <label for="impuesto">Impuesto:</label>
                            <div class="input-group">
                                <input type="number" step="0.01" class="form-control" id="impuesto" name="impuesto" required>
                                <div class="input-group-append">
                                    <span class="input-group-text">%</span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="stock">Stock:</label>
                        <input type="number" class="form-control" id="stock" name="stock" required>
                    </div>
                    <div class="form-group">
                        <label for="baja">Estado de Baja (0 o 1):</label>
                        <input type="number" class="form-control" id="baja" name="baja" min="0" max="1" required>
                    </div>
                    <div class="form-group mt-3">
                        <label for="imagen">URL de la Imagen:</label>
                        <input type="text" class="form-control" id="imagen" name="imagen">
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
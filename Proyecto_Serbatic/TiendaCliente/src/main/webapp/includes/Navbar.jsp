<%@ page language="java" pageEncoding="UTF-8" import="java.util.Locale, java.util.ResourceBundle, java.util.List, java.util.ArrayList, ModeloVO.ProductoVO, ModeloVO.OpcionMenuVO" %>

<%
    Locale locale = (Locale) request.getSession().getAttribute("locale");
    ResourceBundle rb = null;
    if (locale != null) {
        rb = ResourceBundle.getBundle("idioma", locale);
    }
    Integer numCarrito = (Integer) session.getAttribute("numcarrito");
    List<OpcionMenuVO> opciones = (List<OpcionMenuVO>) request.getSession().getAttribute("opcionesMenu");
%>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand ms-2" href="./">E-BOOK</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse justify-content-between" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="./CarritoPag">
                    <i class="bi bi-cart-fill"></i>
                    <% if (numCarrito != null && numCarrito > 0) { %>
                        <span class="badge bg-danger rounded-circle align-text-top ms-n1" style="margin-left: -10px; margin-top: -5px; font-size: 0.65rem;"><%= numCarrito %></span>
                    <% } %>
                </a>
            </li>
            <% if (opciones != null) {
                for (OpcionMenuVO opcion : opciones) { %>
                    <li class="nav-item">
                        <a class="nav-link" href="<%= opcion.getUrl() %>"><%= opcion.getNombre() %></a>
                    </li>
                <% }
            } %>
        </ul>
    </div>
    <div class="d-flex">
        <% if(request.getSession().getAttribute("nombre") != null) { %>
            <span class="navbar-text me-3"><%= rb != null ? rb.getString("bienvenido") + request.getSession().getAttribute("nombre") : "" %></span>
            <% if(request.getSession().getAttribute("nombre") != null) { %>
                <a href="./urlInfo" class="nav-link me-2"><i class="bi bi-person fs-4 text-white"></i></a> <!-- Agrega margen a la derecha -->
            <% } %>
            <a class="btn btn-outline-light me-2" href="./Logout"><%= rb != null ? rb.getString("cerrar") : "" %></a>
        <% } else { %>
            <a class="btn btn-outline-light me-2" href="./RutaLogin"><%= rb != null ? rb.getString("inicio") : "" %></a>
        <% } %>
        <form action="./i18Servlet" method="get" class="d-flex me-2"> <!-- Agrega margen a la derecha -->
            <select name="lang" class="form-select form-select-sm" onchange="this.form.submit()">
                <option value="es" <%= locale != null && locale.getLanguage().equals("es") ? "selected" : "" %>>Español</option>
                <option value="en" <%= locale != null && locale.getLanguage().equals("en") ? "selected" : "" %>>English</option>
            </select>
        </form>
    </div>
</nav>

<style>
    /* Estilos para el efecto al pasar el cursor sobre los enlaces */
    .navbar-nav .nav-link:hover {
        color: #ffc107; /* Cambia el color al pasar el cursor */
        transform: scale(1.05); /* Aumenta ligeramente el tamaño */
        transition: color 0.3s ease, transform 0.3s ease; /* Agrega una transición suave */
    }
</style>

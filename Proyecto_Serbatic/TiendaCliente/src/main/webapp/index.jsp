<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List, java.util.Map, ModeloVO.ProductoVO,  ModeloVO.CategoriaVO, ModeloVO.ValoracionVO" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="./includes/Head.jsp" %>
    <link rel="stylesheet" type="text/css" href="index.css">
</head>
<body>
    <%@include file="./includes/Navbar.jsp" %> 
 		
 		
 	    <% 
    ProductoVO productoRecomendado = (ProductoVO) request.getAttribute("ProductoRecomendado");
 	   if(productoRecomendado != null && productoRecomendado.getNombre() != null) {
    %>
    <div class="container-fluid bg-primary text-white text-center py-2">
        <a href="./RutaDetalle?id=<%= productoRecomendado.getId() %>" class="text-white text-decoration-none"><strong>Descubre un mundo de aventuras, misterios y emociones entre las páginas de <%= productoRecomendado.getNombre() %>!</strong></a>
    </div>
    <% } else { %>
    <div class="container-fluid bg-primary text-white text-center py-2">
        <a href="./RutaDetalle?id=7" class="text-white text-decoration-none"><strong><%= rb.getString("mensaje") %></strong></a>
    </div>
    <% } %>
 
    
<% if (request.getAttribute("errorStock") != null) { %>
    <div class="alert alert-danger text-center" role="alert">
        <%= request.getAttribute("errorStock") %>
    </div>
<% } %>

   <div class="container mt-4">
    <div class="row">
        <div class="col-md-6 offset-md-3"> 
            <form action="./CategoriasFiltrar" method="get" class="mb-3">
                <div class="input-group">
                    <label for="categoria" class="input-group-text"><%= rb.getString("filtro.categoria") %>:</label>
                    <select class="form-select" id="categoria" name="categoria">
                        <option value="Todo"><%= rb.getString("todo") %></option>
                        <% 
                        Integer seleccionada = (Integer) request.getAttribute("seleccionada");
                        List<CategoriaVO> categorias = (List<CategoriaVO>) request.getSession().getAttribute("categorias");
                        for (CategoriaVO categoria : categorias) {
                            
                            if(seleccionada != null) {
                                if(categoria.getId() == seleccionada) {
                        %>
                                    <option value="<%= categoria.getId() %>" selected><%= categoria.getNombre() %></option>
                        <%      
                                } else {
                        %>
                                    <option value="<%= categoria.getId() %>"><%= categoria.getNombre() %></option>
                        <%          
                                }   
                            }
                            else {
                        %>
                                <option value="<%= categoria.getId() %>"><%= categoria.getNombre() %></option>
                        <%  
                            }
                        }
                        %>
                    </select>
                    <button type="submit" class="btn btn-primary"><%= rb.getString("terminar") %></button>
                </div>
            </form>
        </div>
    </div>
</div>

<div class="container mt-4">
    <div class="row row-cols-1 row-cols-md-5 g-6">
        <% 
        List<ProductoVO> catalogo = (List<ProductoVO>) request.getAttribute("catalogo");
        List<ProductoVO> catalogoCateg = (List<ProductoVO>) request.getAttribute("ListaCategorias");
        Map<ProductoVO, Double> listavaloraciones = (Map<ProductoVO, Double>) request.getAttribute("valoraciones");

        if (catalogoCateg != null) {
            for (ProductoVO producto : catalogoCateg) {
            	
            	
            	
        %>       
        <div class="col mb-5">
            <div class="card h-100 shadow">
                <img src="<%= producto.getImagen() %>" class="card-img-top img-fluid" alt="...">
                <div class="card-body">
                    <h5 class="card-title"><%= producto.getNombre() %></h5>
                    <p class="card-text"><%= producto.getPrecio()+"€" %></p>
                     <h6>Valoraciones:</h6>
	                    <div class="rating">
						 <% 
						    if(listavaloraciones != null){
				            	double valoracionProducto = listavaloraciones.get(producto);
						    if (valoracionProducto != 0) {
						        int valoracionEntera = (int) valoracionProducto;
						        double fraccionParte = valoracionProducto - valoracionEntera;
						        for (int i = 0; i < valoracionEntera; i++) { %>
						            <i class="bi bi-star-fill text-warning"></i>
						        <% }
						        // Muestra la media estrella si es necesario
						        if (fraccionParte >= 0.25 && fraccionParte < 0.75) {
						        	 valoracionEntera=valoracionEntera + 1;%>
						            <i class="bi bi-star-half text-warning"></i>
						           
						        <% }
						        int estrellasRestantes = 5 - valoracionEntera - (fraccionParte >= 0.75 ? 1 : 0);
						        for (int i = 0; i < estrellasRestantes; i++) { %>
					            <i class="bi bi-star"></i>
					        <% }
						    } else { %>
						        <p>No hay valoraciones para este producto.</p>
						    <% } 
						    } %>
						</div>
                    <br>
                    <a href="./RutaDetalle?id=<%= producto.getId() %>" class="btn btn-primary"><%= rb.getString("ver.detalles") %></a>
                    <a href="./Añadir?action=anadir&id=<%= producto.getId() %>&precio=<%= producto.getPrecio() %>&impuesto=<%= producto.getImpuesto() %>" class="btn btn-success"><%= rb.getString("comprar") %></a>
                </div>
            </div>
        </div>
        <% 
            }
        }else if(catalogo != null) {
         
          for (ProductoVO producto : catalogo) {
            	double valoracionProducto = listavaloraciones.get(producto);
            	
            	if(producto.getBaja() == 1){
        %>
        <div class="col mb-5">
            <div class="card h-100 shadow">
                <img src="<%= producto.getImagen() %>" class="card-img-top img-fluid zoom-img" alt="...">

                <div class="card-body">
                    <h5 class="card-title"><%= producto.getNombre() %></h5>
                    <p class="card-text"><%= producto.getPrecio()+"€"  %></p>
                  
                    <h6><%= rb.getString("valoracion") %></h6>
	                    <div class="rating">
						    <% 
								if (valoracionProducto != 0) {
								    int valoracionEntera = (int) valoracionProducto;
								    double fraccionParte = valoracionProducto - valoracionEntera;
								    for (int i = 0; i < valoracionEntera; i++) { %>
								        <i class="bi bi-star-fill text-warning"></i>
								    <% }
								    if (fraccionParte >= 0.25 && fraccionParte < 0.75) { 
								     valoracionEntera=valoracionEntera +1;%>
								        <i class="bi bi-star-half text-warning"></i>
								      
								    <% }
								    int estrellasRestantes = 5 - valoracionEntera;
								    for (int i = 0; i < estrellasRestantes; i++) { %>
								        <i class="bi bi-star"></i>
								    <% }
								} else { %>
								    <p>No hay valoraciones para este producto.</p>
								<% } %>
						</div>
                    <br>
                    <a href="./RutaDetalle?id=<%= producto.getId() %>&nombre=<%= producto.getNombre() %>&descripcion=<%= producto.getDescripcion() %>&precio=<%= producto.getPrecio() %>&imagen=<%= producto.getImagen() %>" class="btn btn-primary"><%= rb.getString("ver.detalles") %></a>
                    <a href="./Añadir?action=anadir&id=<%= producto.getId() %>&precio=<%= producto.getPrecio() %>&impuesto=<%= producto.getImpuesto() %>" class="btn btn-success"><%= rb.getString("comprar") %></a>
                </div>
            </div>
        </div>
        <% 
            }
        }
        %>
    </div>
</div>

<br><br>





<% } %>

<br><br>
<%@include file="./includes/Footer.jsp" %>




</body>
</html>

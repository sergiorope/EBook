<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="./includes/Head.jsp" %>
   <link rel="stylesheet" type="text/css" href="LineasDetalles.css">
    <style>

    footer {
      position: fixed;
      bottom: 0;
      width: 100%;
    }
  </style>
</head>
<body>
<%@ include file="./includes/Navbar.jsp" %>

<%ProductoVO producto = (ProductoVO)request.getAttribute("producto"); %>

<div class="ms-3">
<h2>Valoración de  <%= producto.getNombre() %></h2>
 <form id="ratingForm" action="./ValoracionResena?idproducto=<%= producto.getId() %>" method="POST">
            <div class="star-rating">
                <span class="star" data-rating="1">&#9733;</span>
                <span class="star" data-rating="2">&#9733;</span>
                <span class="star" data-rating="3">&#9733;</span>
                <span class="star" data-rating="4">&#9733;</span>
                <span class="star" data-rating="5">&#9733;</span>
            </div>
            <input type="hidden" id="rating" name="rating">
           <div class="form-group">
                <label for="comentario">Escribe tu reseña:</label>
                <textarea class="form-control" id="comentario" name="comentario" rows="3" style="max-width: 25%;"></textarea>
            </div>
            <br>
            <button type="submit" class="btn btn-primary">Publicar Reseña</button>   
        </form>
        
        </div>
    

    <%@ include file="./includes/Footer.jsp" %>
    
    
    <script>
    document.addEventListener("DOMContentLoaded", function() {
        var stars = document.querySelectorAll('.star');

        stars.forEach(function(star) {
            star.addEventListener('mouseover', function() {
                var rating = this.getAttribute('data-rating');
                markStars(rating);
            });

            star.addEventListener('mouseleave', function() {
                var rating = document.getElementById('rating').value;
                resetStars(rating);
            });

            star.addEventListener('click', function() {
                var rating = this.getAttribute('data-rating');
                document.getElementById('rating').value = rating;
            });
        });

        function markStars(rating) {
            stars.forEach(function(star) {
                if (star.getAttribute('data-rating') <= rating) {
                    star.style.color = 'gold';
                }
            });
        }

        function resetStars(rating) {
            stars.forEach(function(star) {
                if (star.getAttribute('data-rating') > rating) {
                    star.style.color = '';
                }
            });
        }
    });
</script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="models.Product" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es" data-bs-theme="dark">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Comprar - Tienda de CDs</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
    <jsp:include page="menu.jsp" />
    <h1 class="text-center">Products</h1>
    <div class="container">
        <div class="row">    
            <c:forEach var="product" items="${products}">
                <div class="col-md-4 mt-5">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">${product.getName()}</h5>
                            <p class="card-text">Precio: ${product.getPrice()}</p>
                            <form action="products" method="post">
                                <input type="hidden" name="productName" value="${product.getName()}">
                                <div class="input-group mb-3">
                                    <span class="input-group-text">Cantidad</span>
                                    <input type="number" name="quantity" id="quantity-${product.getName()}" class="form-control" value="1" min="1">
                                    <button type="button" class="btn btn-secondary down">-</button>
                                    <button type="button" class="btn btn-secondary up">+</button>
                                </div>
                                <button type="submit" class="btn btn-primary">Añadir al carrito</button>
                            </form>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="js/cds.js"> </script>
</body>
</html>


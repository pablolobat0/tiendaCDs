<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es" data-bs-theme="dark">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lista de Productos</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
    <jsp:include page="menu.jsp" />
    <div class="container">
        <h1 class="mt-5 mb-3">Lista de Productos</h1>
        <div class="row">
            <div class="col-md-6">
                <ul class="list-group">
                    <c:forEach var="entry" items="${products}">
                        <li class="list-group-item d-flex justify-content-between align-items-center">
                            ${entry.key.getName()}
                            <span class="badge bg-primary rounded-pill">
                                ${entry.value} x ${entry.key.getPrice()}€
                            </span>
                        </li>
                    </c:forEach>
                </ul>
            </div>
            <div class="col-md-6">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Total</h5>
                        <p class="card-text">${totalPrice}€</p>
                        <form action="shoppingCart" method="post">
                            <button type="submit" class="btn btn-primary">Comprar</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>


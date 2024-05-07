<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es" data-bs-theme="dark">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
    <jsp:include page="menu.jsp" />
    <div class="container">
        <h1 class="mt-5 mb-3">Realizar el Pedido</h1>
        <form action="buy" method="post">
            <div class="mb-3">
                <label for="name" class="form-label">Nombre</label>
                <input type="text" class="form-control" id="name" name="name" required>
            </div>
            <div class="mb-3">
                <label for="surname" class="form-label">Apellidos</label>
                <input type="text" class="form-control" id="surname" name="surname" required>
            </div>
            <div class="mb-3">
                <label for="address" class="form-label">Direccion</label>
                <input type="text" class="form-control" id="address" name="address" required>
            </div>
            <div class="mb-3">
                <label for="cardNumber" class="form-label">Número de Tarjeta</label>
                <input type="text" class="form-control" id="cardNumber" name="cardNumber" placeholder="Ingrese el número de su tarjeta" required>
            </div>
            <div class="mb-3">
                <label for="cardName" class="form-label">Nombre en la Tarjeta</label>
                <input type="text" class="form-control" id="cardName" name="cardName" placeholder="Ingrese su nombre como aparece en la tarjeta" required>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <div class="mb-3">
                        <label for="expiryDate" class="form-label">Fecha de Expiración</label>
                        <input type="text" class="form-control" id="expiryDate" name="expiryDate" placeholder="MM/YY" required>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="mb-3">
                        <label for="securityCode" class="form-label">Código de Seguridad</label>
                        <input type="text" class="form-control" id="securityCode" name="securityCode" placeholder="CVV" required>
                    </div>
                </div>
                <input type="hidden" name="totalPrice" value="${param.totalPrice}">
                <div class="mb-3">
                    <label class="form-label">Total a Pagar:</label>
                    <p>${param.totalPrice}</p>
                </div>
            </div>
            <button type="submit" class="btn btn-primary">Pagar</button>
        </form>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>


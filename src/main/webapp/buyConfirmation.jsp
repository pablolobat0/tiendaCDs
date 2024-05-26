
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es" data-bs-theme="dark">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Inicio - Tienda de CDs</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
    <jsp:include page="menu.jsp" />
        <c:if test="${not empty requestScope.confirmationMessage}">
            <div class="container">
                <div class="alert alert-success" role="alert">${requestScope.confirmationMessage}</div>
                <div class="card mt-3">
                        <div class="card-body">
                            <h5 class="card-title">Detalles del Pedido</h5>
                            <p class="card-text"><strong>Nombre:</strong> ${name} ${surname}</p>
                            <p class="card-text"><strong>Dirección:</strong> ${address}</p>
                            <p class="card-text"><strong>Importe total:</strong> ${totalPrice}€</p>
                            <p class="card-text"><strong>Fecha de llegada:</strong> ${expectedArrival}</p>
                        </div>
                    </div>
            </div>
        </c:if>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>


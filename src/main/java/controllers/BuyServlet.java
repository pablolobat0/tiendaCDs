package controllers;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import daos.FachadaBaseDeDatos;
import exceptions.UserDoesNotExist;
import models.ShoppingCart;
import models.User;

@WebServlet("/buy")
public class BuyServlet extends HttpServlet {
    FachadaBaseDeDatos fachadaBaseDeDatos;

    @Override
    public void init() {
        fachadaBaseDeDatos = FachadaBaseDeDatos.getFachadaBaseDeDatos();
    }

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        BigDecimal totalPrice = cart.getTotalPrice();
        if (totalPrice.compareTo(BigDecimal.ZERO) == 0) {
            request.setAttribute("error", "Introduzca algún artículo al carrito para comprar");
            request.getRequestDispatcher("shoppingCart.jsp").forward(request, response);
            return;
        }

        User user = (User) session.getAttribute("user");
        if (user == null) {
            request.setAttribute("error", "El usuario debe estar registrado");
            request.getRequestDispatcher("shoppingCart.jsp").forward(request, response);
            return;
        }

        fachadaBaseDeDatos.createOrder(user.getId(), totalPrice);
        cart.clear();
        String confirmationMessage = "¡Compra realizada con éxito!";
        request.setAttribute("confirmationMessage", confirmationMessage);
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String address = request.getParameter("address");
        // Calcular la fecha de llegada esperada
        LocalDate today = LocalDate.now();
        LocalDate expectedArrivalDate = today.plusDays(2);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = expectedArrivalDate.format(formatter);

        request.setAttribute("expectedArrival", formattedDate);

        request.setAttribute("name", name);
        request.setAttribute("surname", surname);
        request.setAttribute("address", address);
        request.setAttribute("totalPrice", totalPrice);
        request.getRequestDispatcher("buyConfirmation.jsp").forward(request, response);
    }
}

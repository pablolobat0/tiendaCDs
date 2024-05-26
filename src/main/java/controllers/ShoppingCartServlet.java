package controllers;

import daos.FachadaBaseDeDatos;
import exceptions.UserDoesNotExist;
import models.ShoppingCart;
import models.User;

import java.io.*;
import java.math.BigDecimal;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/shoppingCart")
public class ShoppingCartServlet extends HttpServlet {
    private FachadaBaseDeDatos fachadaBaseDeDatos;

    @Override
    public void init() {
        fachadaBaseDeDatos = FachadaBaseDeDatos.getFachadaBaseDeDatos();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        if (cart == null) {
            cart = new ShoppingCart();
            session.setAttribute("cart", cart);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("shoppingCart.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        // Verificar si el usuario está autenticado
        if (user == null) {
            request.setAttribute("error", "Debe iniciar sesión o registrarse para realizar una compra.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("signup.jsp");
            dispatcher.forward(request, response);
            return;
        }
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        BigDecimal totalPrice = cart.getTotalPrice();
        if (totalPrice.compareTo(BigDecimal.ZERO) == 0) {
            request.setAttribute("error", "Introduzca algún artículo al carrito para comprar");
            request.getRequestDispatcher("shoppingCart.jsp").forward(request, response);
            return;
        }

        request.setAttribute("totalPrice", cart.getTotalPrice());
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("order.jsp");
        requestDispatcher.forward(request, response);
    }
}

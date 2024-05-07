package controllers;

import daos.FachadaBaseDeDatos;
import exceptions.UserDoesNotExist;
import models.ShoppingCart;

import java.io.*;
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

        request.setAttribute("products", cart.getProducts());
        request.setAttribute("totalPrice", cart.getTotalPrice());
        RequestDispatcher dispatcher = request.getRequestDispatcher("shoppingCart.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        if (cart == null) {
            cart = new ShoppingCart();
            session.setAttribute("cart", cart);
        }

        request.setAttribute("totalPrice", cart.getTotalPrice());
        response.sendRedirect("order.jsp?totalPrice=" + cart.getTotalPrice());
    }
}

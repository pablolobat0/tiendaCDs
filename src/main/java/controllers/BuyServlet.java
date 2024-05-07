package controllers;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
        if (cart == null) {
            cart = new ShoppingCart();
            session.setAttribute("cart", cart);
        }

        String username = (String) session.getAttribute("username");
        BigDecimal totalPrice = new BigDecimal(request.getParameter("totalPrice"));
        try {
            User user = fachadaBaseDeDatos.getUserByName(username);
            fachadaBaseDeDatos.createOrder(user.getId(), totalPrice);
            cart.clear();
        } catch (UserDoesNotExist e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("shoppingCart.jsp").forward(request, response);
            return;
        }
        response.sendRedirect("index.jsp");
    }
}

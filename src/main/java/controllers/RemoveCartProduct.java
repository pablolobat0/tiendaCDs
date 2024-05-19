package controllers;

import daos.FachadaBaseDeDatos;
import exceptions.ProductDoesNotExists;
import models.Product;
import models.ShoppingCart;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/removeCartProduct")
public class RemoveCartProduct extends HttpServlet {
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
            response.sendRedirect("shoppingCart");
            return;
        }

        String productName = request.getParameter("productName");
        try {
            Product product = fachadaBaseDeDatos.getProductByName(productName);
            cart.removeItem(product);
            session.setAttribute("cart", cart);
        } catch (ProductDoesNotExists e) {
            e.printStackTrace();
        }
        response.sendRedirect("shoppingCart");
    }
}

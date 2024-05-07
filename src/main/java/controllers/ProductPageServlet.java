package controllers;

import daos.FachadaBaseDeDatos;
import exceptions.ProductDoesNotExists;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import models.Product;
import models.ShoppingCart;

@WebServlet("/products")
public class ProductPageServlet extends HttpServlet {
    private FachadaBaseDeDatos fachadaBaseDeDatos;

    @Override
    public void init() {
        fachadaBaseDeDatos = FachadaBaseDeDatos.getFachadaBaseDeDatos();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ArrayList<Product> products = fachadaBaseDeDatos.getAllProducts();
        request.setAttribute("products", products);
        RequestDispatcher dispatcher = request.getRequestDispatcher("cds.jsp");
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

        String productName = request.getParameter("productName");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        try {
            Product product = fachadaBaseDeDatos.getProductByName(productName);
            cart.addItem(product, quantity);
        } catch (ProductDoesNotExists e) {
            e.printStackTrace();
        }
        response.sendRedirect(request.getContextPath() + "/products");
    }

}

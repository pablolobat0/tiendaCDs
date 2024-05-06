package controllers;

import daos.FachadaBaseDeDatos;
import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import models.CD;
import models.Product;

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
}

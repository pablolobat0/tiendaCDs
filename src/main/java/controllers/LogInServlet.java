package controllers;

import daos.FachadaBaseDeDatos;
import daos.UserDao;
import exceptions.PasswordNotValid;
import exceptions.UserDoesNotExist;
import exceptions.UsernameNotValid;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/login")
public class LogInServlet extends HttpServlet {
  private FachadaBaseDeDatos fachadaBaseDeDatos;

  @Override
  public void init() {
    fachadaBaseDeDatos = FachadaBaseDeDatos.getFachadaBaseDeDatos();
  }

  protected void doPost(HttpServletRequest request,
                        HttpServletResponse response)
      throws ServletException, IOException {
    String username = request.getParameter("username");
    String password = request.getParameter("password");

    try {
      fachadaBaseDeDatos.authenticate(username, password);
      HttpSession session = request.getSession(true);
      session.setAttribute("username", username);
      response.sendRedirect("index.jsp");
    } catch (UserDoesNotExist e) {
      request.setAttribute("error", "Credenciales inválidas");
      request.getRequestDispatcher("login.jsp").forward(request, response);
    }
  }
}

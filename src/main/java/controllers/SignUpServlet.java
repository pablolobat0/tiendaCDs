package controllers;

import daos.FachadaBaseDeDatos;
import exceptions.EmailAlreadyExists;
import exceptions.EmailNotValid;
import exceptions.PasswordNotValid;
import exceptions.UsernameAlreadyExists;
import exceptions.UsernameNotValid;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {
  private FachadaBaseDeDatos fachadaBaseDeDatos;

  @Override
  public void init() {
    fachadaBaseDeDatos = FachadaBaseDeDatos.getFachadaBaseDeDatos();
  }

  protected void doPost(HttpServletRequest request,
                        HttpServletResponse response)
      throws ServletException, IOException {
    String username = request.getParameter("username");
    String email = request.getParameter("email");
    String password = request.getParameter("password");
    String confirmPassword = request.getParameter("confirmPassword");

    try {
      fachadaBaseDeDatos.register(username, email, password);
      HttpSession session = request.getSession(true);
      session.setAttribute("username", username);
      response.sendRedirect("index.jsp");
    } catch (UsernameNotValid | UsernameAlreadyExists | EmailNotValid |
             EmailAlreadyExists | PasswordNotValid e) {
      request.setAttribute("error", e.getMessage());
      request.getRequestDispatcher("signup.jsp").forward(request, response);
    }
  }
}

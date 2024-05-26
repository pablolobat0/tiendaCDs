package controllers;

import daos.FachadaBaseDeDatos;
import exceptions.UserDoesNotExist;
import models.User;

import java.io.*;
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
            User user = fachadaBaseDeDatos.getUserByName(username);
            HttpSession session = request.getSession(true);
            session.setAttribute("user", user);
            response.sendRedirect("index.jsp");
        } catch (UserDoesNotExist e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}

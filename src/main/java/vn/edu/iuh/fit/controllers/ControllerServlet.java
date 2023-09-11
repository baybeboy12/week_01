package vn.edu.iuh.fit.controllers;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet (urlPatterns = {"/controllerServlet","/control"})
public class ControllerServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse respond) throws ServletException, IOException {

        String action = request.getParameter("action");
        PrintWriter out = respond.getWriter();
        if (action.equalsIgnoreCase("xxx")) {
            out.println("You call action XXX");
        } else if (action.equalsIgnoreCase("yyy")) {
            out.println("You call action YYY");
            respond.sendRedirect("dashboard.jsp");
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}

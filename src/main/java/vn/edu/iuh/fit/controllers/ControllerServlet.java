package vn.edu.iuh.fit.controllers;


import jakarta.inject.Inject;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.iuh.fit.entities.Account;
import vn.edu.iuh.fit.entities.GrantAccess;
import vn.edu.iuh.fit.entities.Status;
import vn.edu.iuh.fit.repositories.AccountRepository;
import vn.edu.iuh.fit.services.AccountServices;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.List;

@WebServlet(urlPatterns = {"/ControlServlet"})
public class ControllerServlet extends HttpServlet {
    @Inject
    AccountRepository services;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse respond) throws ServletException, IOException {

        String action = request.getParameter("action");
        switch (action) {
            case "login":
                logIn(request, respond);
                break;
            case "logout":
                logOut(request, respond);
                break;
            case "addAccount":
                addAccount(request, respond);
                break;
            case "submitAddAccount":
                submitaddAccount(request, respond);
                break;
            case "cancelAddAcc":
                cancelAddAcc(request,respond);
                break;
            default:
                break;
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AccountServices service = new AccountServices();
        List<Account> accountList = service.getAll();
    }
    public void logIn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        services = new AccountRepository();
        HttpSession session = request.getSession(true);
        String uName = request.getParameter("username");
        String uPassword = request.getParameter("password");
        PrintWriter out = response.getWriter();
        GrantAccess gr = services.getAccountRole(uName, uPassword);
        List<Account> dsAccount = services.getAll();
        if (gr.getRole_id().getRole_id().equals("admin")) {
            Account admin = services.accountLogin(uName, uPassword);
            RequestDispatcher dispatcher = request.getRequestDispatcher("dashboard.jsp");
            session.setAttribute("dsAcc", dsAccount);
            session.setAttribute("admin-role", gr);
            dispatcher.forward(request, response);

        } else {
            Account ac = services.accountLogin(uName, uPassword);
            RequestDispatcher dispatcher = request.getRequestDispatcher("userLogin.jsp");
            session.setAttribute("userLogin", ac);
            dispatcher.forward(request, response);
        }
    }
    public void logOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uName = request.getParameter("userID");
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }
    public void cancelAddAcc (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("dashboard.jsp");
        dispatcher.forward(request, response);
    }
    public void addAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("addAcc.jsp");
        dispatcher.forward(request, response);

    }

    public void submitaddAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = request.getParameter("username");
        String fullName = request.getParameter("fullName");
        String pass = request.getParameter("password");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        Account ac = new Account(user, fullName, pass, email, phone, Status.Active);
        boolean rs = services.addAccount(ac);
        PrintWriter out = response.getWriter();
        if (rs) {
            // Cập nhật danh sách tài khoản từ cơ sở dữ liệu
            List<Account> updatedAccountList = services.getAll();
            // Lưu danh sách tài khoản vào session
            HttpSession session = request.getSession(true);
            session.setAttribute("dsAcc", updatedAccountList);
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Insert Success!');");
            out.println("location='dashboard.jsp';");
            out.println("</script>");
        }


    }
}

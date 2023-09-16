<%@ page import="vn.edu.iuh.fit.entities.GrantAccess" %>
<%@ page import="java.util.List" %>
<%@ page import="vn.edu.iuh.fit.entities.Status" %>
<%@ page import="vn.edu.iuh.fit.entities.Account" %>
<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 9/23/2023
  Time: 10:47 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
      integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<link href="css/dashboard.css" rel='stylesheet' type='text/css'>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thêm Account</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link href="css/dashboard.css" rel='stylesheet' type='text/css'>
    <%
        GrantAccess gr = (GrantAccess) session.getAttribute("admin-role");
    %>
</head>
<body style="background: cadetblue">
<header>
    <div class="container">
        <div class="row">
            <div class="col-12 mt-4">
                <h3 class="center-text" style="text-align: center">Xin chào <%=gr.getAccount_id().getFull_name()%></h3>
            </div>
        </div>
    </div>
</header>
<div class="container">
    <div class="row">
        <div class="col-12 mt-4">
            <table class="table">
                <thead class="table-light">
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">FullName</th>
                    <th scope="col">Email</th>
                    <th scope="col">Phone</th>
                    <th scope="col">Status</th>
                    <th scope="col">Action</th>
                </tr>
                </thead>
                <tbody style="background: #f4f4f4">
                <% List<Account> list = (List<Account>) session.getAttribute("dsAcc"); %>
                <%
                    for (int i = 0; i < list.size(); i++) {
                        Account dsAccount = list.get(i);
                %>
                <tr>
                    <td><%=dsAccount.getAccount_id()%></td>
                    <td><%=dsAccount.getFull_name()%></td>
                    <td><%=dsAccount.getEmail()%></td>
                    <td><%=dsAccount.getPhone()%></td>
                    <%
                        Status status = dsAccount.getStatus();
                        String statusText = "";
                        if (status == Status.Active) {
                            statusText = "active";
                        } else if (status == Status.Deactive) {
                            statusText = "deactive";
                        } else if (status == Status.Delete) {
                            statusText = "delete";
                        } else {
                            statusText = "null";
                        }
                    %>
                    <td><%= statusText %></td>

                    <td>
                        <button class="btn btn-primary bi bi-trash" onclick="" name="delete">Delete</button>
                        <button class="btn btn-warning bi bi-pencil-square" name="update">Update</button>
                    </td>
                </tr>
                <%
                    }
                %>
                </tbody>
            </table>
        </div>
    </div>
    <form action="ControlServlet?action=logout" method="post">
        <!-- ... -->
        <button class="logout-button">Đăng Xuất</button>
    </form>
    <form action="ControlServlet?action=addAccount" method="post">
        <button class="btn btn-success">Thêm Account</button>
    </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
        crossorigin="anonymous"></script>
</body>
</html>

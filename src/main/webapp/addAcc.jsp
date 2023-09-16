<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 9/23/2023
  Time: 10:42 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <title>Thêm Account</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #f2f2f2;
      margin: 0;
      padding: 0;
    }

    header {
      background-color: #333;
      color: #fff;
      text-align: center;
      padding: 20px;
    }

    .container {
      background-color: #fff;
      border-radius: 5px;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
      padding: 20px;
      width: 300px;
      margin: 0 auto;
      margin-top: 20px;
    }

    form {
      display: flex;
      flex-direction: column;
    }

    label {
      margin-bottom: 10px;
      font-weight: bold;
    }

    input[type="text"], input[type="password"] {
      padding: 10px;
      margin-bottom: 15px;
      border: 1px solid #ccc;
      border-radius: 4px;
      font-size: 16px;
    }

    button {
      background-color: #333;
      color: #fff;
      border: none;
      border-radius: 4px;
      padding: 10px 20px;
      font-size: 16px;
      cursor: pointer;
    }

    button.cancel {
      background-color: #f44336;
    }

    button:hover {
      background-color: #555;
    }
  </style>
</head>
<body>
<header>
  <h1>Thêm Account</h1>
</header>
<div class="container">
  <form action="ControlServlet?action=submitAddAccount" method="post">
    <label >Họ và Tên:</label>
    <input type="text"  name="fullName" required>

    <label>Số điện thoại:</label>
    <input type="text"  name="phone" required>

    <label >Tên đăng nhập:</label>
    <input type="text"  name="username" required>

    <label >Mật khẩu:</label>
    <input type="password"  name="password" required>

    <label >Email:</label>
    <input type="text"  name="email" required>
    <button type="submit" name="add">Thêm</button>
  </form>
  <form action="ControlServlet?action=cancelAddAcc" method="post">
    <button  class="cancel"  name="cancelAddAcc">Hủy</button>
  </form>
</div>
</body>
</html>


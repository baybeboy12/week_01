<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<form action="controllerServlet" method="post">

    <input type="hidden" name="action" value="xxx">
    <input type="submit" value="call xxx">
</form>

<hr>
<form action="controllerServlet" method="post">

    <input type="hidden" name="action" value="yyy">
    <input type="submit" value="call yyy">
</form>
</body>
</html>
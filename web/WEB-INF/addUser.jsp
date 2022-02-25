<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 24.02.2022
  Time: 19:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>addUser</title>
</head>
<body>
<form action="/addUser" method="post">
    <h1>Գրանցում</h1><br>
    name: <input style="margin-top: auto" type="text" name="name"> <br>
    surname: <input style="margin-top: auto" type="text" name="surname"> <br>
    email: <input style="margin-top: auto" type="text" name="email"> <br>
    password: <input style="margin-top: auto" type="text" name="password"> <br>
    <input style="margin-top: auto" type="submit" value="ok">  </form>
</body>
</html>

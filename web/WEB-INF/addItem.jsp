<%@ page import="java.util.List" %>
<%@ page import="myItems.model.Category" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 24.02.2022
  Time: 19:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>add Item</title>
</head>
<body>
<% List<Category> categories = (List<Category>) session.getAttribute("categories");


%>
<form action="/addItem" method="post"; enctype="multipart/form-data">
    <h1>Ավելացրեք Ձեր հայտարարությունը</h1><br>
    Անուն: <input style="margin-top: auto" type="text" name="title"> <br>
    Գին: <input style="margin-top: auto" type="number" name="price"> <br>
    Նկար: <input style="margin-top: auto" type="file" name="image"> <br>
    <select style="margin-top: auto" name="cat"><%
        for (Category category : categories) {%>
        <option value=<%=category.getId()%>><%=category.getName() %>
        </option>
        <% }%></select><br>
    <input style="margin-top: auto" type="submit" value="ok">  </form>
</body>
</html>

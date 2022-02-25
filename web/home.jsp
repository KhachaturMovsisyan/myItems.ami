<%@ page import="java.util.List" %>
<%@ page import="myItems.model.Item" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 24.02.2022
  Time: 19:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> My Items </title>
</head>
<body>

<% List<Item> allItems = (List<Item>)request.getAttribute("allItems");%>

<form style="width: 200px; height: 200px; float: right" action="/register" method="get">
    <input type="submit" value="Գրանցում"></form>

<form style="width: 200px; height: 200px; float: right" action="/login" method="get">
    <input type="submit" value="Մուտք"></form>
<br>


<form style="width: 200px; height: 200px; float: right" action="" method="get">
    <input type="submit" value="Կահույք"></form>

<form style="width: 200px; height: 200px; float: right" action="/logout" method="get">
    <input type="submit" value="Ավտոմեքենաներ"></form>

<form style="width: 200px; height: 200px; float: right" action="/logout" method="get">
    <input type="submit" value="Տներ"></form>

<form style="width: 200px; height: 200px; float: right" action="/logout" method="get">
    <input type="submit" value="կոմերցիոն"></form>

<form style="width: 200px; height: 200px; float: right" action="/" method="get">
    <input type="submit" value="Գլխավոր"></form> <br>
<div align="center"  style="width: max-content">

    <% for (Item item : allItems) { %>
    <div style="color: cornflowerblue">
    <tr style="border-style: solid">
        <td style="border-style: solid; border-color: dimgray"><h4><%=item.getTitle()%>
        </h4>
        </td>
        <td style="border-style: solid; border-color: dimgray"><h4><%=item.getPrice()%>
        </h4>
        </td>
    </tr></div>
    <% } %>

 </diva>



</body>
</html>

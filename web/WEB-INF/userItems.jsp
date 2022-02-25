<%@ page import="myItems.model.User" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 24.02.2022
  Time: 23:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Home</title>

</head>

<%  User user = (User) session.getAttribute("user"); %>
<h3>  <%=user.getName()%>  <%=user.getSurname()%> </h3>
<form style="width: 200px; height: 200px; float: right" action="/" method="get">
    <input type="submit" value="Դուրս գալ"></form>


<form style="width: 200px; height: 200px; float: right" action="/userItems" method="get">
    <input type="submit" value="Իմ հայտարարությունները"></form>

<form style="width: 200px; height: 200px; float: right" action="/addItem" method="get">
    <input type="submit" value="Ավելացնել"></form>





<%--<img src="/image?path=<%=user.getPictureUrl()%>" width="150"/>--%>
<body>

</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lav din cupcake!</title>
</head>
<body>
<h1> Lav din cupcake!</h1>
<form method="get">
<select name="topping">
    <c:forEach items="${listToppings}" var="topping">
        <option value="${topping.top}">${topping.price}</option>
    </c:forEach>
</select>
<select name="bottom">
    <c:forEach items="${listBottoms}" var="bottom">
        <option value="${bottom.bottom}">${bottom.price}</option>
    </c:forEach>
</select>
</form>
</body>
</html>

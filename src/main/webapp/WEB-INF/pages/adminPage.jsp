<%--
  Created by IntelliJ IDEA.
  User: Jonathan
  Date: 13-11-2020
  Time: 21:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<div class="content">
    <h1>Vælg, hvad du har lyst til at gøre:</h1>

    <form id="login_button" action="${pageContext.request.contextPath}/AddBalanceToUser">
        <input type="submit" value="Tilføj penge til en bruger" />

    </form>
</div>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>${requestScope.title}</title>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
          integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
          crossorigin="anonymous">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css"/>

</head>

<body>


<jsp:include page="/WEB-INF/contains/navbar.jsp" flush="true"/>

<main role="main" class="container">

    <div class="content">
        <h1>Byg din egen cupcake!</h1>

    </div>

</main>
<form method="post">
    <div class="form-group">
        <label for="ShoppingListName">Indkøbslistens navn</label>
        <input type="text" class="form-control" id="shoppingListName" aria-describedby="shoppingListNameHelp" name="name">
        <small id="shoppingListNameHelp" class="form-text text-muted">Indtast venligst et navn på den ønskede liste</small>
    </div>
    <!-- <div class="form-group">
         <label for="exampleInputPassword1">Password</label>
         <input type="password" class="form-control" id="exampleInputPassword1">
     </div>
     <div class="form-group form-check">
         <input type="checkbox" class="form-check-input" id="exampleCheck1">
         <label class="form-check-label" for="exampleCheck1">Check me out</label>
     </div>-->
     <button type="submit" class="btn btn-primary">Submit</button>
</form>
</body>
</html>
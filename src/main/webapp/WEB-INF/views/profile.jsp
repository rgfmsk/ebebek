<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>


<!DOCTYPE html>
<html lang="en">
<head>
    <title>Log in with your account</title>
    <style type="text/css">
        .flex {
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            color: red;
        }
    </style>
</head>

<body>

<div class="flex" style="text-align: center; alignment: center; align-content: center">
    <h2 class="form-heading">Logged in as ${user.userName}</h2>

    ${user.userName}
    <br/>
    ${user.email}
    <br/>
    ${user.password}
    <br/>

    <form method="POST" action="${contextPath}/logout">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="submit" value="Logout"/>
    </form>

</div>

</body>
</html>

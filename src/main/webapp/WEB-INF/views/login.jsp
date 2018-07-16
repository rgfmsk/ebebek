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

    <form method="POST" action="${contextPath}/login">
        <h2 class="form-heading">Log in</h2>

        <div class="flex">
            <span>${message}</span>
            <br/>
            <input name="username" type="text" placeholder="Username"
                   autofocus="true"/>
            <br/>
            <input name="password" type="password" placeholder="Password"/>
            <br/>
            <span>${error}</span>
            <br/>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <br/>

            <button type="submit">Log In</button>
            <br/>
            <h4><a href="${contextPath}/register">Create account</a></h4>
            <br/>
        </div>

    </form>

</div>
</body>
</html>
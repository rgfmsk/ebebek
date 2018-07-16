<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Create account</title>
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

<div style="text-align: center; alignment: center; align-content: center">

    <form:form method="POST" action="${contextPath}/register" modelAttribute="userForm">
        <h2 class="form-heading">Create Account</h2>

        <div>
            <spring:bind path="userName">
                <div class="flex form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="userName" placeholder="Username" autofocus="true"></form:input>
                        <%--<br/>--%>
                    <form:errors path="userName"></form:errors>
                </div>
            </spring:bind>

            <spring:bind path="email">
                <div class="flex form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="email" placeholder="Email" autofocus="true"></form:input>
                        <%--<br/>--%>
                    <form:errors path="email"></form:errors>
                </div>
            </spring:bind>

            <spring:bind path="password">
                <div class="flex form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="password" path="password" placeholder="Password"></form:input>
                        <%--<br/>--%>
                    <form:errors path="password"></form:errors>
                </div>
            </spring:bind>

            <spring:bind path="confirmPassword">
                <div class="flex form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="password" path="confirmPassword" placeholder="Confirm your password"></form:input>
                        <%--<br/>--%>
                    <form:errors path="confirmPassword"></form:errors>
                </div>
            </spring:bind>

            <button type="submit">Submit</button>
        </div>

    </form:form>

</div>
</body>
</html>
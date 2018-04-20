<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/reset.css">
        <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/screen-07.css">
        <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/screen-01.css">
        <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/font-awesome.min.css">
        <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/jquery-ui.css">
	  	<link href="${pageContext.servletContext.contextPath}/css/nouislider.css" rel="stylesheet">
<title>Login</title>
</head>
<script>
            function getPage(obj)
            {
            var url=obj;

            document.registerForm.action=url;
            document.registerForm.method="POST";
            document.registerForm.submit();
            return;
            }
            </script>
 <body style="" class="page-new-search">

        <!-- ngView:  --><div ng-view="" class="ng-scope">      <div class="page-login ng-scope">
        <div class="login-container">
            <img class="logo" src="${pageContext.servletContext.contextPath}/images/logo-login.jpg">
            <div class="login-form">

	<form:form id="registerForm" name="registerForm" modelAttribute="user" action="registerProcessNew"
		method="post">
		<c:if test="${not empty message}">
                <table align="center">
              		<tr>
              			<td style="font-style: italic; color: red;">${message}</td>
              		</tr>
              	</table>
            </c:if>

                <c:if test="${not empty verified}">
                <form:label path="email"> Email entered </form:label>
                <form:input path="email" name="email" id="email" readonly="true"/>
                </c:if>
                <c:if test="${empty verified}">
                <form:label path="email">Enter Your Email </form:label>
                    <form:input path="email" name="email" id="email" />
        		</c:if>

        <c:if test="${not empty verified}">
            <c:if test="${not empty user.email}">


                    <c:if test="${not empty registered}">
                        <form:label path="username">Username chosen </form:label>
                        <form:input path="username" name="username" id="username"  readonly="true"/>
                    </c:if>
                    <c:if test="${empty registered}">
                        <form:label path="username">Enter Your Username </form:label>
                        <form:input path="username" name="username" id="username" />
                    </c:if>
                     <c:if test="${empty registered}">
                        <form:label path="password">Enter Password</form:label></td>
                        <form:password path="newpassTxt" name="newpassTxt"
                                id="newpassTxt" />
                                <form:label path="password">Confirm Password</form:label></td>
                                                <form:password path="repassTxt" name="repassTxt"
                                                        id="repassTxt" />
                     </c:if>
            </c:if>
		</c:if>
		<c:if test="${empty registered}">
            <input type="submit" name="submit2" value = "submit">
         </c:if>

			<div class="reset-register-links group">
                                    <!-- Forgot Password Link -->
                                    <a class="forgot-password fl" href="#" onClick="getPage('forgotPassword')">Forgot Password?</a>
                                    <!-- New User Registration Link -->
                                    <a class="new-user fr" href="#" onClick="getPage('loginnew')" >Existing User?</a>
                                </div>

	</form:form>
	</div>
            </div>
        </div>
    </div>
	<table align="center">
		<tr>
			<td style="font-style: italic; color: red;">${message}</td>
		</tr>
	</table>

</body>
</html>
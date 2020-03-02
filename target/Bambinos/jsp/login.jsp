<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
 <body style="" class="page-new-search">

        <!-- ngView:  --><div ng-view="" class="ng-scope">      <div class="page-login ng-scope">
        <div class="login-container">
            <img class="logo" src="./TradeWins Login_files/logo-login.jpg">
            <div class="login-form">

	<form:form id="loginForm" modelAttribute="login" action="loginProcess"
		method="post">
		<form:label path="username">Enter Your Username </form:label>
				<form:input path="username" name="username" id="username" />

				<form:label path="password">Enter Password</form:label></td>
				<form:password path="password" name="password"
						id="password" />

			<form:button id="login" name="login">Login</form:button>
			<div class="reset-register-links group">
                                    <!-- Forgot Password Link -->
                                    <a class="forgot-password fl" href="http://www.twtradr.com/#">Forgot Password?</a>
                                    <!-- New User Registration Link -->
                                    <a class="new-user fr" href="http://www.twtradr.com/#">New User?</a>
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
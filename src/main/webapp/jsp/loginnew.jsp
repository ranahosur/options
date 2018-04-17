<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>

<head>
<meta http-equiv="Content-Language" content="en-us">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<meta name="GENERATOR" content="Microsoft FrontPage 4.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<title>Login</title>
<link href="includes/eSevaBottom1.css" type="text/css" rel="stylesheet" />
<script>
function login(btnName)
{
   window.document.indexFrm.submit();
}
</script>

</head>

<body topmargin="0" leftmargin="0" onLoad = 'window.document.indexFrm.username.focus()'>
<div id="wrapper">
  <form:form id="loginForm" modelAttribute="login" action="loginProcessNew" method="post">
  <table align="center">
  		<tr>
  			<td style="font-style: italic; color: red;">${message}</td>
  		</tr>
  	</table>
<table width="971" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="100%" height="15" style="padding-right:4">
          </td>
  </tr>
  <tr>
    <td width="100%" height="15"></td>
  </tr>
  <tr>
    <td width="100%">
      <div align="center">
        <center>
        <table border="1" width="60%" bordercolor="#7CC8FA" cellspacing="0" cellpadding="0">
          <tr>
            <td width="100%">
              <table width="100%" border="0" cellpadding="0" cellspacing="1" class="newtexts">
                <tr>
                  <td width="100%" bgcolor="#ABDCFC" colspan="2" height="20">
                    <p align="center" class="newhead">LOGIN</td>
                </tr>
                <tr>
                  <td width="100%" bgcolor="#D6EEFE" colspan="2" height="21">&nbsp;</td>
                </tr>
                <tr>
                  <td width="42%" bgcolor="#D6EEFE" align="right">User
                              Id&nbsp; </td>
                  <td width="58%" bgcolor="#D6EEFE">
                  <form:input path="username" name="username" id="username" size="20"/></td>
                  
                </tr>
                <tr>
                  <td width="42%" bgcolor="#D6EEFE" align="right">Password&nbsp;
                              </td>
                  <td width="58%" bgcolor="#D6EEFE">
                  <form:password path="password" name="password" id="password" /></td>
                </tr>
                <tr>
                  <td width="100%" bgcolor="#D6EEFE" colspan="2" height="21">&nbsp;</td>
                </tr>
                <tr>
                  <td width="100%" bgcolor="#D6EEFE" colspan="2">
                    <div align="center">
                      <center>
                      <table border="1" width="20%" bordercolor="#FFFFFF" cellspacing="0" cellpadding="0">
                        <tr>
                          <td width="100%" colspan="2">
                          <form:button name="stdSubBtn" id ="stdSubBtn" class="newtexts" style="font-family: MS Sans Serif; font-size: 8pt; font-weight: bold"  onClick = 'login(this.name)' >Standard Login</form:button></td>

                        </tr>
                      </table>
                      </center>
                    </div>                  </td>
                </tr>
                <!-- <tr>
                  <td width="100%" bgcolor="#CDE2DB" colspan="2" height="21" align="center"><a href="../htmlpages/secure.htm">What is Secure & Standard Login&nbsp;</a></td>
                </tr> -->
              </table>            </td>
          </tr>
        </table>
        </center>
      </div>    </td>
  </tr>
  <tr>
    <td width="100%" height="15"></td>
  </tr>
  <tr>
    <td width="100%" height="15">
      <div align="center">

        <table border="1" width="60%" bordercolor="#7CC8FA" cellspacing="0" cellpadding="0">
          <tr>
            <td width="100%">
              <table border="0" width="100%" cellspacing="0" cellpadding="0">
                <tr>
                  <td width="50%" bgcolor="#ABDCFC" height="25">
                    <p align="center">&nbsp;

		<a href="registernew" class="newhead">NEW USER !</a>&nbsp;

		</td>
                  <td width="50%" bgcolor="#ABDCFC" height="25">

                  </td>
                </tr>

			  <tr>
                  <td width="50%" bgcolor="#ABDCFC" height="25">
                    <p align="center">&nbsp;
			<a href="resetPassword" class="newhead">RESET&nbsp;PASSWORD!</a>&nbsp;

			</td>
                   <td width="50%" bgcolor="#ABDCFC" height="25">
                  </td>
                </tr>


             </table>
             </td>
          </tr>
        </table>

      </div></td>
    <tr>
	    <td width="100%" height="15"></td>
  </tr>
  <tr>
      <td width="100%" height="15"></td>
  </tr>
  </tr>

          </tr>
  <tr>
    <td width="100%" height="15"></td>
  </tr>
  <tr>
    <td width="100%" height="15"></td>
  </tr>
  <tr>
    <td width="100%" height="15"></td>
  </tr><tr>
    <td width="100%"></td>
  </tr>
</table>
<input type="hidden" name="frmName" value="indexFrm">
</form:form>
</div>
</body>
</html>

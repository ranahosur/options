<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<html>

<head>
<meta http-equiv="Content-Language" content="en-us">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<meta name="GENERATOR" content="Microsoft FrontPage 4.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<title>ForGotPassword</title>
<link href="includes/eSevaBottom1.css" type="text/css" rel="stylesheet" />
<script>
function login(){
   window.document.indexFrm.action = "loginnew"
   window.document.indexFrm.method = "post";
   window.document.indexFrm.submit();
  }

</script>

</head>

<body topmargin="0" leftmargin="0" background="../images/background.gif">
<div id="wrapper">

  <form:form id="indexFrm" modelAttribute="login" action="loginnew" method="post">
<table width="550" border="0" align="center" cellpadding="0" cellspacing="0">

  <tr>
    <td width="100%" height="15"></td>
  </tr>
  <tr>
    <td width="100%" height="15"></td>
  </tr>
  <tr>
    <td width="100%" height="15"></td>
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
                    <p align="center" class="newhead"><B>FORGOT&nbsp;PASSWORD</B></td>
                </tr>
                <tr>
                  <td width="100%" bgcolor="#D6EEFE" colspan="2" height="21">&nbsp;</td>
                </tr>

                <tr>
                  <td width="100%" bgcolor="#D6EEFE" colspan="2" height="21" ALIGN="CENTER"><FONT SIZE="4" COLOR="blue">
                  ${message}:
                  ${email}</FONT></td>
                </tr>
				<tr>
                  <td width="100%" bgcolor="#D6EEFE" colspan="2" height="21">&nbsp;</td>
                </tr>
                <tr>
                  <td width="100%" bgcolor="#D6EEFE" colspan="2">
                    <div align="center">
                      <center>
                      <table border="0" width="50%" bordercolor="#FFFFFF" cellspacing="0" cellpadding="0">
                        <tr>
                          <td width="100%" colspan="2" align="center">
                          <form:button name="Btnlogin" id ="Btnlogin" class="newtexts" style="font-family: MS Sans Serif; font-size: 8pt; font-weight: bold"  onClick = 'login()' tabindex="4">Back</form:button>
                          </td>
                         </tr>
                      </table>
                      </center>
                    </div>
					</td>
                </tr>

              </table>
			  </td>
          </tr>
        </table>
        </center>
      </div>    </td>
  </tr>
   <tr>
    <td width="100%" height="15"></td>
  </tr><tr>
    <td width="100%"></td>
  </tr>
</table>

</form:form>
</div>
</body>
</html>

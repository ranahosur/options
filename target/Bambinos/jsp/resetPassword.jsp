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
  if(window.document.indexFrm.username.value==""){
	alert("Please Enter User Id");
	window.document.indexFrm.username.focus();
	return false;
   }else{
   window.document.indexFrm.stdSubBtn.disabled=true;
   window.document.indexFrm.action = "resetPassword"
   window.document.indexFrm.method = "post";
   window.document.indexFrm.submit();
  }
}

function loginHome(){
   window.document.indexFrm.action = "loginnew"
   window.document.indexFrm.method = "post";
   window.document.indexFrm.submit();
}

</script>
<script type="text/JavaScript">
function valid(f) {
!(/^-[A-zÑñ0-9]*$_#@!`~./i).test(f.value)?f.value = f.value.replace(/[^-*$_#@!`~.A-zÑñ0-9]/ig,''):null;
}

</script>

</head>

<body topmargin="0" leftmargin="0" background="../images/background.gif" onLoad = 'window.document.indexFrm.username.focus()'>
<div id="wrapper">
<form:form id="indexFrm" name="indexFrm" modelAttribute="login" action="resetPassword" method="post">

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
        <table border="1" width="80%" bordercolor="#7CC8FA" cellspacing="0" cellpadding="0">
          <tr>
            <td width="100%">
              <table width="100%" border="0" cellpadding="0" cellspacing="1" class="newtexts">
                <tr>
                  <td width="100%" bgcolor="#ABDCFC" colspan="2" height="20">
                    <p align="center" class="newhead"><B>FORGOT&nbsp;PASSWORD</B></td>
                </tr>
                 <c:if test="${not empty message}">

                          <tr>
                           <td width="100%" bgcolor="#D6EEFE" colspan="2" height="21" ALIGN="CENTER"><FONT SIZE="4" COLOR="red">${message}</FONT></td>
                                          </tr>
                      </c:if>


                <tr>
                  <td width="100%" bgcolor="#D6EEFE" colspan="2" height="21">&nbsp;</td>
                </tr>
                <tr>
                  <td width="42%" bgcolor="#D6EEFE" align="right"><B>User&nbsp;Id&nbsp;:&nbsp;</B> </td>
                  <td width="58%" bgcolor="#D6EEFE">

                  <form:input path="username" name="username" id="username" size="20" onkeyup="valid(this)" onblur="valid(this)" tabindex="1"/> </td>
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
                          <form:button name="stdSubBtn" id ="stdSubBtn" class="newtexts" style="font-family: MS Sans Serif; font-size: 8pt; font-weight: bold"  onClick = 'login()' tabindex="3">Submit</form:button>&nbsp;
                          <form:button name="Btnlogin" id ="Btnlogin" class="newtexts" style="font-family: MS Sans Serif; font-size: 8pt; font-weight: bold"  onClick = 'loginHome()' tabindex="4">Back</form:button>
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

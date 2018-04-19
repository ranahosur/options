<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<html>

<head>

<title>eSeva</title>
<script>
function callFocus()
{
window.document.indexFrm.password.focus();
}
function login()
{
	if(window.document.indexFrm.password.value=="")
      {
      	alert("Enter Password");
	      window.document.indexFrm.password.focus();
            return false;
      }
      else if((window.document.indexFrm.newpassTxt.value).length < 6)
      {
            alert("Password should not be less than 6 characters ");
            window.document.indexFrm.newpassTxt.focus();
            return false;
      }
      else if(window.document.indexFrm.repassTxt.value=="")
      {
            alert("Enter Re-Type Password");
            window.document.userRegFrm.repassTxt.focus();
            return false;
      }
	else
	{
     		window.document.indexFrm.action = "modifyPassword"
     		window.document.indexFrm.method="post";
     		window.document.indexFrm.submit();
	}
}

    function cancel(){
            window.document.indexFrm.action = "welcome"
            window.document.indexFrm.method="post";
            window.document.indexFrm.submit();
    }


	//** To check the Re-type password with password field
	function checkPass(obj)
	{
	         var repwd=obj.value;
	         if((repwd!=window.document.indexFrm.newpassTxt.value) && (repwd!=""))
	         {
	              alert("Incorrect Password");
	              obj.value="";
	              obj.focus();
	         }
                           else if(repwd=="")
                           {

	         }
	}
</script>

</head>

<body topmargin="0" leftmargin="0" onLoad='callFocus()' background="../images/background.gif">
<form:form name="indexFrm" id="indexFrm" modelAttribute="user" method="post" action="modifyPassword">
<form name="indexFrm" autocomplete="off" method="post">

<table border="0" width="971" align="center" bordercolor="#7CC8FA"  cellspacing="0" cellpadding="0">
  <tr>
    <td width="100%" height="15"></td>
  </tr>
  <tr>
    <td width="70%" height="15">
    </td>
  </tr>
  <tr>
    <td width="100%" height="15"></td>
  </tr>
  <tr>
    <td width="100%" height="15">
      <div align="center">
        <center>
        <table border="0" width="971" align="center" bordercolor="#7CC8FA"  cellspacing="0" cellpadding="0">
        <c:if test="${ empty message}">
          <tr>
            <td width="100%" bgcolor="#FFFFFF" align =left><font color="#000080" face="Arial" size="2">
            <b>Hello ${user.firstName}<b></font></td>
          </tr>
          </c:if>
          <tr>
            <td width="100%" bgcolor="#ABDCFC" align =left><font color="#000080" face="Arial" size="4"><b>Change Of Your Password<b></font></td>
          </tr>
          <tr>
            <td width="100%" bgcolor="#FFFFFF" align =left><font color="#000080" face="Arial" size="2">Enter your old password and then choose your new password. Click Submit when you're done
        </font>
        </td></tr>

            <c:if test="${not empty message}">
                      <b></b>
                       <tr>
                                  <td width="100%" bgcolor="#FFFFFF" align =left><font color="#F62217" face="Arial" size="3">
                                  ${message}
                              </font>
                              </td></tr>
             </c:if>

        </table>
        </center>
      </div>
    </td>
  </tr>
  <tr>
    <td width="100%" height="15"></td>
  </tr>
  <tr>
    <td width="100%">
      <div align="center">
        <center>
        <table border="1" width="971" align="center" bordercolor="#7CC8FA"  cellspacing="0" cellpadding="0">
          <tr>
            <td width="100%">
              <table border="0" width="100%" cellspacing="1" cellpadding="0">
                <tr>
                  <td width="100%" bgcolor="#CDE2DB" colspan="2" height="21">&nbsp;</td>
                </tr>
                <tr>
                  <td width="40%" bgcolor="#CDE2DB" align="right"><font face="MS Sans Serif" size="2">Enter your <b>Old Password&nbsp;</b></font></td>
                  <td width="60%" bgcolor="#CDE2DB"><form:password path="password" name="password" id="password" size="20"  maxlength="16" />
                  </td>
                </tr>
                <tr>
                  <td width="40%" bgcolor="#CDE2DB" align="right"><font face="MS Sans Serif" size="2">Choose a <b>New Password&nbsp;</b></font>
                              </td>
                  <td width="60%" bgcolor="#CDE2DB">
                  <form:password path="newpassTxt" name="newpassTxt" id="newpassTxt" size="20"  maxlength="16"/> (6 to 16 Characters)
</td>
                </tr>

                <tr>
                  <td width="40%" bgcolor="#CDE2DB" align="right"><font face="MS Sans Serif" size="2">Re-type your <b>New Password&nbsp;</b></font></td>
                  <td width="60%" bgcolor="#CDE2DB">
                  <form:password path="repassTxt" name="repassTxt" id="repassTxt" size="20"  onBlur="checkPass(this)" maxlength="16"/>
                  </td>
                </tr>
                <tr>
                  <td width="100%" bgcolor="#CDE2DB" colspan="2" height="21">&nbsp;</td>
                </tr>
                <tr>
                  <td width="100%" bgcolor="#CDE2DB" colspan="2">
                    <div align="center">
                      <center>
                      <table border="0" width="20%" bordercolor="#84B9A7" cellspacing="0" cellpadding="0">
                        <tr>
                          <td width="50%">
                          <form:button id="subBtn" name="subBtn" style="font-family: MS Sans Serif; font-size: 8pt; font-weight: bold"  onClick = 'login()'>Submit</form:button>
                          </td>
                <td width="50%">
                <form:button id="subBtn" name="subBtn" style="font-family: MS Sans Serif; font-size: 8pt; font-weight: bold"  onClick = 'cancel()'>Cancel</form:button>
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
      </div>
    </td>
  </tr>
  <tr>
    <td width="100%" height="15"></td>
  </tr>
  <tr>
    <td width="100%" height="15">

    <div align="center">
      <center>
      <table border="0" width="971" align="center" bordercolor="#7CC8FA"  cellspacing="0" cellpadding="0">
        <tr>
          <td width="100%" bgcolor="#ABDCFC">&nbsp;</td>
        </tr>
      </table>
      </center>
    </div>

    </td>
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
    <td width="100%"></td>
  </tr>
</table>
<form:input path="username" id="username" type="hidden" name="username" />
</form:form>
</body>

</html>

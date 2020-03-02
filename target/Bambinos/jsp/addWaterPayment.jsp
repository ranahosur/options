<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<meta name="GENERATOR" content="Microsoft FrontPage 4.0">

<title>Hyderabad Metro Water Works</title>
<script>
function services()
{
	window.document.Metro.action="selectService";
	window.document.Metro.submit();
}

function validate()
{
    if(window.document.Metro.consumerNo.value=="")
    {
	alert("Enter Consumer No");
	window.document.Metro.consumerNo.focus();
	return false;
    }
    else
    {
    window.document.Metro.userAction.value = "submit";
       window.document.Metro.method = "post";
       window.document.indexFrm.submit();
	   window.document.Metro.action="waterPayment"
	   return  true;
    }

}
</script>
</head>

<body bgcolor="#FFFFFF" background="../images/background.gif">

<form:form name="Metro" id="Metro" method="POST" onSubmit='return validate()' modelAttribute="waterPaymentUser">
  <p>&nbsp;</p>
  <div align="center">
    <center>
    <table border="0" cellspacing="0" width="89%">
      <tr>
       <td width="30%">&nbsp;</td>
        <td width="40%" align="center" bgcolor="#CDCECE"><font face="Arial" size="4" color="#000000">
	<b>Hyderabad Metro Water Works</b></font></td>
       <td width="30%">&nbsp;</td>
      </tr>
    </table>
    </center>
  </div>
  <br>
   <c:if test="${not empty message}">

        <tr>
         <td width="100%" bgcolor="#D6EEFE" colspan="2" height="21" ALIGN="CENTER"><FONT SIZE="4" COLOR="red">${message}</FONT></td>
                        </tr>
    </c:if>

  <div align="center">
    <table border="1" bordercolor="#7CC8FA" cellspacing="0" width="68%">
    <tr><td width="100%">
    <table border="0" bgcolor="#FFFFFF"cellspacing="1" width="100%">
        <td width="44%" bgcolor="#ABDCFC" align="right">
          <font face="Arial" size="2"><b>Consumer No&nbsp;</b></font></td>
        <center>
        <td width="56%" bgcolor="#DCDBDB">
        <form:input path="consumerNo" name="consumerNo" id="consumerNo" size="9" tabindex="1"/> </td>
        </tr>
      </table>
    </center>
   </td></tr>
   </table>
  </div>
   <p align="center">&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <font face="Arial">
   <form:button name="subBtn" id ="subBtn" class="newtexts" style="font-family: MS Sans Serif; font-size: 8pt; font-weight: bold"  onClick = 'login()' tabindex="3">Submit</form:button>&nbsp;
   <form:button name="serBtn" id ="serBtn" class="newtexts" style="font-family: MS Sans Serif; font-size: 8pt; font-weight: bold"  onClick = 'services()' tabindex="4">Back</form:button>
	</p>
	<form:input type="hidden" path="userAction" name="userAction" id="userAction"  />
	<form:input type="hidden" path="paymentServiceId" name="paymentServiceId" id="paymentServiceId" value = "${waterPaymentUser.paymentServiceId}"  />
</form:form>
</body>
</html>

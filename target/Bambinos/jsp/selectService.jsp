<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html><head>

<title>Bill Payment</title>
<script>
  function pay()
  {
    window.document.serFrm.action="ConsData.jsp";
    window.document.serFrm.submit();
  }
function welcome()
{
  window.document.serFrm.action="welcome"
  window.document.serFrm.submit();

}


function submit1(obj,obj2)
{

document.serFrm.paymentServiceId.value=obj;
document.serFrm.action=obj2;
document.serFrm.method="post";
document.serFrm.submit();
}


</script>
</head>
<body  bgcolor="#FFFFFF" background="${pageContext.request.contextPath}/images/background.gif">
<form:form id="serFrm" name="serFrm" method="post" modelAttribute="userPaymentService">


   <br>
   <center>
    <table border="0" cellspacing="0" width="90%">
      <tr>
         <td  width="25%">&nbsp;</td>
        <td width="50%"  bgcolor="#CDCECE" align="center">
          <font face="Arial" size="4" color="#000000"><b>Click The Service You Want To Add</b></font></td>
         <td width="25%">&nbsp;</td>
      </tr>
    </table>
    </center>
  <br>

<c:if test="${not empty paymentServiceList}">


			<c:forEach var="paymentService" items="${paymentServiceList}">

				<div align="center"><center>
                           	<table border="1" width="350" bordercolor="#7CC8FA"  bgcolor="#cdcece" cellspacing="0" cellpadding="0" height="20">
                           	<tr>
                              <td height="20" VALIGN="MIDDLE" width="90%"><font face="Arial" size="4" align=center>

                              <img src="${pageContext.request.contextPath}/images/dot.jpg" width='20' height='20' name="${paymentService.paymentServiceId}" onClick="submit1('${paymentService.paymentServiceId}','${paymentService.paymentServiceCode}')" >
                		${paymentService.paymentServiceName}

                              </td>
                            	 </tr>
                               </table>
                               <table border="0" width="350" cellspacing="0" cellpadding="0" height="8">
                               <tr><td></td></tr>
                    	 </table>
                          	 </center></div></td></tr></table>

			</c:forEach>
		</ul>

	</c:if>



	 <form:input type="hidden" path="username" name="username" id="username"  />
    <form:input type="hidden" path="paymentServiceId" name="paymentServiceId" id="paymentServiceId"  />
    <p>&nbsp;</p>
          <table border="0" cellspacing="0" width="90%">
          <tr>

            <td align="center">
    	<font face="Arial" size="4" color="#000000"><b>
             <form:button name="Btnlogin" id ="Btnlogin" class="newtexts" style="font-family: MS Sans Serif; font-size: 8pt; font-weight: bold"  onClick = 'welcome()'>Back</form:button>
          </tr>
        </table>
	</form:form>
</body>
</html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
     <head><title>Bill Payment Consumer Details</title></head>



      <center>

     <script>
      	function getPage(obj)
      	{
      	var url=obj;

      	document.ConsData.action=url
      	document.ConsData.method="POST";
      	document.ConsData.submit();
      	}

      </script>
            <body bgcolor="#FFFFFF" onLoad='disable()' background="${pageContext.request.contextPath}/images/background.gif">

            <form:form name="ConsData" modelAttribute="user" action="payoptions.jsp" onSubmit='return validate()'>

      <center>



     <table border="0" cellspacing="0" width="971" align="center">
     <tr>
     <td width="33%" align="left">
     <img src="${pageContext.request.contextPath}/images/addmoreservices.gif" border="0" name="selectService" onClick="getPage(this.name)">
     </td>
          <td width="33%" align="left">
          <img src="${pageContext.request.contextPath}/images/lastrecieptpaid.gif" border="0" name="custbills" onClick="getPage(this.name)">
          </td>
     <td width="33%" align="left">
     <img src="${pageContext.request.contextPath}/images/deleteservices.gif" border="0" name="DelConsData" onClick="getPage(this.name)">
     </td>
     <td width="20%" align="left">
     <img src="${pageContext.request.contextPath}/images/changeyourpassword.gif" border="0" name="modifyPassword" onClick="getPage(this.name)">
     </td>
   <td width="20%" align="left">
     <img src="${pageContext.request.contextPath}/images/modifyyourprofile.gif" border="0" name="modifyProfile" onClick="getPage(this.name)">
     </td>
     </tr>
	<tr>
      <td width="33%" align="left"><font face="Arial" size="3" color="#6C0000">
      <td width="33%" align="center" colspan=3><font face="Arial" size="4" color="#000000">
      <b>PAYMENT DETAILS</b></font></td>
      <td width="33%" align="right"><font face="Arial" size="3" color="#6C0000">
	<img src="images/logout.gif" border="0" name="logout" onClick="getPage(this.name)">
	</font></td>
      </tr>
      </table>

      </center>
      <br>
      <c:if test="${not empty message}">
          <b>${message}</b>
      </c:if>
      <center>
      <table border="1" bordercolor="#7CC8FA" cellspacing="0" width="971" align="center">
      <tr>
      <td width="100%">
      <table border="0" cellspacing="1" width="100%">
      <tr>
      <td width="50%" align="right" bgcolor="#ABDCFC"><font face="Arial" size="2">
      <b>Customer Name&nbsp;&nbsp;</b></font></td>
      <td width="50%" align="left" bgcolor="#DCDBDB"><font face="Arial" size="2">
     ${user.firstName} &nbsp;&nbsp;${user.lastName}</font></td>
      </tr>
      <tr>
      <td width="50%" align="right" bgcolor="#ABDCFC"><font face="Arial" size="2">
      <b>H No&nbsp;&nbsp;</b></font></td>
      <td width="50%" align="left" bgcolor="#DCDBDB"><font face="Arial" size="2">
      ${user.houseNo}</font></td>
      </tr>
      <tr>
      <td width="50%" align="right" bgcolor="#ABDCFC"><font face="Arial" size="2">
      <b>Street&nbsp;&nbsp;</b></font></td>
      <td width="50%" align="left" bgcolor="#DCDBDB"><font face="Arial" size="2">
      ${user.street}</font></td>
      </tr>

      <tr>
      <td width="50%" align="right" bgcolor="#ABDCFC"><font face="Arial" size="2">
      <b>Location&nbsp;&nbsp;</b>
      </font></td>
      <td width="50%" align="left" bgcolor="#DCDBDB"><font face="Arial" size="2">
      ${user.location}</font></td>
      </tr>
      <tr>
      <td width="50%" align="right" bgcolor="#ABDCFC"><font face="Arial" size="2">
      <b>City&nbsp;&nbsp;</b></font></td>
      <td width="50%" align="left" bgcolor="#DCDBDB"><font face="Arial" size="2">
      ${user.city}</font></td>
      </tr></table></td></tr></table></center>
      <br>

      <input type = hidden name = "frmNameHdn" value = "ConsData">
   	<script>

	function login()
	{

  	  window.document.ConsData.action="../htmlpages/mainbottom.htm"
  	  window.document.ConsData.submit();
	}

        </script>
        <c:if test="${empty user.userPaymentServiceList}">

    	<center>
    	<table border="0" cellspacing="0" width="90%">
      	<tr>
        <td width="100%" align="center"><font face="Arial" size="4" color=blue>
	You must add service for Payment.
	</font></td>
      	</tr>
        <tr></tr>
    	</table>
    	</center>
    	</c:if>
    	 <c:if test="${not empty user.userPaymentServiceList}">
    	 <center>
             	<table border="0" cellspacing="0" width="90%">
               	<tr>
                 <td width="100%" align="center"><font face="Arial" size="4" color=blue>
         	Services registered
         	</font></td>
               	</tr>
                 <tr></tr>
             	</table>
             	</center>
             	             	<table border="1" width="350" bordercolor="#7CC8FA" bgcolor="#cdcece" cellspacing="0" cellpadding="0" height="20">
             	<tr>
                      <td height="20" VALIGN="MIDDLE" width="50%"><font face="Arial" size="4" align=center>
                      Service Name
                       </td>
                             <td height="20" VALIGN="MIDDLE" width="50%"><font face="Arial" size="4" align=center>
                                                                 Consumer No.
                                      </td>
                             </tr>
                 </tbody>
    	 <c:forEach var="userPaymentService" items="${user.userPaymentServiceList}">


                                    	<tr bgcolor="#ABDCFC">
                                                              <td height="20" valign="MIDDLE" width="60%"><font face="Arial" size="4" align="center">
                                      ${userPaymentService.paymentServiceName}


                                       </font></td>
                                                        <td height="20" valign="MIDDLE" width="40%"><font face="Arial" size="4" align="center">
                                                                              <font face="Arial" size="4" align="center">
                                                                             ${userPaymentService.externalId}
                                                                 </font></td>
                                                              </tr>
                                                  </tbody>
           </c:forEach>

                                        <table border="0" width="350" cellspacing="0" cellpadding="0" height="8">
                                        <tr><td></td></tr>

                             	 </table>
                                   	 </center></div></td></tr></table>

    	 </c:if>
	<br><br>


	<form:input path="username" id="username" type="hidden" name="username"  />

</form:form>
        </body>
	</html>
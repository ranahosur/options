<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>



	<html>
	<head>
	<title>User Registration</title>
	<script type="text/JavaScript">

function RegexEmail(obj){
   var emailStr = obj.value;
   if(emailStr == ""){
        return true;
   }
   var emailRegexStr = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
   var isvalid = emailRegexStr.test(emailStr);

   if (!isvalid) {
        alert('Invalid email address');
        obj.value = "";
        obj.focus;
    }
 }


	function valid(f)
	{
		!(/^- [A-zÑñ0-9]*$_#@!`~/i).test(f.value)?f.value = f.value.replace(/[^- *$_#@!`~A-zÑñ0-9]/ig,''):null;
	}
	</script>
       	<script>
 	//**This function used for allowing entering only numbers and dots.
      	function numOnly()
      	{
          if(!((event.keyCode>=48)&&(event.keyCode<=57)))
          {
              event.keyCode=0;
          }
          else event.keyCode = event.keyCode;
       	}







	//** To Check the manadatory fields

 	function validFeilds()
 	{
	 /* getMon();
	  var mon=window.document.userRegFrm.monHdn.value;
	  var year=window.document.userRegFrm.yearTxt.value;
	  var days=getDays(mon,year);
        */
	              if(window.document.userRegFrm.firstName.value=="")
                         {
	              alert("Enter First Name");
	              window.document.userRegFrm.firstName.focus();
	              return false;
                         }
               	       else if(window.document.userRegFrm.lastName.value=="")
                         {
	              alert("Enter Last Name");
	              window.document.userRegFrm.lastName.focus();
	              return false;
                         }
			else if(window.document.userRegFrm.email.value=="")
                         {
	              alert("Enter Email ID");
	              window.document.userRegFrm.email.focus();
	              return false;
                         }

               	       else if(window.document.userRegFrm.houseNo.value=="")
                         {
	              alert("Enter House No");
	              window.document.userRegFrm.houseNo.focus();
	              return false;
                         }
               	       else if(window.document.userRegFrm.location.value=="")
                         {
	              alert("Enter Location");
	              window.document.userRegFrm.location.focus();
	              return false;
                         }
                         else if(window.document.userRegFrm.city.value=="")
                         {
	              alert("Enter City");
	              window.document.userRegFrm.city.focus();
	              return false;
                         }

	       else
	       {
	              return true;
	       }
	}


  	</script>





	</head>
	<body topmargin="0" leftmargin="0" background="../images/background.gif" onLoad= 'window.document.userRegFrm.firstName.focus()'>
	<center>
	<form:form name="userRegFrm" id="userRegFrm" modelAttribute="user" method="post" action="updateProfile" onSubmit='return validFeilds()'>
	<table border="0" width="100%" cellspacing="0" cellpadding="0">
	  <tr>
	  <td width="100%" valign="top" colspan=2>
      	  <div align="center">
                <center>
        	  <table border="0" width="75%" cellspacing="0" cellpadding="0">
	  <tr  bgColor=#a0b8c8>
	    <td align=left noWrap width="50%" valign="top" height="20" bgcolor="#ABDCFC"><font face="Arial" size="4" color="#C00000"><b>
	      Modify Your Personal Information</b></font></td>
	     <td align=right noWrap width="50%" valign="top" height="20" bgcolor="#ABDCFC">
		<font face="Arial" color="#C00000" size="3">
          <A href="welcome">Home</a>
</font></b></td>
  	  </tr>
	  </table></center></div></td></tr>
  	  <tr>
    	     <td width="100%" valign="top" colspan=2>
      	  <div align="center">
        	  <center>

        	  <table border="0" width="75%" cellspacing="1" cellpadding="0">
          	    <tr>
                           <td width="568" valign="middle" bgcolor="#CCE1DB" align="left" colspan="2" height="20">
        <p align="left"><b><font face="Arial" size="2">&nbsp;<font face="Arial" size="2" color="#893333">
        To Update Your Personal Information&nbsp;</font></font></b></td>
                        </tr>

        	      </center>
        	      <tr>
                           <td width="568" valign="middle" bgcolor="#CCE1DB" align="left" colspan="2" height="20">
        <b><font face="Arial" size="2" color="#893333">&nbsp;1.&nbsp; Change
        Your Personal Information e.g. First Name,Last Name,Address etc.,</font></b></td>
                        </tr>
        	      <tr>
                           <td width="568" valign="middle" bgcolor="#CCE1DB" align="left" colspan="2" height="20">
        <b><font face="Arial" size="2" color="#893333">&nbsp;2.&nbsp; Click on
        Modify button to save you changes.</font></b></td>
                        </tr>
        	      <tr>
                           <td width="568" valign="middle" bgcolor="#CCE1DB" align="left" colspan="2" height="20">
        <b><font face="Arial" size="2" color="#893333">&nbsp;3.&nbsp; You can
        not Change your User Id.</font></b></td>
                        </tr>
        	      <tr>
                           <td width="568" valign="middle" bgcolor="#ABDCFC" align="left" colspan="2" height="20">
		<p align="left"><b><font face="Arial" size="2">&nbsp;<font face="Arial" size="2" color="#893333">
		Personal Information</font></font></b></td>
                        </tr>
                        <center>
                        <tr>
          	          <td width="253" valign="middle" bgcolor="#ABDCFC" align="right"><b><font color="#FF0000" face="Arial" size="2">
		* </font><font size="2" face="Arial">First Name&nbsp;&nbsp;</font></b></td>
          	          <td width="312" valign="top" bgcolor="#dcdbdb">
          	          <form:input path="firstName" name="firstName" id="firstName" size="30"  autocomplete="off" maxlength="15" onkeyup="valid(this)" onblur="valid(this)"/>

        	       </tr>
        	       <tr>
          	          <td width="253" valign="middle" bgcolor="#ABDCFC" align="right"><b><font color="#FF0000" face="Arial" size="2">
		* </font><font size="2" face="Arial">Last Name&nbsp;&nbsp;</font></b></td>
          	          <td width="312" valign="top" bgcolor="#dcdbdb">
          	          <form:input path="lastName" name="lastName" id="lastName" size="30"  autocomplete="off" maxlength="30" onkeyup="valid(this)" onblur="valid(this)"/>
          	          </td>
                         </tr>

                         </center>
        	       <tr>
          	          <td width="568" valign="middle" bgcolor="#ABDCFC" align="left" colspan="2" height="20">
            		<p align="left"><b><font face="Arial" size="2" color="#893333">&nbsp;Address</font></b></td>
	       </tr>

                         <center>
        	       <tr>
          	          <td width="253" valign="middle" bgcolor="#ABDCFC" align="right"><b><font face="Arial" size="2">
		Email ID&nbsp;<font size="2" face="Arial" color="#FFFFFF">&nbsp;</font></font></b></td>
          	          <td width="312" valign="top" bgcolor="#dcdbdb">
          	            <form:input path="email" name="email" id="email"
                      		size="30"  autocomplete="off" maxlength="50" onblur="return RegexEmail(this);"/>
          	          </td>
                        </tr>

        	       <tr>
          	          <td width="253" valign="middle" bgcolor="#ABDCFC" align="right"><b><font color="#ff0000" face="Arial" size="2">
		* </font><font face="Arial" size="2">House No&nbsp;&nbsp;</font></b></td>
          	          <td width="312" valign="top" bgcolor="#dcdbdb">
          	          <form:input type="text" id="houseNo" path="houseNo"
                      		name="houseNo" size="20"  autocomplete="off" maxlength="35" onkeyup="validate(this)" onblur="validate(this)"/></td>
        	      </tr>
                        <tr>
          	         <td width="253" valign="middle" bgcolor="#ABDCFC" align="right"><b><font face="Arial" size="2">
		Street&nbsp;&nbsp;</font></b></td>
          	         <td width="312" valign="top" bgcolor="#dcdbdb">
          	         <form:input type="text" id="street" path="street" name="street"
                     		 size="20"  autocomplete="off" maxlength="45" onkeyup="valid(this)" onblur="valid(this)"/></td>
                       </tr>
                       <tr>
          	        <td width="253" valign="middle" bgcolor="#ABDCFC" align="right"><b><font color="#ff0000" face="Arial" size="2">
		* </font><font face="Arial" size="2">Location&nbsp;&nbsp;</font></b></td>
          	        <td width="312" valign="top" bgcolor="#dcdbdb">
          	        <form:input type="text" id="location" path="location" name="location"
                    		size="20"  autocomplete="off" maxlength="45" onkeyup="valid(this)" onblur="valid(this)"/></td>
        	    </tr>
        	    <tr>
           	       <td width="253" valign="middle" bgcolor="#ABDCFC" align="right"><b><font color="#ff0000" face="Arial" size="2">
		* </font><font face="Arial" size="2">City&nbsp;&nbsp;</font></b></td>
          	       <td width="312" valign="top" bgcolor="#dcdbdb">
          	       <form:input type="text" id="city" path="city" name="city"
                   		size="20"  autocomplete="off" maxlength="35" onkeyup="valid(this)" onblur="valid(this)"/></td>
                      </tr>
                     <tr>
          	      <td width="253" valign="middle" bgcolor="#ABDCFC" align="right"><b><font face="Arial" size="2">
		PIN&nbsp;&nbsp;</font></b></td>
          	      <td width="312" valign="top" bgcolor="#dcdbdb">
          	      <form:input type="text" id="pinCode" path="pinCode" name="pinCode"
                                                                 		size="20"  autocomplete="off" maxlength="6" onKeyPress = 'numOnly()'/></td>
                    </tr>
                    <tr>
                       <td width="253" valign="middle" bgcolor="#ABDCFC" align="right"><b><font face="Arial" size="2">
		Phone No&nbsp;&nbsp;</font></b></td>
          	     <td width="312" valign="top" bgcolor="#dcdbdb"><b><font size="2" face="Arial">
	<form:input type="text" id="residencePhone" path="residencePhone" name="residencePhone" size="10"  autocomplete="off" maxlength="15" onKeyPress = "numOnly()"/>&nbsp;Res</font></b></td>
	  </tr>
        	  <tr>
          	     <td width="253" valign="middle" bgcolor="#ABDCFC" align="right">&nbsp;</td>
          	     <td width="312" valign="top" bgcolor="#dcdbdb"><b><font size="2" face="Arial">
		<form:input type="text" id="mobilePhone" path="mobilePhone"  name="mobilePhone" size="10"  autocomplete="off" maxlength="12" onKeyPress = "numOnly()"/>&nbsp;Mobile</font></b></td>
        	  </tr>
        	  <tr>
          	     <td width="253" valign="top" bgcolor="#ABDCFC" align="right">&nbsp;</td>
          	     <td width="312" valign="top" bgcolor="#dcdbdb"><b><font size="2" face="Arial">
		<form:input type="text" id="officePhone" path="officePhone"  name="officePhone" size="10"  autocomplete="off" maxlength="15" onKeyPress = "numOnly()"/>&nbsp;Office</font></b></td>
        	  </tr>
        	</table>
        	</center>
      	</div>
    	</td>
  	</tr>
  	  <tr>
    	      <td width="100%" valign="top"></td>
  	  </tr>

  	  <tr>
    	     <td width="100%" valign="top">
      	     <div align="center">
        	     <table border="0" width="75%" cellspacing="1" cellpadding="0">

          	       <tr>
                          <br>
            	          <td width="45%" bgcolor="#dcdbdb" height="20"><font color="#ff0000" face="Arial" size="2"><b>
		           * </font><font face="Arial" size="2">mandatory values</font></td>
                          <td width="65%" bgcolor="#dcdbdb" height="20" ALIGN = CNETRE>
                          <font face="Arial" size="2">
                          <form:button id="subBtn" name="subBtn">Modify</form:button>
		          </font>&nbsp;
		          </td>
          	       </tr>
	    </table>
    <form:input path="username" id="username" type="hidden" name="username"  />
	</form:form>
	</body>
	</html>

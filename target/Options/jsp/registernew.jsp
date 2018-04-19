<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>User Registration</title>

	<script type="text/JavaScript">
	function valid(f)
	{
		!(/^-[A-zÑñ0-9Ññ ]*$_#@!`~/i).test(f.value)?f.value = f.value.replace(/[^-*$_#@!`~A-zÑñ0-9Ññ ]/ig,''):null;
	}

	function validate(f)
	{
	//alert("asfdsf");
	    !(/^-[A-zÑñ0-9]*:_#~/i).test(f.value)?f.value = f.value.replace(/[^-*:_#~A-zÑñ0-9]/ig,''):null;
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

        function numCharsOnly()
        {
         if(event.keyCode >=97 && event.keyCode<=122)
           event.keyCode = event.keyCode;
         if((event.keyCode>=48)&&(event.keyCode<=57)||(event.keyCode>=65)&&(event.keyCode<=90)
          ||(event.keyCode>=97)&&(event.keyCode<=122)||(event.keyCode==95))
         event.keyCode = event.keyCode;
        else
          event.keyCode=0;
        }


	//** This function used for check leap year or not.
  	function leapYear(year)
  	{
      	  if(((year%4==0)&&(year%100!=0))||((year%100==0)&&(year%400==0)))
     	  {
        	return true;
     	  }
     	  else
     	  {
       		return false;
     	  }
  	}

        //** This function used for check each month number of days.
  	function getDays(month,year)
        {
          var monarr=new Array(12);
          monarr[0]=31
          monarr[1]=(leapYear(year))?29:28
          monarr[2]=31
          monarr[3]=30
          monarr[4]=31
          monarr[5]=30
          monarr[6]=31
          monarr[7]=31
          monarr[8]=30
          monarr[9]=31
          monarr[10]=30
          monarr[11]=31
          return monarr[month]
      	}
        function getMon()
	{
	  var monval=window.document.userRegFrm.month.value;
	  if(monval=="JAN")
	  {
	     window.document.userRegFrm.monHdn.value=0;
          }
	  else if(monval=="FEB")
          {
	     window.document.userRegFrm.monHdn.value=1;
          }
	  else if(monval=="MAR")
          {
	     window.document.userRegFrm.monHdn.value=2;
          }
	  else if(monval=="APR")
          {
	     window.document.userRegFrm.monHdn.value=3;
          }
	  else if(monval=="MAY")
          {
	     window.document.userRegFrm.monHdn.value=4;
          }
	  else if(monval=="JUN")
          {
	     window.document.userRegFrm.monHdn.value=5;
          }
	  else if(monval=="JUL")
          {
	     window.document.userRegFrm.monHdn.value=6;
          }
	  else if(monval=="AUG")
          {
	     window.document.userRegFrm.monHdn.value=7;
          }
	  else if(monval=="SEP")
          {
	     window.document.userRegFrm.monHdn.value=8;
          }
	  else if(monval=="OCT")
          {
	     window.document.userRegFrm.monHdn.value=9;
          }
	  else if(monval=="NOV")
          {
	     window.document.userRegFrm.monHdn.value=10;
          }
	  else if(monval=="DEC")
          {
	     window.document.userRegFrm.monHdn.value=11;
          }

	}
	//** To check the Re-type password with password field
	function checkPass(obj)
	{
	         var repwd=obj.value;
	         if((repwd!=window.document.userRegFrm.password.value) && (repwd!=""))
	         {
	              alert("Incorrect Password");
	              obj.value="";
	              obj.focus();
	         }
                           else if(repwd=="")
                           {

	         }
	}

	//** To Check the manadatory fields
 	function validFeilds()
 	{
	  getMon();
	  var mon=window.document.userRegFrm.monHdn.value;
	  var year=window.document.userRegFrm.year.value;
	  var days=getDays(mon,year);


	       if(window.document.userRegFrm.userIdTxt.value=="")
	       {
	              alert("Enter UserId");
	              window.document.userRegFrm.userIdTxt.focus();
	              return false;
	       }
	       else if(window.document.userRegFrm.userIdTxt.value=="null")
	       {
	              alert("Please Enter Valid UserId");
	              window.document.userRegFrm.userIdTxt.focus();
	              return false;

	       }
	          else if((window.document.userRegFrm.userIdTxt.value).length < 4)
                         {
	              alert("UserId should not be less than 4 characters ");
	              window.document.userRegFrm.userIdTxt.focus();
	              return false;
                         }
               	       else if(window.document.userRegFrm.password.value=="")
                         {
	              alert("Enter Password");
	              window.document.userRegFrm.password.focus();
	              return false;
                         }
		      else if((window.document.userRegFrm.password.value).length < 6)
                         {
	              alert("Password should not be less than 6 characters ");
	              window.document.userRegFrm.password.focus();
	              return false;
                         }

               	       else if(window.document.userRegFrm.repassword.value=="")
                         {
	              alert("Enter Re-Type Password");
	              window.document.userRegFrm.repassword.focus();
	              return false;
                         }
               	else if(window.document.userRegFrm.firstNameTxt.value=="")
                         {
	              alert("Enter First Name");
	              window.document.userRegFrm.firstNameTxt.focus();
	              return false;
                         }
               	       else if(window.document.userRegFrm.lastName.value=="")
                         {
	              alert("Enter Last Name");
	              window.document.userRegFrm.lastName.focus();
	              return false;
                         }
               	       else if(window.document.userRegFrm.month.value=="")
                         {
	              alert("Select Month of DOB");
	              window.document.userRegFrm.month.focus();
	              return false;
                         }

               	       else if(window.document.userRegFrm.year.value=="")
                         {
	              alert("Enter Year of DOB");
	              window.document.userRegFrm.year.focus();
	              return false;
                         }
	  		else if((parseInt(window.document.userRegFrm.year.value)<1920)||
				(parseInt(window.document.userRegFrm.year.value)>2001))
          		{
	    		alert("Enter Correct Year");
			window.document.userRegFrm.year.focus();
	    		return false;
          	         }
               	       else if(window.document.userRegFrm.day.value=="")
                         {
	              alert("Enter Day of DOB");
	              window.document.userRegFrm.day.focus();
	              return false;
                         }
	  		else if(parseInt(window.document.userRegFrm.day.value)>days)
          		{
	    		 alert("Enter Correct Date");
			window.document.userRegFrm.day.focus();
	    		return false;
          		}

               	       else if(window.document.userRegFrm.gender.value=="")
                         {
	              alert("Select Gender");
	              window.document.userRegFrm.gender.focus();
	              return false;
                         }
               	       else if(window.document.userRegFrm.category.value=="")
                         {
	              alert("Select Occupation");
	              window.document.userRegFrm.category.focus();
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
	           else if(window.document.userRegFrm.secQtnSel.value=="")
	           {
	              alert("Select Security Question");
	              window.document.userRegFrm.secQtnSel.focus();
	              return false;
	           }
	           else if(window.document.userRegFrm.ansTxt.value=="")
	           {
	              alert("Enter Answer");
	              window.document.userRegFrm.ansTxt.focus();
	              return false;
	           }
	           else if((window.document.userRegFrm.ansTxt.value).length<4)
	           {
	              alert("Enter Answer minimum 4 characters");
	              window.document.userRegFrm.secQtnSel.focus();
	              return false;
	           }
	       else
	       {
	              return true;
	       }
	}



        function datecheck()
	{
	  var mon=window.document.userRegFrm.month.value;
	  var year=window.document.userRegFrm.year.value;
	  var days=getDays(mon,year)
	  if((window.document.userRegFrm.year.value<1920)||(window.document.userRegFrm.year.value<2001))
          {
	    alert("Enter Correct Year");
	    return false;
          }
	  if(window.document.userRegFrm.day.value>days)
          {
	    alert("Enter Correct Day");
	    return false;
          }
	}

  	</script>
	</head>
	<body topmargin="0" leftmargin="0" onLoad= 'window.document.userRegFrm.userIdTxt.focus()'>
	<center>
	<form:form name="userRegFrm" id="userRegFrm" modelAttribute="user" action="registerProcessNew">
	<c:if test="${not empty message}">
        <table align="center">
      		<tr>
      			<td style="font-style: italic; color: red;">${message}</td>
      		</tr>
      	</table>
    </c:if>
	<table border="0" width="971" align="center" bordercolor="#7CC8FA"  cellspacing="0" cellpadding="0">
	  <tr>
	  <td width="100%" valign="top" colspan=2>
      	  <div align="center">
                <center>
        	  <table border="0" width="971" align="center" bordercolor="#7CC8FA"  cellspacing="0" cellpadding="0">
	  <tr  bgColor=#a0b8c8>
	    <td align=left noWrap width="50%" valign="top" height="20" bgColor=#ABDCFC><font face="Arial" size="4" color="#C00000"><b>
	      Registration Details</b></font></td>
	     <td align=right noWrap width="50%" valign="top" height="20" bgColor=#ABDCFC><b><font face="Arial" size="4" color="#C00000">
		Already Registered&nbsp;</font><font face="Arial" color="#C00000" size="3">
          <A href="loginnew">LogIn</a>
</font></b></td>
  	  </tr>
	  </table></center></div></td></tr>
  	  <tr>
    	     <td width="100%" valign="top" colspan=2>
      	  <div align="center">
        	  <center>
        	  <table border="0" width="971" align="center" bordercolor="#7CC8FA"  cellspacing="1" cellpadding="0">
          	    <tr>
            	       <td width="35%" valign="middle" bgColor=#ABDCFC align="right"><b><font face="Arial" color="#FF0000" size="2">
		* </font><font size="2" face="Arial">User-ID&nbsp;&nbsp;</font></b></td>
            	       <td width="65%" valign="top" bgcolor="#dcdbdb">
		<form:input path="username" name="username" id="username" size="20" autocomplete="off" maxlength="16" onkeypress = "numCharsOnly()" onkeyup="valid(this)" onblur="valid(this)"/><font size=-1 color=blue>(4 to 16 Characters)</font></td>
          	    </tr>
                     <tr>
            	       <td width="35%" valign="middle" bgColor=#ABDCFC align="right"><b><font color="#FF0000" face="Arial" size="2">
		* </font><font size="2" face="Arial">Password &nbsp;&nbsp;</font></b></td>
            	        <td width="65%" valign="top" bgcolor="#dcdbdb">
            	        <form:password path="password" name="password" id="password"  size="20" maxlength="16"/><font size=-1 color=blue>(6 to 16 Characters)</font></td>
                       </tr>
                      <tr>
            	        <td width="35%" valign="middle" bgColor=#ABDCFC align="right"><b><font color="#FF0000" face="Arial" size="2">
	          * </font><font size="2" face="Arial" >Re-type Password&nbsp;&nbsp;</font></b></td>
            	        <td width="65%" valign="top" bgcolor="#dcdbdb">
            	        <input type="password" name="repassword" size="20" maxlength="16" onBlur='checkPass(this)'></td>
          	     </tr>
        	      </center>
        	      <tr>
                           <td width="100%" valign="middle" bgColor=#ABDCFC align="left" colspan="2" height="20">
		<p align="left"><b><font face="Arial" size="2">&nbsp;<font face="Arial" size="2" color="#893333">
		Personal Information</font></font></b></td>
                        </tr>
                        <center>
                        <tr>
          	          <td width="35%" valign="middle" bgColor=#ABDCFC align="right"><b><font color="#FF0000" face="Arial" size="2">
		* </font><font size="2" face="Arial">First Name&nbsp;&nbsp;</font></b></td>
          	          <td width="65%" valign="top" bgcolor="#dcdbdb">
          	          <form:input path="firstName" name="firstName" id="firstName" size="30"  autocomplete="off" maxlength="15" onkeyup="valid(this)" onblur="valid(this)"/></td>
        	       </tr>
        	       <tr>
          	          <td width="35%" valign="middle" bgColor=#ABDCFC align="right"><b><font color="#FF0000" face="Arial" size="2">
		* </font><font size="2" face="Arial">Last Name&nbsp;&nbsp;</font></b></td>
          	          <td width="65%" valign="top" bgcolor="#dcdbdb">
          	          <form:input path="lastName" name="lastName" id="lastName" size="30"  autocomplete="off" maxlength="30" onkeyup="valid(this)" onblur="valid(this)"/></td>
                         </tr>
	       <tr>
          	          <td width="35%" valign="middle" bgColor=#ABDCFC align="right"><b><font color="#FF0000" face="Arial" size="2">
		* </font><font size="2" face="Arial">DOB&nbsp;&nbsp;</font></b></td>
          	          <td width="65%" valign="top" bgcolor="#dcdbdb">
          	           <form:select size="1" path="month" name="month" id="month" style="width:80px">
             		<option value="" selected>--Select--</option>
             		<option value="01" id="0">Jan</option>
             		<option value="02" id="1">Feb</option>
             		<option value="03" id="2">Mar</option>
             		<option value="04" id="3">Apr</option>
             		<option value="05" id="4">May</option>
             		<option value="06" id="5">Jun</option>
			<option value="07" id="6">Jul</option>
             		<option value="08" id="7">Aug</option>
             		<option value="09" id="8">Sep</option>
             		<option value="10" id="9">Oct</option>
             		<option value="11" id="10">Nov</option>
             		<option value="12" id="11">Dec</option>
             		</form:select>
                	<form:input path="day" name="day" id="day"  size="2"  autocomplete="off" maxlength="2" onKeyPress = "numOnly()"/>
		<form:input path="year" name="year" id="year" size="4"  autocomplete="off" maxlength="4" onKeyPress = "numOnly()"/>
			<font size="2" face="Arial">
		<b>&nbsp;(Month Day Year)</b></font></td>
	       </tr>
        	  <tr>
          	          <td width="35%" valign="middle" bgColor=#ABDCFC align="right"><b><font  color="#FF0000" face="Arial" size="2">
		* </font><font size="2" face="Arial">Gender&nbsp;&nbsp;</font></b></td>
          	          <td width="65%" valign="top" bgcolor="#dcdbdb">
          	          <form:select path="gender" name="gender" id="gender" size="1" style="width:80px">
             		<option value="">--Select--</option>
             		<option value="M">Male</option>
             		<option value="F">Female</option>
            		</form:select></td>
	       </tr>
                         </center>
        	       <tr>
          	          <td width="100%" valign="middle" bgColor=#ABDCFC align="left" colspan="2" height="20">
            		<p align="left"><b><font face="Arial" size="2" color="#893333">&nbsp;Address</font></b></td>
	       </tr>
                         <center>
        	       <tr>
          	          <td width="35%" valign="middle" bgColor=#ABDCFC align="right"><b><font face="Arial" size="2">
		Email ID&nbsp;<font size="2" face="Arial" color="#FFFFFF">&nbsp;</font></font></b></td>
          	          <td width="65%" valign="top" bgcolor="#dcdbdb">
          	          <form:input path="email" name="email" id="email" size="30"  autocomplete="off" maxlength="50" onblur="return RegexEmail(this);"/></td>
                        </tr>
                        <tr>
          	         <td width="35%" valign="middle" bgColor=#ABDCFC align="right"><b><font  color="#FF0000" face="Arial" size="2">
		* </font><font size="2" face="Arial">Occupation&nbsp;&nbsp;</font></b></td>
          	         <td width="65%" valign="top" bgcolor="#dcdbdb"><form:select size="1" name="category" id="category" path="category" style="width=154px">
		<OPTION selected value="">--Select Occupation--</OPTION>

		   <OPTION value="1">Central Govt Employee</OPTION>

		   <OPTION value="2">State Govt Employee</OPTION>

		   <OPTION value="3">Public Ltd Company</OPTION>

		   <OPTION value="4">Private Ltd Company</OPTION>

		   <OPTION value="5">Bussiness</OPTION>

		   <OPTION value="6">Doctor</OPTION>

		   <OPTION value="7">Lawyer</OPTION>

		   <OPTION value="8">Educator</OPTION>

		   <OPTION value="9">Unemployed</OPTION>

		   <OPTION value="10">Retired</OPTION>

		   <OPTION value="11">Other</OPTION>

            		</form:select></td>
        	       </tr>
        	       <tr>
          	          <td width="35%" valign="middle" bgColor=#ABDCFC align="right"><b><font color="#ff0000" face="Arial" size="2">
		* </font><font face="Arial" size="2">House No&nbsp;&nbsp;</font></b></td>
          	          <td width="65%" valign="top" bgcolor="#dcdbdb">
          	          <form:input type="text" id="houseNo" path="houseNo"
		name="houseNo" size="20"  autocomplete="off" maxlength="35" onkeyup="validate(this)" onblur="validate(this)"/></td>
        	      </tr>
                        <tr>
          	         <td width="35%" valign="middle" bgColor=#ABDCFC align="right"><b><font face="Arial" size="2">
		Street&nbsp;&nbsp;</font></b></td>
          	         <td width="65%" valign="top" bgcolor="#dcdbdb">
          	          <form:input type="text" id="street" path="street"
                     		name="street"
		 size="20"  autocomplete="off" maxlength="45" onkeyup="valid(this)" onblur="valid(this)"/></td>
                       </tr>
                       <tr>
          	        <td width="35%" valign="middle" bgColor=#ABDCFC align="right"><b><font color="#ff0000" face="Arial" size="2">
		* </font><font face="Arial" size="2">Location&nbsp;&nbsp;</font></b></td>
          	        <td width="65%" valign="top" bgcolor="#dcdbdb"><form:input type="text" id="location" path="location"
                                                                                        		name="location"
		size="20"  autocomplete="off" maxlength="45" onkeyup="valid(this)" onblur="valid(this)"/></td>
        	    </tr>
        	    <tr>
           	       <td width="35%" valign="middle" bgColor=#ABDCFC align="right"><b><font color="#ff0000" face="Arial" size="2">
		* </font><font face="Arial" size="2">City&nbsp;&nbsp;</font></b></td>
          	       <td width="65%" valign="top" bgcolor="#dcdbdb"><form:input type="text" id="city" path="city" name="city"
		size="20"  autocomplete="off" maxlength="35" onkeyup="valid(this)" onblur="valid(this)"/></td>
                      </tr>
        	    <tr>
           	       <td width="35%" valign="middle" bgColor=#ABDCFC align="right"><b><font face="Arial" size="2">
		State&nbsp;&nbsp;</font></b></td>
          	       <td width="65%" valign="top" bgcolor="#dcdbdb"><form:input type="text" id="state" path="state" name="state"
		value = "TELANGANA" size="20"  autocomplete="off" maxlength="30"/></td>
                     </tr>
                     <tr>
          	      <td width="35%" valign="middle" bgColor=#ABDCFC align="right"><b><font face="Arial" size="2">
		PIN&nbsp;&nbsp;</font></b></td>
          	      <td width="65%" valign="top" bgcolor="#dcdbdb"><form:input type="text" id="pinCode" path="pinCode" name="pinCode"
		size="20"  autocomplete="off" maxlength="6" onKeyPress = 'numOnly()'/></td>
                    </tr>
                    <tr>
                       <td width="35%" valign="middle" bgColor=#ABDCFC align="right"><b><font face="Arial" size="2">
		Phone No&nbsp;&nbsp;</font></b></td>
          	     <td width="65%" valign="top" bgcolor="#dcdbdb"><b><font size="2" face="Arial">
	<form:input type="text" id="residencePhone" path="residencePhone" name="residencePhone" size="10"  autocomplete="off" maxlength="15" onKeyPress = "numOnly()"/>&nbsp;Res</font></b></td>
	  </tr>
        	  <tr>
          	     <td width="35%" valign="middle" bgColor=#ABDCFC align="right">&nbsp;</td>
          	     <td width="65%" valign="top" bgcolor="#dcdbdb"><b><font size="2" face="Arial">
		<form:input type="text" id="mobilePhone" path="mobilePhone"  name="mobilePhone" size="10"  autocomplete="off" maxlength="12" onKeyPress = "numOnly()"/>&nbsp;Mobile</font></b></td>
        	  </tr>
        	  <tr>
          	     <td width="35%" valign="top" bgColor=#ABDCFC align="right">&nbsp;</td>
          	     <td width="65%" valign="top" bgcolor="#dcdbdb"><b><font size="2" face="Arial">
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
        	     <table border="0" width="971" align="center" bordercolor="#7CC8FA"  cellspacing="1" cellpadding="0">
          	       <tr>
            	           <td width="100%" bgColor=#ABDCFC height="20" colspan="2">
              		<p align="left"><b><font face="Arial" size="2"><font color="#FFFF00">
		&nbsp;</font><font face="Arial" size="2" color="#893333">
		If you forgot your Password, we would identify you with this information.</font></font></b></td>
          	       </tr>
                         <center>
          	       <tr>
            	          <td width="35%" valign="middle" bgColor=#ABDCFC align="right"><font color="#ff0000" face="Arial" size="2"><b>
		* </font><font face="Arial" size="2">Security Question&nbsp;&nbsp;</b></font></td>
            	          <td width="65%" bgcolor="#dcdbdb" height="20"><select size="1" name="secQtnSel"
		style="width:250px">
                  	<OPTION selected value="">--select a question to answer--
		<OPTION value="What is your favorite restaurant?">What is your favorite restaurant?
		<OPTION value="What is your pets name?">What is your pet's name?
		<OPTION value="When is your anniversary?">When is your anniversary?
		<OPTION value="Who was your childhood hero?">Who was your childhood hero?
		<OPTION value="Who is your favorite celebrity of all time?">Who is your favorite celebrity of all time?
		<OPTION value="What is your favorite sports team?">What is your favorite sports team?</OPTION>
              		</select></td>
          	       </tr>
          	       <tr>
            	          <td width="35%" valign="middle" bgColor=#ABDCFC align="right"><font color="#ff0000" face="Arial" size="2"><b>
		* </font><font face="Arial" size="2">Answer&nbsp;&nbsp;</b></font></td>
            	          <td width="65%" bgcolor="#dcdbdb" height="20"><input type=password name="ansTxt" size="34"
				autocomplete="off" maxlength=25></td>
          	       </tr>
          	       <tr>
            	          <td width="35%" bgcolor="#dcdbdb" height="20">&nbsp;</td>
            	          <td width="65%" bgcolor="#dcdbdb" height="20"><font color="#ff0000" face="Arial" size="2"><b>
		* </font><font face="Arial" size="2">mandatory values</font></td>
          	       </tr>

          	       <tr>
            	          <td width="35%" bgcolor="#dcdbdb" height="20">&nbsp;</td>
            	          <td width="65%" bgcolor="#dcdbdb" height="20">
        <font face="Arial" size="2">
        <form:button id="register" name="register">Register</form:button>

		</td>
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
    	        <td width="100%" valign="top"></td>
  	      </tr>
	    </table>

	</form:form>
	</body>
	</html>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<!-- saved from url=(0032)http://www.twtradr.com/managevol -->
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title>TradeWins Ltd</title>
        <meta name="description" content="">
        <meta name="viewporddt" content="width=device-width, initial-scale=1">

       <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/reset.css">
               <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/screen-07.css">
               <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/screen-01.css">
               <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/font-awesome.min.css">
               <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/jquery-ui.css">
       	  	<link href="${pageContext.servletContext.contextPath}/css/nouislider.css" rel="stylesheet">


    </head>
    <script>
          	function getPage(obj,selectedId)
          	{
          	var url=obj;

          	document.ConsData.action=url;
          	document.ConsData.selectAdminPrivilegeId.value=selectedId;
          	document.ConsData.method="POST";
          	document.ConsData.submit();
          	}

          	function getPage2(obj)
            {
            var url=obj;

            document.ConsData.action=url;
            document.ConsData.method="POST";
            document.ConsData.submit();
            }

          </script>
    <body style="" class="page-vol-surface">
        <form:form name="ConsData" modelAttribute="user" action="welcomeSAdmin" onSubmit='return validate()'>
        <!-- ngView:  --><div ng-view="" class="ng-scope">    <div ng-controller="MVolCtrl" changebody="" ng-init="getlist()" class="ng-scope">
    <div ng-model="mvolres" class="ng-pristine ng-untouched ng-valid">
        <header class="main-header group">
            <a href="http://www.twtradr.com/#" class="mobile-menu inactive"><img src=".${pageContext.servletContext.contextPath}/images/icon-sidebar-inactive.png"></a>
            <a href="http://www.twtradr.com/#" class="mobile-menu active"><img src="${pageContext.servletContext.contextPath}/images/icon-sidebar-active.png"></a>
            <div class="header-block logo-area">
                <a href="http://www.twtradr.com/#" class="desktop-logo"><img src="${pageContext.servletContext.contextPath}/images/header-logo.png"></a>
                <a href="http://www.twtradr.com/#" class="mobile-logo inactive"><img src="${pageContext.servletContext.contextPath}/images/header-logo-icon.png"></a>
            </div>
            <div class="header-block nav-area">
                <ul class="group">
                    <li class="active" ><a ng-href="/welcomeSAdmin"  href="#" onClick="getPage2('welcomeSAdmin');">Subscription View</a></li>
                    <li ><a ng-href="/logout"  href="#" onClick="getPage2('addSubscription');">New Subscription</a></li>
                    <li><a href="http://www.twtradr.com/history">Manage Market</a></li>
                    <li><a href="http://www.twtradr.com/#">Manage News</a></li>
                </ul>
            </div>
            <div class="header-block misc-area">
                <ul class="group">
                    <li><a ng-href="/logout" onClick="getPage('logout')" href="#">Sign out</a></li>
                    <li><a ng-href="#" href="http://www.twtradr.com/#">Hello, <span class="ng-binding">${user.firstName} &nbsp;&nbsp;${user.lastName}</span></a></li>
                </ul>
            </div>
            <div class="gradient-line"></div>
        </header>
    <main>
        <section class="content">
           <div class="table-data sort">

            <c:if test="${empty adminPrivileges}">

               	<center>
               	<table border="0" cellspacing="0" width="90%">
                 	<tr>
                       <td width="100%" align="center"><font face="Arial" size="4" color=blue>
                Currently there are no subscriptions
                </font></td>
                        </tr>
                       <tr></tr>
                    </table>
                    </center>
              </c:if>
              <c:if test="${not empty adminPrivileges}">


                <table cellpadding="0" cellspacing="0">
                <thead>
                   <tr>
                    <td>Name</td>
                    <td>Email</td>
                    <td>Subscription Date</td>
                    <td>Expiry Date</td>
                    <td>Team Count</td>
                    <td>Action</td>
                   </tr>
                </thead>
                <c:forEach var="adminPrivilege" items="${adminPrivileges}">

                <!-- ngRepeat: xx in vollist --><tbody ng-model="forfutref" ng-repeat="xx in vollist" class="ng-pristine ng-untouched ng-valid ng-scope">
                    <tr>

                        <td class="ng-binding"> ${adminPrivilege.firstName} &nbsp; ${adminPrivilege.lastName} </td>
                        <td class="ng-binding">${adminPrivilege.email}</td>
                        <td class="ng-binding">${adminPrivilege.createDate}</td>
                        <td class="ng-binding">${adminPrivilege.trialPeriodEndDate}</td>
                        <td class="ng-binding">${adminPrivilege.maxTeamCount}</td>
                        <td><button type="button" onClick="getPage('manageSubscription','${adminPrivilege.adminPrivilegeId}');">View/Edit</button></td>
                    </tr>
                </tbody>
                </c:forEach>
                </table>
                </c:if>
<!--<form:input path="username" id="username" type="hidden" name="username"  />-->
<input type="hidden" name="selectAdminPrivilegeId"  />
</form:form>



</table></div></section></main></div></div></div>

</body></html>
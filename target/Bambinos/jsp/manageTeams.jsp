<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

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
          	function getPage(obj)
          	{
          	var url=obj;
          	document.ConsData.action=url;
            document.ConsData.method="POST";
          	document.ConsData.submit();
          	return;
          	}

          </script>
     <body style="" class="page-new-search">
<form:form name="ConsData" modelAttribute="team" action="manageTeams" class="start-search-form ng-pristine ng-valid" onSubmit='return validate()'>

            <div ng-view="" class="ng-scope">    <div ng-controller="MVolCtrl" changebody="" ng-init="getlist()" class="ng-scope">
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
                        <li><a href="#"  onClick="getPage('welcomeAdmin');">Subscription</a></li>
                        <li class="active"><a href="#" onClick="getPage('addTeams');">Add Team</a></li>
                        <li><a href="http://www.twtradr.com/histo#ry">Payment History</a></li>
                        <li><a href="#">Make Payment</a></li>
                        <li><a href="#">Reports</a></li>
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
        <table width="50%" align="center">
        	<tr><td>
                <section class="content">

                                <h3 class="section-title" align="center">Team Details</h3>
                                <ul class="form-elements">


                                        <li class="single-select required error">
                                            <label>Team Name</label>
                                               <form:input path="teamName" name="teamName" id="teamName" ng-model="gamma" ng-change="validateField(searchform,&#39;gamma&#39;)" class="ng-pristine ng-untouched ng-valid"/>
                                      <div ng-show="searchform.gamma.$touched" class="ng-hide">
                                            <div ng-messages="searchform.gamma.$error" class="errors ng-inactive">
                                              <!-- ngMessage: onlynum -->
                                              <!-- ngMessage: oobonds -->
                                            </div>
                                      </div>
                                        </li>

        								<li class="single-select required error">
                                            <label>Email</label>
                                            <c:if test="${not empty team.teamId}">
                                                <form:input path="teamLogin.email" readonly="true" name="email" id="email" ng-model="gamma" ng-change="validateField(searchform,&#39;gamma&#39;)" class="ng-pristine ng-untouched ng-valid"/>
                                             </c:if>
                                             <c:if test="${empty team.teamId}">
                                                 <form:input path="teamLogin.email" name="email" id="email" ng-model="gamma" ng-change="validateField(searchform,&#39;gamma&#39;)" class="ng-pristine ng-untouched ng-valid"/>
                                              </c:if>
                                            <div ng-messages="searchform.vega.$error" class="errors ng-inactive">
                                              <!-- ngMessage: onlynum -->
                                              <!-- ngMessage: oobonds -->
                                            </div>
                                      </div>
                                        </li>
                                        <c:if test="${not empty team.teamId}">

                                          <li class="single-select required">
                                               <label>Status</label>
                                                <form:checkbox path="active" name="active" id="active" />
                                                <p class="validation-error"></p>
                                            </li>

                                        </c:if>

                                </ul>
        				<table width="100%"><tr>

        				<td width="45%" align="center">
                        <form:hidden path="teamId"/>
        				 <input type="submit" name="submit2" value="submit" onClick="getPage('saveTeam');" >
        				  </td>
        				  <td width="10%" align="center">&nbsp;</td>
        				<td width="45%" align="center">
                          <input type="submit" name="submit3" value="cancel" onClick="getPage('welcomeAdmin');" >
        	</td>
        		</tr></table>


                </form:form>


                </section>
                   </td></tr></table>

                </main>
        </div>


        </div></div>



        </body></html>
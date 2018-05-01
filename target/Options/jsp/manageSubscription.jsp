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
<form:form name="ConsData" modelAttribute="adminPrivilege" action="manageSubscription" class="start-search-form ng-pristine ng-valid" onSubmit='return validate()'>

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
                        <li ><a ng-href="/welcomeSAdmin"  href="#" onClick="getPage('welcomeSAdmin');">Subscription View</a></li>
                        <li class="active" ><a ng-href="/logout"  href="#" onClick="getPage('addSubscription');">New Subscription</a></li>
                        <li><a href="#" onClick="getPage('inputFileMarket');">Upload Market Data</a></li>
                        <li><a href="#" onClick="getPage('manageMarket');">Manage Market</a></li>
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
        <table width="50%" align="center">
        	<tr><td>
                <section class="content">

                                <h3 class="section-title" align="center">Subscription Details</h3>
                                <ul class="form-elements">


                                        <li class="single-select required error">
                                            <label>First Name</label>
                                               <form:input path="user.firstName" name="firstName" id="firstName" ng-model="gamma" ng-change="validateField(searchform,&#39;gamma&#39;)" class="ng-pristine ng-untouched ng-valid"/>
                                      <div ng-show="searchform.gamma.$touched" class="ng-hide">
                                            <div ng-messages="searchform.gamma.$error" class="errors ng-inactive">
                                              <!-- ngMessage: onlynum -->
                                              <!-- ngMessage: oobonds -->
                                            </div>
                                      </div>
                                        </li>


                                        <li class="single-select required error">
                                            <label>Last Name</label>
                                            <form:input path="user.lastName" name="lastName" id="lastName" ng-model="gamma" ng-change="validateField(searchform,&#39;gamma&#39;)" class="ng-pristine ng-untouched ng-valid"/>
                                      <div ng-show="searchform.vega.$touched" class="ng-hide">
                                            <div ng-messages="searchform.vega.$error" class="errors ng-inactive">
                                              <!-- ngMessage: onlynum -->
                                              <!-- ngMessage: oobonds -->
                                            </div>
                                      </div>
                                        </li>

        								<li class="single-select required error">
                                            <label>Email</label>
                                                <form:input path="user.email" name="email" id="email" ng-model="gamma" ng-change="validateField(searchform,&#39;gamma&#39;)" class="ng-pristine ng-untouched ng-valid"/>

                                            <div ng-messages="searchform.vega.$error" class="errors ng-inactive">
                                              <!-- ngMessage: onlynum -->
                                              <!-- ngMessage: oobonds -->
                                            </div>
                                      </div>
                                        </li>

                                        <li class="single-select required error">
                                            <label>Max Team Count</label>
                                                <form:input path="maxTeamCount" name="maxTeamCount" id="maxTeamCount" ng-model="gamma" ng-change="validateField(searchform,&#39;gamma&#39;)" class="ng-pristine ng-untouched ng-valid"/>

                                            <div ng-messages="searchform.vega.$error" class="errors ng-inactive">
                                              <!-- ngMessage: onlynum -->
                                              <!-- ngMessage: oobonds -->
                                            </div>
                                      </div>
                                        </li>
                                        <li class="single-select required error">
                                            <label>Max Team Size</label>
                                                <form:input path="maxTeamMemberCount" name="maxTeamMemberCount" id="maxTeamMemberCount" ng-model="gamma" ng-change="validateField(searchform,&#39;gamma&#39;)" class="ng-pristine ng-untouched ng-valid"/>

                                            <div ng-messages="searchform.vega.$error" class="errors ng-inactive">
                                              <!-- ngMessage: onlynum -->
                                              <!-- ngMessage: oobonds -->
                                            </div>
                                      </div>
                                        </li>
                                        <!--<li class="single-select required error">
                                            <label>Trial Period End Date</label>
                                                <form:input path="trialPeriodEndDate" name="trialPeriodEndDate" id="trialPeriodEndDate" ng-model="gamma" ng-change="validateField(searchform,&#39;gamma&#39;)" class="ng-pristine ng-untouched ng-valid"/>

                                            <div ng-messages="searchform.vega.$error" class="errors ng-inactive">

                                            </div>
                                      </div>
                                        </li>-->

        								<li class="single-select required error">
                                            <label>Phone Number</label>
                                                <form:input path="user.phoneNumber" name="phoneNumber" id="phoneNumber" ng-model="gamma" ng-change="validateField(searchform,&#39;gamma&#39;)" class="ng-pristine ng-untouched ng-valid"/>

                                      <div ng-show="searchform.vega.$touched" class="ng-hide">
                                            <div ng-messages="searchform.vega.$error" class="errors ng-inactive">
                                              <!-- ngMessage: onlynum -->
                                              <!-- ngMessage: oobonds -->
                                            </div>
                                      </div>
                                        </li>

        								<li class="single-select required error">
                                            <label>Address Line 1</label>
                                                <form:input path="user.addressLine1" name="addressLine1" id="addressLine1" ng-model="gamma" ng-change="validateField(searchform,&#39;gamma&#39;)" class="ng-pristine ng-untouched ng-valid"/>

                                      <div ng-show="searchform.vega.$touched" class="ng-hide">
                                            <div ng-messages="searchform.vega.$error" class="errors ng-inactive">
                                              <!-- ngMessage: onlynum -->
                                              <!-- ngMessage: oobonds -->
                                            </div>
                                      </div>
                                        </li>

        								<li class="single-select required error">
                                            <label>Address Line 2</label>
                                                <form:input path="user.addressLine2" name="addressLine2" id="addressLine2" ng-model="gamma" ng-change="validateField(searchform,&#39;gamma&#39;)" class="ng-pristine ng-untouched ng-valid"/>

                                      <div ng-show="searchform.vega.$touched" class="ng-hide">
                                            <div ng-messages="searchform.vega.$error" class="errors ng-inactive">
                                              <!-- ngMessage: onlynum -->
                                              <!-- ngMessage: oobonds -->
                                            </div>
                                      </div>
                                        </li>
                                        <li class="single-select error">
                                            <label>City </label>
                                                <form:input path="user.city" name="city" id="city" ng-model="gamma" ng-change="validateField(searchform,&#39;gamma&#39;)" class="ng-pristine ng-untouched ng-valid"/>

                                      <div ng-show="searchform.theta.$touched" class="ng-hide">
                                            <div ng-messages="searchform.theta.$error" class="errors ng-inactive">
                                            </div>
                                      </div>
                                        </li>
        								 <li class="single-select error">
                                            <label>State </label>
                                                <form:input path="user.state" name="state" id="state" ng-model="gamma" ng-change="validateField(searchform,&#39;gamma&#39;)" class="ng-pristine ng-untouched ng-valid"/>
                                      <div ng-show="searchform.theta.$touched" class="ng-hide">
                                            <div ng-messages="searchform.theta.$error" class="errors ng-inactive">
                                            </div>
                                      </div>
                                        </li>
        								 <li class="single-select error">
                                            <label>Zip/Postal Code </label>
                                                <form:input path="user.zip" name="zip" id="zip" ng-model="gamma" ng-change="validateField(searchform,&#39;gamma&#39;)" class="ng-pristine ng-untouched ng-valid"/>
                                      <div ng-show="searchform.theta.$touched" class="ng-hide">
                                            <div ng-messages="searchform.theta.$error" class="errors ng-inactive">
                                            </div>
                                      </div>

                                    <li class="single-select required">
                                        <label>Country</label>
                                        <form:select path="user.countryCode" id="countryCode" name="countryCode">
                                                <form:options items="${countries}" itemValue="countryCode" itemLabel="countryName" />
                                            </form:select>
                                        <p class="validation-error"></p>
                                        </li>


                                </ul>
        				<table width="100%"><tr>

        				<td width="45%" align="center">

        				 <input type="submit" name="submit2" value="submit" onClick="getPage('saveSubscription');" >
        				  </td>
        				  <td width="10%" align="center">&nbsp;</td>
        				<td width="45%" align="center">
                          <input type="submit" name="submit3" value="cancel" onClick="getPage('welcomeSAdmin');" >
        	</td>
        		</tr></table>

        		<!--<form:input path="username" id="username" type="hidden" name="username"  />-->
        		<form:input path="adminPrivilegeId" id="adminPrivilegeId" type="hidden" name="adminPrivilegeId"  />
                </form:form>


                </section>
                   </td></tr></table>

                </main>
        </div>


        </div></div>



        </body></html>
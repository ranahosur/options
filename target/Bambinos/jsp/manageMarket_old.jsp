<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
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
                    <li ><a ng-href="/welcomeSAdmin"  href="#" onClick="getPage2('welcomeSAdmin');">Subscription View</a></li>
                    <li ><a ng-href="/logout"  href="#" onClick="getPage2('addSubscription');">New Subscription</a></li>
                    <li><a href="#" onClick="getPage2('inputFileMarket');">Upload Market Data</a></li>
                    <li class="active"><a href="#" onClick="getPage2('manageMarket');">Manage Market</a></li>
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

            <c:if test="${empty optionDetails}">

               	<center>
               	<table border="0" cellspacing="0" width="90%">
                 	<tr>
                       <td width="100%" align="center"><font face="Arial" size="4" color=blue>
                Currently there is no market data
                </font></td>
                        </tr>
                       <tr></tr>
                    </table>
                    </center>
              </c:if>
              <c:if test="${not empty optionDetails}">


                <table cellpadding="0" cellspacing="0">
                <thead>
                   <tr>
                    <td>Symbol</td>
                    <td>Stock Price</td>
                    <td>Expiry Date</td>
                    <td>Call Open Int</td>
                    <td>Call OI Change</td>
                    <td>Call Volume</td>
                    <td>Call IV</td>
                    <td>Call LTP</td>
                    <td>Call Net Change</td>
                    <td>Call Bid Qty</td>
                    <td>Call Bid  Price</td>
                    <td>Call Ask Price</td>
                    <td>Call Ask Qty</td>
                    <td>Strike Price</td>
                    <td>Put Bid Qty</td>
                    <td>Put Bid  Price</td>
                    <td>Put Ask Price</td>
                    <td>Put Ask Qty</td>
                    <td>Put Net Change</td>
                    <td>Put LTP</td>
                    <td>Put IV</td>
                    <td>Put Volume</td>
                    <td>Put OI Change</td>
                     <td>Put Open Int</td>
                    <td>Action</td>
                   </tr>
                </thead>
                <c:forEach var="optionDetail" items="${optionDetails}">

                <tbody ng-model="forfutref" ng-repeat="xx in vollist" class="ng-pristine ng-untouched ng-valid ng-scope">
                    <tr>

                        <td class="ng-binding"> ${optionDetail.symbol}  </td>
                        <td class="ng-binding"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${optionDetail.stockPrice}"/></td>
                        <td class="ng-binding">${optionDetail.expiryDate}</td>
                        <td class="ng-binding">${optionDetail.callOpenInt}</td>
                        <td class="ng-binding">${optionDetail.callOpenIntChange}</td>
                        <td class="ng-binding">${optionDetail.callVolume}</td>
                        <td class="ng-binding"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${optionDetail.callImpliedVolatility}"/></td>
                        <td class="ng-binding"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${optionDetail.callLastTradedPrice}"/></td>
                        <td class="ng-binding"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${optionDetail.callNetChange}"/></td>
                        <td class="ng-binding">${optionDetail.callBidQty}</td>
                        <td class="ng-binding"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${optionDetail.callBidPrice}"/></td>
                        <td class="ng-binding"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${optionDetail.callAskPrice}"/></td>
                        <td class="ng-binding">${optionDetail.callAskQty}</td>
                        <td class="ng-binding"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${optionDetail.strikePrice}"/></td>
                        <td class="ng-binding">${optionDetail.putBidQty}</td>
                        <td class="ng-binding"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${optionDetail.putBidPrice}"/></td>
                        <td class="ng-binding"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${optionDetail.putAskPrice}"/></td>
                        <td class="ng-binding">${optionDetail.putAskQty}</td>
                        <td class="ng-binding"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${optionDetail.putNetChange}"/></td>
                         <td class="ng-binding"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${optionDetail.putLastTradedPrice}"/></td>
                         <td class="ng-binding"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${optionDetail.putImpliedVolatility}"/></td>
                         <td class="ng-binding">${optionDetail.putVolume}</td>
                         <td class="ng-binding"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${optionDetail.putOpenIntChange}"/></td>
                         <td class="ng-binding"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${optionDetail.putOpenInt}"/></td>
                        <td><button type="button" onClick="getPage('manageOptionData','${optionDetail.optionDetailId}');">View/Edit</button></td>
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
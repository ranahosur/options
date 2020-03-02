<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<
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
            document.ConsData.selectTeamId.value=selectedId;
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

            function editPortfolio(){
                document.ConsData.screenMode.value="edit";
                getPage2('editPortfolio');
            }
          </script>
    <body style="" class="page-vol-surface">
        <form:form name="ConsData" modelAttribute="user" action="welcomeParticipant" onSubmit='return validate()'>
        <!-- ngView:  --><div ng-view="" class="ng-scope">
        <div ng-controller="MVolCtrl" changebody="" ng-init="getlist()" class="ng-scope">
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
                    <li class="active" ><a ng-href="#"  href="#" onClick="getPage2('welcomeParticipant');">Current Portfolio</a></li>
                    <li ><a ng-href="/logout"  href="#" onClick="getPage2('manageMarket');">Trade</a></li>
                    <li><a href="#">Transaction History</a></li>

                </ul>
            </div>
            <div class="header-block misc-area">
                <ul class="group">
                    <li><a ng-href="/logout" onClick="getPage2('logout')" href="#">Sign out</a></li>
                    <li><a ng-href="#" href="http://www.twtradr.com/#">Hello, <span class="ng-binding">${user.firstName} &nbsp;&nbsp;${user.lastName}</span></a></li>
                </ul>
            </div>
            <div class="gradient-line"></div>
        </header>
    <main>
        <section class="content">
        <div class="header-block misc-area">
        <table width="60%" align="center">
                <tr><td>Subscription Details</td></tr>
                </table>

        </div>
        <table width="60%" align="center">
                        <tr><td>Total Available Funds ${participant.totalFunds}</td></tr>
         </table>

           <div class="table-data sort">

            <c:if test="${empty participantTransactions}">

               	<center>
               	<table border="0" cellspacing="0" width="90%">
                 	<tr>
                       <td width="100%" align="center"><font face="Arial" size="4" color=blue>
                Portfolio yet to open
                </font></td>
                        </tr>
                       <tr></tr>
                    </table>
                    </center>
              </c:if>
              <c:if test="${not empty participantTransactions}">


                <table cellpadding="0" cellspacing="0">
                <thead>
                   <tr>
                    <td>Stock Name</td>
                    <td>Symbol</td>
                    <td>Option Type</td>
                    <td>Strike Price</td>
                    <td>Expiry Date</td>
                    <td>Number of Lots  <input type="submit" id="submit2" name="submit2" onclick="editPortfolio();" value="Edit"></a></td>
                    <td>Entry Price</td>
                    <td>Current Market Price</td>
                    <td>Profit / Loss</td>
                   </tr>
                </thead>
                <c:forEach var="participantTransaction" items="${participant.participantTransactions}">

                <tbody ng-model="forfutref" ng-repeat="xx in vollist" class="ng-pristine ng-untouched ng-valid ng-scope">
                    <tr>

                        <td class="ng-binding"> ${participantTransaction.stockName} </td>
                        <td class="ng-binding">${participantTransaction.symbol}</td>
                        <td class="ng-binding">${participantTransaction.optionType}</td>
                        <td class="ng-binding"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${participantTransaction.strikePrice}"/></td>
                        <td class="ng-binding">${participantTransaction.expiryDate}</td>
                        <td class="ng-binding">${participantTransaction.lotCount}</td>
                        <td class="ng-binding"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${participantTransaction.entryPrice}"/></td>
                        <td class="ng-binding"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${participantTransaction.currentMarketPrice}"/></td>
                        <td class="ng-binding"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${participantTransaction.profitLoss}"/></td>


                    </tr>
                </tbody>
                </c:forEach>
                </table>
                </c:if>
                <input type="hidden" name="selectParticipantTransactionId"  />
                <input type="hidden" name="screenMode" value="${screenMode}"  />
</form:form>
</table></div></section></main></div></div></div>

</body></html>
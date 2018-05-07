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

          </script>
    <body style="" class="page-vol-surface">
        <form:form name="ConsData" modelAttribute="marketDataView" action="welcomeParticipant" onSubmit='return validate()'>
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
                    <li><a ng-href="/welcomeSAdmin"  href="#" onClick="getPage2('welcomeParticipant');">Current Portfolio</a></li>
                    <li class="active" ><a ng-href="/logout"  href="#" onClick="getPage2('manageMarket');">Trade</a></li>
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
           <div class="table-data sort">
                <table cellpadding="0" cellspacing="0">
                <thead>
                   <tr>
                    <td>Stock Name</td>
                    <td>Symbol</td>
                    <td>Option Type</td>
                    <td>Strike Price</td>
                    <td>Expiry Date</td>
                    <td>Number of Lots</td>
                    <td>Transaction Type</td>
                    <td>Entry Price</td>
                    <td>Current Stock Price</td>

                   </tr>
                </thead>
                <c:forEach var="optionDetail" items="${marketDataView.optionDetails}" varStatus="i" begin="0" >
                    <c:if test="${(optionDetail.callLotInput < 0)  || (optionDetail.callLotInput > 0)}">
                    <tbody ng-model="forfutref" ng-repeat="xx in vollist" class="ng-pristine ng-untouched ng-valid ng-scope">
                        <tr>

                        <td class="ng-binding"> ${optionDetail.name} </td>
                        <td class="ng-binding">${optionDetail.symbol}</td>
                        <td class="ng-binding">Call</td>
                        <td class="ng-binding"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${optionDetail.strikePrice}"/></td>
                        <td class="ng-binding">${optionDetail.expiryDate}</td>
                        <td class="ng-binding">${optionDetail.callLotInput}</td>
                        <c:if test="${(optionDetail.callLotInput < 0)}">
                            <td class="ng-binding">Sell</td>
                            <td class="ng-binding"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${optionDetail.callBidPrice}"/></td>
                        </c:if>
                        <c:if test="${(optionDetail.callLotInput > 0)}">
                            <td class="ng-binding">Buy</td>
                            <td class="ng-binding"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${optionDetail.callAskPrice}"/></td>
                        </c:if>

                        <td class="ng-binding"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${optionDetail.stockPrice}"/></td>


                     </tr>
                    </tbody>
                </c:if>

                <c:if test="${(optionDetail.putLotInput < 0)  || (optionDetail.putLotInput > 0)}">
                    <tbody ng-model="forfutref" ng-repeat="xx in vollist" class="ng-pristine ng-untouched ng-valid ng-scope">
                        <tr>
                            <td class="ng-binding"> ${optionDetail.name} </td>
                            <td class="ng-binding">${optionDetail.symbol}</td>
                            <td class="ng-binding">Put</td>
                            <td class="ng-binding"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${optionDetail.strikePrice}"/></td>
                            <td class="ng-binding">${optionDetail.expiryDate}</td>
                            <td class="ng-binding">${optionDetail.putLotInput}</td>
                            <c:if test="${(optionDetail.putLotInput < 0)}">
                                <td class="ng-binding">Sell</td>
                                <td class="ng-binding"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${optionDetail.putBidPrice}"/></td>
                            </c:if>
                            <c:if test="${(optionDetail.putLotInput > 0)}">
                                <td class="ng-binding">Buy</td>
                                <td class="ng-binding"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${optionDetail.putAskPrice}"/></td>
                            </c:if>
                            <td class="ng-binding"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${optionDetail.stockPrice}"/></td>
                        </tr>
                    </tbody>
                </c:if>
                <c:if test="${(optionDetail.putLotInput < 0)  || (optionDetail.putLotInput > 0) || (optionDetail.callLotInput < 0)  || (optionDetail.callLotInput > 0)}">
                     <form:hidden path="optionDetails[${i.index}].optionDetailId" id="optionDetailId${i.index}" />
                      <form:hidden path="optionDetails[${i.index}].callBidPrice" id="callBidPrice${i.index}" />
                      <form:hidden path="optionDetails[${i.index}].callAskPrice" id="callAskPrice${i.index}" />
                      <form:hidden path="optionDetails[${i.index}].putAskPrice" id="putAskPrice${i.index}" />
                      <form:hidden path="optionDetails[${i.index}].putBidPrice" id="putBidPrice${i.index}" />
                      <form:hidden path="optionDetails[${i.index}].name" id="name${i.index}" />
                      <form:hidden path="optionDetails[${i.index}].symbol" id="symbol${i.index}" />
                      <form:hidden path="optionDetails[${i.index}].strikePrice" id="strikePrice${i.index}" />
                      <form:hidden path="optionDetails[${i.index}].stockPrice" id="stockPrice${i.index}" />
                      <form:hidden path="optionDetails[${i.index}].expiryDate" id="expiryDate${i.index}" />
                      <form:hidden path="optionDetails[${i.index}].callLotInput" id="callLotInput${i.index}" />
                      <form:hidden path="optionDetails[${i.index}].putLotInput" id="putLotInput${i.index}" />
                      <form:hidden path="optionDetails[${i.index}].participantTransactionId" id="participantTransactionId${i.index}" />
                      <form:hidden path="optionDetails[${i.index}].optionType" id="optionType${i.index}" />
                  </c:if>
                </c:forEach>
                </table>
                <form:hidden path="selectedStock" id="selectedStock" />
                <form:hidden path="selectedDate" id="selectedDate" />
                <input type="hidden" name="screen" value="edit"  />
                <input type="hidden"  name="screenMode" value="confirmSave"/>
                <form:hidden path="previousScreen" />
                <table width="100%"><tr>

                        				<td width="25%" align="center">
                                        <c:if test="${marketDataView.previousScreen != 'editPortfolio'}">
                        				 <input type="submit" name="submit2" value="Edit" onClick="getPage('manageMarket');" >
                        				  </td>
                        				 </c:if>
                        				 <c:if test="${marketDataView.previousScreen == 'editPortfolio'}">
                                             <input type="submit" name="submit2" value="Edit" onClick="getPage('editPortfolio');" >
                                              </td>
                                          </c:if>
                        				  <td width="10%" align="center">&nbsp;</td>
                        				<td width="25%" align="center">
                                          <input type="submit" name="submit3" value="Trade" onClick="getPage('saveMarket');" >
                        	            </td>
                        	             <td width="10%" align="center">&nbsp;</td>
                                        <td width="25%" align="center">
                                          <input type="submit" name="submit3" value="Cancel" onClick="getPage('welcomeParticipant');" >
                                        </td>
                        		</tr></table>
</form:form>



</table></div></section></main></div></div></div>

</body></html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
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
       	  	<link href="${pageContext.servletContext.contextPath}/css/style.css" rel="stylesheet" type="text/css">
            <link href="${pageContext.servletContext.contextPath}/css/live_market.css" rel="stylesheet" type="text/css">
            <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/skin.css">


    </head>
    <style>
    .tphead {
        color: #EF620D;
        padding: 0;
    }

    .srchpnl, .srchpnl2 {
        font-size: 1.1em;
        margin: 10px 0 0;
    }
    </style>
    <script>


          	function getPage2(obj)
            {
            var url=obj;

            document.ConsData.action=url;
            document.ConsData.method="POST";
            document.ConsData.submit();
            }

            function submitSymbolChange(obj)
            {
            document.ConsData.selectedDate.value = "";
            getPage2(obj);
            }

            function validate(){
                var url = document.ConsData.submitAction.value;
                document.ConsData.action=url;
                document.ConsData.method="POST";
                document.ConsData.submit();
                return true;

            }

          </script>
    <body style="" class="page-vol-surface">
        <form:form name="ConsData" modelAttribute="marketDataView" action="manageMarket" onSubmit='return validate()'>
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
                 <c:if test="${empty participantRole}">
                    <li ><a ng-href="/welcomeSAdmin"  href="#" onClick="getPage2('welcomeSAdmin');">Subscription View</a></li>
                    <li ><a ng-href="/logout"  href="#" onClick="getPage2('addSubscription');">New Subscription</a></li>
                    <li><a href="#" onClick="getPage2('inputFileMarket');">Upload Market Data</a></li>
                    <li class="active"><a href="#" onClick="getPage2('manageMarket');">Manage Market</a></li>
                    <li><a href="http://www.twtradr.com/#">Manage News</a></li>
                  </c:if>
                  <c:if test="${not empty participantRole}">
                      <li><a ng-href="/welcomeSAdmin"  href="#" onClick="getPage2('welcomeParticipant');">Current Portfolio</a></li>
                      <li  class="active" ><a ng-href="/logout"  href="#" onClick="getPage2('manageMarket');">Trade</a></li>
                      <li><a href="#">Transaction History</a></li>
                    </c:if>
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
        </div></div></div>
    <table width="100%">
    				<tbody><tr></tr>

    				<tr><td height="58"> </td></tr>
    				</tbody></table>
        <main>
           <div class="content_big">
           		<div class="black_overlay" style="display:none"></div>
           		<div id="wrapper_btm">
           			<div class="main_content">

    				<div class="srchpnl" style="padding:2px 0 2px 5px;height:25px;">

    				<b>View Options Contracts for:</b> <span>


                        <form:select path="selectedStock" style="width:100px" id="selectedStock" name="selectedStock" action="manageMarket" onchange="getPage2('manageMarket');" class="goodTextBox">
                        <option value="select" selected="">Select Stock</option>
                          <form:options items="${stocks}" itemValue="symbol" itemLabel="symbol" />
                        </form:select>

    					</span>

    				<b>Filter by:</b><span> Expiry Date


    					<form:select path="selectedDate" style="width:100px" id="selectedDate" name="selectedDate" onchange="getPage2('manageMarket');" class="goodTextBox">
    					    <option value="select" selected="">Select Date</option>
                            <form:options items="${expiryDates}" itemValue="expiryDate" itemLabel="expiryDateLabel" />
                        </form:select>
    					</span>



    				</div>
                <div class="opttbldata">
                    <div id="hdoctable" style="visibility: hidden; top: 0px; width: 0px;"></div><table id="octable" width="100%" border="0" cellpadding="0" cellspacing="0">
                    <thead>
                        <tr>
                        <c:if test="${not empty participantRole}">
                            <th colspan="11">CALLS</th>
                            <th>&nbsp;</th>
                            <th colspan="11">PUTS</th>
                        </c:if>
                        <c:if test="${empty participantRole}">
                            <th colspan="10">CALLS</th>
                            <th>&nbsp;</th>
                            <th colspan="10">PUTS</th>
                        </c:if>
                        </tr>
                        <tr>
                            <!-- <th>Quote </th>   -->
                            <c:if test="${not empty participantRole}">
                                <th title="Call Lot Count">Call Lot Count</th>
                            </c:if>
                            <th title="Open Interest">OI</th>
                            <th title="Change in Open Interest">Chng in OI</th>
                            <th title="Traded Volume">Volume</th>
                            <th title="Implied Volatility">IV</th>
                            <th title="Last Traded Price">LTP</th>
                            <!--**-->
                            <th title="Net Change">Net Chng</th>

                            <th title="Bid Quantity">Bid<br>Qty</th>
                            <th title="Bid Price">Bid<br>Price</th>
                            <th title="Ask Price">Ask<br>Price</th>
                            <th title="Ask Quantity">Ask<br>Qty</th>
                            <th title="Strike Price">Strike Price</th>
                            <th title="Bid Quantity">Bid<br>Qty</th>
                            <th title="Bid Price">Bid<br>Price</th>
                            <th title="Ask Price">Ask<br>Price</th>
                            <th title="Ask Quantity">Ask<br>Qty</th>
                            <th title="Net Change">Net Chng</th>
                            <th title="Last Traded Price">LTP</th>
                            <th title="Implied Volatility">IV</th>
                            <th title="Traded Volume">Volume</th>
                            <th title="Change in Open Interest">Chng in OI</th>
                            <th title="Open Interest">OI</th>
                            <c:if test="${not empty participantRole}">
                                <th title="Put Lot Count">Put Lot Count</th>
                            </c:if>

                        </tr>
                        </thead>



                <c:forEach var="optionDetail" items="${marketDataView.optionDetails}" varStatus="i" begin="0" >

                <tbody><tr>
                        <c:if test="${not empty participantRole}">
                            <td class="ylwbg" align="center"><form:input path="optionDetails[${i.index}].callLotInput" id="callLotInput${i.index}"  style="width: 5em"  min="-999999" max="999999"  type="number"/></td>
                        </c:if>
                        <td class="ylwbg">${optionDetail.callOpenInt}</td>
                        <td class="ylwbg">${optionDetail.callOpenIntChange}</td>
                        <td class="ylwbg">${optionDetail.callVolume}</td>
                        <td class="ylwbg"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${optionDetail.callImpliedVolatility}"/></td>
                        <td class="ylwbg"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${optionDetail.callLastTradedPrice}"/></td>
                        <td class="ylwbg"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${optionDetail.callNetChange}"/></td>
                        <td class="ylwbg">${optionDetail.callBidQty}</td>
                        <td class="ylwbg"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${optionDetail.callBidPrice}"/></td>
                        <td class="ylwbg"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${optionDetail.callAskPrice}"/></td>
                        <td class="ylwbg">${optionDetail.callAskQty}</td>
                        <td class="grybg"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${optionDetail.strikePrice}"/></td>
                        <td class="nobg">${optionDetail.putBidQty}</td>
                        <td class="nobg"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${optionDetail.putBidPrice}"/></td>
                        <td class="nobg"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${optionDetail.putAskPrice}"/></td>
                        <td class="nobg">${optionDetail.putAskQty}</td>
                        <td class="nobg"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${optionDetail.putNetChange}"/></td>
                         <td class="nobg"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${optionDetail.putLastTradedPrice}"/></td>
                         <td class="nobg"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${optionDetail.putImpliedVolatility}"/></td>
                         <td class="nobg">${optionDetail.putVolume}</td>
                         <td class="nobg"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${optionDetail.putOpenIntChange}"/></td>
                         <td class="nobg"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${optionDetail.putOpenInt}"/></td>
                          <c:if test="${not empty participantRole}">
                             <td class="ylwbg" align="center"><form:input path="optionDetails[${i.index}].putLotInput" id="putLotInput${i.index}"   style="width: 5em"  min="-999999" max="999999"  type="number"/></td>
                         </c:if>
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
                    </tr>
                </tbody>
                </c:forEach>
                </table>
                <c:if test="${not empty participantRole}">
                    <table width="100%"><tr>

                                <td width="45%" align="center">

                                 <input type="submit" id="submit2" name="submit2" onclick="submitAction.value = 'confirmSave';" value="submit">
                                  </td>
                                  <td width="10%" align="center">&nbsp;</td>
                                <td width="45%" align="center">
                                  <input type="submit" id="submit2" name="submit2" onclick="submitAction.value = 'welcomeParticipant';" value="cancel"  >
                    </td>
                        </tr></table>
                </c:if>
<input type="hidden" name="selectAdminPrivilegeId"  />
<input type="hidden" name="submitAction">
</form:form>



</table></div></div></div></div></main></div></div></div>

</body></html>
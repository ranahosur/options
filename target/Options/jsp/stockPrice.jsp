<!DOCTYPE html>
<html lang="en" ng-app="springPortfolio">
<head>
    <meta charset="utf-8">
    <title>Stock Trading Portfolio</title>
    <meta http-equiv="Cache-Control" content="no-store, no-cache, must-revalidate, max-age=0">
    <link href="webjars/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="webjars/flat-ui/bcaf2de95e/css/flat-ui.css" rel="stylesheet">
    <link href="css/portfolio.css" rel="stylesheet">
</head>
<body>
<div class="container" ng-controller="PortfolioController">

    <nav class="navbar navbar-default" role="navigation">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-collapse-01">
                <span class="sr-only">Toggle navigation</span>
            </button>
            <a class="navbar-brand" href="#">Stock Trading Portfolio</a>
        </div>
        <div class="collapse navbar-collapse" id="navbar-collapse-01">
            <a class="btn btn-default navbar-btn navbar-right logout" href="logout.html" target="_self" ng-click="logout()">
                <span class="glyphicon glyphicon glyphicon-off"></span>
            </a>
        </div>
    </nav>
    <div id="main-content">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Company</th>
                <th>Ticker</th>
                <th class="number">Price</th>
                <th class="number">Change</th>
                <th class="number">%</th>
                <th class="number">Shares</th>
                <th class="number">Value</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <tr ng-repeat="(ticker, position) in positions">
                <td>{{position.company}}</td>
                <td>{{position.ticker}}</td>
                <td class="number">{{position.price | currency:"$"}}</td>
                <td class="number">
                    <span ng-show="position.change > 0" class="glyphicon glyphicon-arrow-up"></span>
                    <span ng-show="position.change == 0" class="glyphicon glyphicon-arrow-right"></span>
                    <span ng-show="position.change < 0" class="glyphicon glyphicon-arrow-down"></span>
                </td>
                <td class="number">{{position.change | percent:position.price}}</td>
                <td class="number">{{position.shares | number}}</td>
                <td class="number">{{position.price * position.shares | currency:"$"}}</td>
                <td class="trade-buttons">
                    <button class="btn btn-primary" ng-click="openTradeModal('buy', position)">Buy</button>
                    <button class="btn btn-primary" ng-click="openTradeModal('sell', position)">Sell</button>
                </td>
            </tr>
            </tbody>
            <tfoot>
            <tr>
                <td colspan="5">Total</td>
                <td class="number">{{positions | totalPortfolioShares | number}}</td>
                <td class="number">{{positions | totalPortfolioValue | currency:"$"}}</td>
                <td></td>
            </tr>
            </tfoot>
            <tbody></tbody>
        </table>
    </div>
    <div class="alert alert-warning">
        <h5>Notifications</h5>
        <ul>
            <li ng-repeat="notification in notifications">{{notification}}</li>
        </ul>
    </div>
</div>
<script type="text/ng-template" id="tradeModal.html">
    <form class="form-horizontal">
        <div class="modal-header">
            <h4 class="modal-title">{{ action }} {{ position.ticker }} Stock</h4>
        </div>
        <div class="modal-body">
            <div class="control-group">
                <label class="control-label" for="inputShares">Shares</label>
                <div class="controls">
                    <input id="inputShares" type="text" ng-model="numberOfShares">
                </div>
            </div>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal" ng-click="cancel()">Close</button>
            <button type="button" class="btn btn-primary" ng-click="trade()">Trade</button>
        </div>
    </form>
</script>

<!-- 3rd party -->
<script src="${pageContext.servletContext.contextPath}/webjars/jquery/jquery.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/webjars/bootstrap/js/bootstrap.js"></script>
<script src="${pageContext.servletContext.contextPath}/webjars/sockjs-client/sockjs.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/webjars/webstomp-client/dist/webstomp.js"></script>
<script src="${pageContext.servletContext.contextPath}/webjars/angular/angular.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/webjars/angular-ui-bootstrap-bower/ui-bootstrap.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/webjars/angular-ui-bootstrap-bower/ui-bootstrap-tpls.min.js"></script>

<!-- application -->
<script src="${pageContext.servletContext.contextPath}/js/app.js"></script>
<script src="${pageContext.servletContext.contextPath}/js/controllers.js"></script>
<script src="${pageContext.servletContext.contextPath}/js/services.js"></script>

</body>
</html>

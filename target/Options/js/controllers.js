angular.module('optionsPortfolio.controllers', ['ui.bootstrap'])
    .constant("buy", "Buy")
    .constant("sell", "Sell")
    .controller('OptionsPortfolioController',
    ['$scope', '$uibModal', 'OptionsTradeService',
    function ($scope, $uibModal, optionsTradeService) {
        $scope.notifications = [];
        $scope.positions = {};

        var processQuote = function(quote) {
            var existing = $scope.positions[quote.optionDetailId];
            if(existing) {
                existing.stockPrice = quote.stockPrice;
            }
        };
        var udpatePosition = function(position) {
            var existing = $scope.positions[position.ticker];
            if(existing) {
                existing.shares = position.shares;
            }
        };
        var pushNotification = function(message) {
            $scope.notifications.unshift(message);
        };


        $scope.logout = function() {
            optionsTradeService.disconnect();
        };

        optionsTradeService.connect("${pageContext.servletContext.contextPath}/optionsPortfolio")
            .then(function (username) {
                    username = 'paulson';
                    $scope.username = 'paulson';
                    pushNotification("Trade results take a 2-3 second simulated delay. Notifications will appear.");
                    return optionsTradeService.loadPositions();
                },
                function (error) {
                    pushNotification(error);
                })
            .then(function (positions) {
                positions.forEach(function(pos) {
                    $scope.positions[pos.optionDetailId] = pos;
                });
                optionsTradeService.fetchQuoteStream().then(null, null,
                    function(quote) {
                        processQuote(quote);
                    }
                );
                optionsTradeService.fetchErrorStream().then(null, null,
                    function (error) {
                        pushNotification(error);
                    }
                );
            });

    }]);
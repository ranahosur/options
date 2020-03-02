angular.module('optionsPortfolio.controllers', ['ui.bootstrap'])
    .constant("buy", "Buy")
    .constant("sell", "Sell")
    .controller('OptionsPortfolioController',
    ['$scope', '$uibModal', 'OptionsTradeService',
    function ($scope, $uibModal, optionsTradeService) {
        $scope.notifications = [];
        $scope.positions = {};

        var processQuote = function(quote) {
            console.debug('quote is '+ quote);
            console.debug('quote.optionDetailId is '+ quote.optionDetailId);
            var existing = $scope.positions[quote.optionDetailId];
            if(existing) {
                existing.stockPrice = quote.stockPrice;
                existing.callBidPrice = quote.callBidPrice;
                existing.callAskPrice = quote.callAskPrice;
                existing.putBidPrice = quote.putBidPrice;
                existing.putAskPrice = quote.putAskPrice;
            }
        };
        var udpatePosition = function(position) {
            var existing = $scope.positions[position.optionDetailId];
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

        optionsTradeService.connect("/Options/optionsPortfolio")
            .then(function (username) {
                    console.debug('connecting with username '+ username);
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
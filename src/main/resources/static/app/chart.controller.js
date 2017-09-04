'use strict';

    app.controller("ChartController",['$scope', '$http',
        function($scope, $http) {
            //Gets data to populate the graphs
            $scope.jobAvgData = {
                chart: {}
                ,
                data:[]
            };
            var chartdata = [];
            var chartPromise = $http.get("/top10bysalary");
            chartPromise.then(function(response){
                chartdata  = response.data;
                $scope.jobAvgData = {
                    chart: {
                        caption: "Top 10 Highest Paid Jobs",
                        numberPrefix: "$"
                    },
                    data: chartdata
                }
            })

            //creates new chart based on filters
            $scope.filter = function () {
                var job = (document.getElementById("jobChart").value);
                var url = "/top10filtered?job=" + job;
                var subcaption = job+" jobs"
                //opens new window to update information as an iframe so url is not displayed
                var chartPromise = $http.get(url);
                chartPromise.then(function(response){
                    chartdata  = response.data;
                    $scope.jobAvgData = {
                        chart: {
                            caption: "Top 10 Highest Paid Jobs",
                            subcaption: subcaption,
                            numberPrefix: "$"
                        },
                        data: chartdata
                    }
            })}

        }]);

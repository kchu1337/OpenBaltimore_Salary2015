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
                        "caption": "Highest Paid Jobs",
                        "subcaption": "Top 10"
                    },
                    data: chartdata
                }
            })
        }]);

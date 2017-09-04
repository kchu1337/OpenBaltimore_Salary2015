'use strict';

    app.controller("ChartController",['$scope', '$http',
        function($scope, $http) {
            //Retreives values for highestest and lowest salaries
            $scope.salarySlider = {
                value: 1000,
                options: {
                    floor: 1000,
                    ceil: 5000,
                    step: 1000,
                }
            };
            var minPromise = $http.get("/minsalary");
            minPromise.then(function(response) {
                var minSalary = response.data;
                $scope.salarySlider.options.floor=minSalary;
            })

            var maxPromise = $http.get("/maxsalary");
            maxPromise.then(function(response) {
                var maxSalary = response.data;
                $scope.salarySlider.options.ceil=maxSalary;
            })

            $scope.countSlider = {
                value: 1,
                options: {
                    floor: 1,
                    ceil: 25,
                    step: 1,
                }
            };

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
                var salary = $scope.salarySlider.value;
                var count = 1;
                var url = "/top10filtered?job=" + job+"&count="+count+"&salary="+salary;
                var subcaption = job+" jobs" + " with maximum salary <= $" +salary +
                    " and with a job count >= " +count
                //opens new window to update information as an iframe so url is not displayed
                var chartPromise = $http.get(url);
                chartPromise.then(function(response){
                    chartdata  = response.data;
                    $scope.jobAvgData = {
                        chart: {
                            caption: "Highest Paid Jobs",
                            subcaption: subcaption,
                            numberPrefix: "$"
                        },
                        data: chartdata
                    }
            })}
        }]);

'use strict';

app.controller("GraphController",['$scope', '$http',
    function($scope, $http) {

        //Creates salary Slider
        $scope.salarySlider = {
            value: 0,
            options: {
                floor: 0,
                ceil: 5000,
                step: 1000,
                translate: function(value) {
                    return '$' + value;
                }
            }
        };
        //Sets min and max values for salary slider
        var minPromise = $http.get("/minsalary");
        minPromise.then(function(response) {
            var minSalary = response.data;
            $scope.salarySlider.options.floor=minSalary;
        })
        var maxPromise = $http.get("/maxsalary");
        maxPromise.then(function(response) {
            var maxSalary = response.data;
            $scope.salarySlider.options.ceil=maxSalary;
            $scope.salarySlider.value = maxSalary;
        })
        //Creates job count slider
        $scope.countSlider = {
            value: 1,
            options: {
                floor: 1,
                ceil: 25,
                step: 1,
            }
        };

        //Gets data to populate the inital graph
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
            //Gets the filters values
            var job = (document.getElementById("jobChart").value);
            var salary = $scope.salarySlider.value;
            var count = $scope.countSlider.value;;
            //calls the rest controller based on the filters
            var url = "/top10filtered?job=" + job+"&count="+count+"&salary="+salary;
            //creates subcaption based on the filters
            var subcaption = job+" jobs" + " with maximum salary <= $" +salary +
                " and with a job count >= " +count
            //updates the graph with the filtered data
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

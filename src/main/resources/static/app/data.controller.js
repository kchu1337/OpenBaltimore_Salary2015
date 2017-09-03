    'use strict';

    app.controller("DataController",['$scope', '$http','$filter',
        function($scope, $http, $filter) {
        $scope.salaryList= [];

        //Loads the data from the database and populates the chart
        var url = "/getdata";
        var salaryPromise
            = $http.get(url);
        salaryPromise.then(function(response){
            $scope.salaryList = response.data;
        });

        //Deletes the salary tuple corresponding to the id and updates the chart
        $scope.delete = function (id) {
            alert("Data set deleted");
            var url = "/delete/"+id;
            var salaryPromise = $http.get(url);
            salaryPromise.then(function(response){
                $scope.salaryList = response.data;
            });
    }
            //opens new window to update existing salary tuple with the corresponding id
            $scope.update = function (id) {
                var url = "/update/"+id;
                //opens new window to update information as an iframe so url is not displayed
                var updateWindow = window.open("about:blank","","top=100,left=300,width=500,height=400");
                updateWindow.document.write('<iframe src="'+url+'"; style="height: 100%;width: 100%;border: none;"></iframe>');
            }

            //opens window to add new salary tuple
            $scope.add = function () {
                var url = "/add"
                //opens new window to update information as an iframe so url is not displayed
                var updateWindow = window.open("about:blank","","top=100,left=300,width=500,height=400");
                updateWindow.document.write('<iframe src="'+url+'"; style="height: 100%;width: 100%;border: none;"></iframe>');
            }

            //Gets data to populate the graphs
            $scope.myDataSource = {
                chart: {
                    "caption":"Caption",
                    "subcaption":"subcaption"
                },
                data:[]
            };
            var url2 = "/top10byjobs"
            var chartdata = [];
            var chartPromise = $http.get(url2);
            chartPromise.then(function(response){
                chartdata  = response.data;
                $scope.myDataSource = {
                       chart: {
                            "caption": "Most Common Jobs",
                            "subcaption": "Top 10"
                        },
                       data: chartdata
                    }
                    })
    }]);

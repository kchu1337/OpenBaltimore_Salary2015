'use strict';

app.controller("TableController",['$scope', '$http','$filter',
    function($scope, $http, $filter) {
        $scope.salaryList= [];
        $scope.itemsPerPage = 25;

        init();

        //Creates salary Slider
        $scope.salaryTableSlider = {
            minValue: 1000,
            maxValue: 2000,
            options: {
                floor: 1000,
                ceil: 5000,
                step: 1000,
            }};
        //Sets min and max values for salary slider
        var minPromise = $http.get("/minsalary");
        minPromise.then(function(response) {
            var minSalary = response.data;
            $scope.salaryTableSlider.options.floor=minSalary;
            $scope.salaryTableSlider.minValue=minSalary;
        })
        var maxPromise = $http.get("/maxsalary");
        maxPromise.then(function(response) {
            var maxSalary = response.data;
            $scope.salaryTableSlider.options.ceil=maxSalary;
            $scope.salaryTableSlider.maxValue=maxSalary;
        })

        //Loads the data from the database and populates the chart
        function init () {
            var url = "/getdata";
            var salaryPromise
                = $http.get(url);
            salaryPromise.then(function (response) {
                $scope.salaryList = response.data;
            });
        }


        $scope.filterSalary = function () {
            var min= $scope.salaryTableSlider.minValue;
            var max= $scope.salaryTableSlider.maxValue;
            var salaryPromise = $http.get("/getdatabetween?min="+min+"&max="+max);
            salaryPromise.then(function(response){
                $scope.salaryList = response.data;
            });
        }

        $scope.reset = function () {
            init();
        }


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

        //Changes the number of items displayed per table page
        $scope.page25 = function () {
           $scope.itemsPerPage = 25;
        }
        $scope.page50 = function () {
            $scope.itemsPerPage = 50;
        }
        $scope.page100 = function () {
            $scope.itemsPerPage = 100;
        }
    }]);

'use strict';

app.controller("TableController",['$scope', '$http','$filter', '$timeout',
    function($scope, $http, $filter, $timeout) {

        $scope.salaryList= [];
        $scope.itemsPerPage = 25;
        var updateWindow;

        //initializes the table
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
            alert("Salary Filterd");
        }

        $scope.reset = function () {
            init();
            alert("Salary Filter reset");
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
            updateWindow = window.open("about:blank","","top=100,left=300,width=500,height=400");
            updateWindow.document.write
            ('<iframe src="'+url+'"; style="height: 100%;width: 100%;border: none;"></iframe>');

        }

        //opens window to add new salary tuple
        $scope.add = function () {
            var url = "/add"
            //opens new window to update information as an iframe so url is not displayed
            updateWindow = window.open("about:blank","","top=100,left=300,width=500,height=400");
            updateWindow.document.write
            ('<iframe src="'+url+'"; style="height: 100%;width: 100%;border: none;"></iframe>');
        }
        
        //If the cancel button is clicked on the update/add window, closes the window
        $scope.closeWin = function () {
            updateWindow.close();
        };

        //If the submit button is clicked on the update/add window,
        // closes the window and alerts the user that the table has been updated
        $scope.closeWinUpdate = function () {
            updateWindow.close();
            $timeout( function(){
                init();
                alert("data updated");
            }, 500 );
        };

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

    'use strict';

    app.controller("DataController",['$scope', '$http','$filter',
        function($scope, $http, $filter) {
        $scope.salaryList= [];
        var url = "/getdata";
        var salaryPromise
            = $http.get(url);
        salaryPromise.then(function(response){
            $scope.salaryList = response.data;
        });

        $scope.delete = function (id) {
            alert("Data set deleted");
            var url = "/delete/"+id;
            var salaryPromise = $http.get(url);
            salaryPromise.then(function(response){
                $scope.salaryList = response.data;
            });
    }

            $scope.update = function (id) {
                var url = "/update/"+id;
                window.open("/update/"+id,"","top=400,left=400,width=400,height=400");
                //opens new window to update information as an iframe so url is not displayed
                //var updateWindow = window.open("about:blank","","top=400,left=400,width=400,height=400");
                //updateWindow.document.write('<iframe src="'/update/'+id"; style="height: 100%;width: 100%;border: none;"></iframe>');
            }
    }]);

    'use strict';



    app.controller("DataController",['$scope', '$http','$filter', function($scope, $http, $filter) {
        $scope.salaryList= [];
        var url = "/getdata";
        var salaryPromise
            = $http.get(url);
        salaryPromise.then(function(response){
            $scope.salaryList = response.data;
        });

    }]);


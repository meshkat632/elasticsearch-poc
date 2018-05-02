angular.module('myApp', ['mui'])
.controller('ExampleController', ['$scope', function ($scope) {
    $scope.prices = [
        {
            display: '< 500 € ',
            id: "lessThan500",
            selected: false
        },
        {
            display: '500 - 749 € ',
            id: "between500and749",
            selected: false
        },
        {
            display: '750 - 999 € ',
            id: "between750and999",
            selected: false
        },
        {
            display: '1000 - 2499 € ',
            id: "between1000and2499",
            selected: false
        },
        {
            display: '2500 - 4999 € ',
            id: "between2500and4999",
            selected: false
        },
        {
            display: '> = 4999 € ',
            id: "heigherThan4999",
            selected: false
        }
    ];                

    $scope.toggleSelection = function (price) {
        console.log("toggleSelection price", price);
        price.selected = !price.selected;
    }                

}]);
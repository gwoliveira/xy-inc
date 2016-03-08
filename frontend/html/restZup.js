var app = angular.module('restZup', ['ngResource', 'ngRoute']);

app.factory('metaModeloService', ['$resource', function($resource) {
  return $resource('/RestZUP');
}]);

app.controller("modeloController", function($scope, metaModeloService) {
  $scope.meta = {
    model: "",
    fields: [{
      required: false,
      type: "String"
    }]
  };

  $scope.modelos = metaModeloService.query({});

  $scope.addCampo = function(pos) {
    $scope.meta.fields.splice(pos + 1, 0, {
      required: false,
      type: "String"
    });
  };

  $scope.enviarModelo = function(minha) {
    metaModeloService.save($scope.meta, function success() {
      $scope.modelos = metaModeloService.query({});
    });
  };

  $scope.removeCampo = function(pos) {
    if ($scope.meta.fields.length > 1) {
      $scope.meta.fields.splice(pos, 1);
    }
  };
});

app.controller("entidadeController", function($scope, $routeParams, metaModeloService) {
  $scope.params = $routeParams;
  $scope.entidade = metaModeloService.get({id:$routeParams.modelo});
  $scope.fields = [];
  console.log($scope.entidade);
});

app.config(function($routeProvider, $locationProvider) {
  $routeProvider
    .when('/', {
      templateUrl: 'modelo.html',
      controller: 'modeloController',
    })
    .when('/:modelo', {
      templateUrl: 'entidade.html',
      controller: 'entidadeController'
    });

  // configure html5 to get links working on jsfiddle
  $locationProvider.html5Mode(true);

});

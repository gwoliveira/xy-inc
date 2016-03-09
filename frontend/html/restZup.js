var app = angular.module('restZup', ['ngResource', 'ngRoute']);

app.factory('metaModeloService', ['$resource', function($resource) {
  return $resource('/RestZUP/');
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

app.controller("entidadeController", function($scope, $routeParams, $resource, metaModeloService) {

  var modeloRest;
  $scope.dados = [];
  $scope.params = $routeParams;
  $scope.entidade = metaModeloService.get({
    id: $routeParams.modelo
  }, function success(modelo) {
    modeloRest = $resource('/RestZUP/' + modelo.model + "/:id");
    $scope.dados = modeloRest.query();
  });
  $scope.fields = {};


  $scope.fieldType = function(fieldType, outra) {
    if (fieldType == "Integer" || fieldType == "Double") {
      return "number";
    }
    if (fieldType == "Boolean") {
      return "checkbox";
    }

  };
  $scope.fieldExtras = function() {
    return 'step="0.01"';
  };
  $scope.enviarEntidade = function() {
    modeloRest.save($scope.fields, function success() {
      $scope.dados = modeloRest.query();
    });
  };


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

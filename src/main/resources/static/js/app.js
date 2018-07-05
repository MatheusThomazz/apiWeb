// Criação do modulo princiapal da aplicação
var appCliente = angular.module("appCliente", []);

//Criação de controller
appCliente.controller("indexController", function($scope, $http){
	
	$scope.clientes=[];
	
	$http({method: 'GET', url:'http://localhost:8080/clientes'})
	.then(function (response){
		
		$scope.clientes=response.data;
		
		console.log(response.data);
		console.log(response.status);
		
	}, function (response){
		
		console.log(response.data);
		console.log(response.status);
		
	});

});


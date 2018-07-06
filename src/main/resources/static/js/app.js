// Criação do modulo princiapal da aplicação
var appCliente = angular.module("appCliente", []);

//Criação de controller
appCliente.controller("indexController", function($scope, $http){
	
	$scope.clientes=[];
	$scope.cliente={};
	
	$scope.carregarCliente = function(){
	$http({method: 'GET', url:'http://localhost:8080/clientes'})
	.then(function (response){
		
		$scope.clientes=response.data;
	
	}, function (response){
		
		console.log(response.data);
		console.log(response.status);
		
	});
	};
	
	$scope.carregarCliente()
	
	$scope.salvarCliente = function(){
		$http({method: 'POST', url:'http://localhost:8080/clientes', data:$scope.cliente})
		.then(function (response){
			$scope.clientes.push(response.data);
	
		}, function (response){
			
			console.log(response.data);
			console.log(response.status);
			
		});
		};
		
		$scope.carregarCliente()
		
		$scope.excluirCliente = function(cliente){
			$http({method: 'DELETE', url:'http://localhost:8080/clientes/'+ cliente.id})
			.then(function (response){
				$scope.clientes.push(response.data);
		
			}, function (response){
				
				console.log(response.data);
				console.log(response.status);
				
			});
			};
			
			
		
		

});


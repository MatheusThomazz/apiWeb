//Criação de controller
appCliente.controller("clienteController", function($scope, $http){
	
	$scope.clientes=[];
	$scope.cliente={};
	
	$scope.carregarCliente = function(){
	$http({method: 'GET', url:'/admin/clientes'})
	.then(function (response){
		
		$scope.clientes=response.data;
	
	}, function (response){
		
		console.log(response.data);
		console.log(response.status);
		
	});
	};
	
	$scope.carregarCliente()
	
	$scope.salvarCliente = function(){
		if($scope.frmCliente.$valid){
		$http({method: 'POST', url:'/admin/clientes', data:$scope.cliente})
		.then(function (response){
			$scope.clientes.push(response.data)
			$scope.carregarCliente();
			$scope.frmCliente.$setPristine();
			delete $scope.cliente;
	
		}, function (response){
			
			console.log(response.data);
			console.log(response.status);
			
		});
		}else{
			window.alert("Dados Invalidos")
			 }
		}
		
		$scope.carregarCliente();
		
		$scope.excluirCliente = function(cliente){
			$http({method: 'DELETE', url:'/admin/clientes/'+ cliente.id})
			.then(function (response){
				
				pos = $scope.clientes.indexOf(cliente);
				$scope.clientes.splice(pos, 1);
				
			//	for(i = 0; i < $scope.clientes.length; i++ ){
				//	if($scope.clientes[i].id == cliente.id){
					//	$scope.clientes.splice(i, 1);
						//break;
					//}
				//}
				
				
			}, function (response){
				
				console.log(response.data);
				console.log(response.status);
				
			});
			};
			
			
			$scope.alterarCliente = function(cli){
				
				$scope.cliente = angular.copy(cli);
			}
			
			$scope.cancelarAlterarCliente = function(){
				
				$scope.cliente = {};
			}
			
			
		
		

});
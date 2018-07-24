appCliente.controller("loginController", function($scope, $http){

	$scope.usuario = {};
	$scope.token = "";
	
	$scope.autenticar= function(){
		
		$http.post("/autenticar", $scope.usuario)
		.then(function(response){
			console.log("sucesso" + response)
			$scope.token = response.data.token;
		}, function(response){
			console.log("falha" + response)
		});
	}
	
});

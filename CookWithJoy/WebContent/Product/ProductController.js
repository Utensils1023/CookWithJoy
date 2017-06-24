app.controller('productController',[ '$scope' , '$location','productService' , function($scope ,$location, $productService) 
{   
	$scope.productname='';
	$scope.price='';
	$scope.description='';
	$scope.category='';
	$scope.quantity='';
	
   
	$scope.addproduct = function()
	{
	
			
			var json = 	{
					"productname" : $scope.productname,
					"price" : $scope.price ,
					"description" : $scope.description,
					"category" : $scope.category,
					"quantity" : $scope.quantity,
					
				
				};
	
			console.log(json);
			
			$productService.Insertproduct(json).then(function(response){
				console.log(response);
				
				$scope.ServerResponse = response.msg;
				$location.path('/Home');
				window.setTimeout(function(){
					$scope.$apply( $scope.ServerResponse = '' );					
				}, 5000);
			});
			
	}
	
	
			$scope.data=[];
		
			$productService.viewproduct(json).then(function(response){
				console.log(response);
				
				$scope.data = response.data;
					
			});
			
	
}]);
app.factory('productService', ['$http', '$q', function($http, $q){
	 

	var BASE_URL = 'http://localhost:8080/com.CookWithJoy';
	
    return {
         		Insertproduct: function(item){
         			
         			console.log( 'Insert User Service:' );
         			console.log(item);
         			
                    return $http({
                    	  method: 'POST',
                    	  url: BASE_URL + '/product',
                    	  data:item,
                    	  headers:{'Content-Type': 'application/json'}
                    	})
                            .then(
                                    function(response){
                                        return response.data;
                                    }, 
                                    function(errResponse){
                                        console.error('Error while updating User');
                                        return $q.reject(errResponse);
                                    }
                            );
            		
         		},
    		
    		
         		viewproduct: function(){
         			
         			console.log( 'Insert User Service:' );
         			
         			
                    return $http({
                    	  method: 'POST',
                    	  url: BASE_URL + '/productview',
                    	
                    	  headers:{'Content-Type': 'application/json'}
                    	})
                            .then(
                                    function(response){
                                        return response.data;
                                    }, 
                                    function(errResponse){
                                        console.error('Error while updating User');
                                        return $q.reject(errResponse);
                                    }
                            );
            		
    		}
    		
    };
                       
        		
		
    		
    
 
}])


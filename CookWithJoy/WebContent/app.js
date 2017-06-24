
var app = angular.module("myApp", ["ngRoute"]);
app.config(function($routeProvider) {
    $routeProvider
    
    .when("/Signup", {
          templateUrl : "Signup/Signup.html",
          controller: "signUpController"
    })
    .when("/Home", {
          templateUrl : "Home/Home.html",
          controller: "homeController"
    })
    .when("/login", {
          templateUrl : "login/login.html",
          controller: "loginController"
    })
    .when("/Product" ,{
           templateUrl : "Product/Product.html",
          controller: "productController"
    }) 
    .when("/ProductView" ,{
        templateUrl : "Product/ProductView.html",
       controller: "productController"
 }) 
   
});

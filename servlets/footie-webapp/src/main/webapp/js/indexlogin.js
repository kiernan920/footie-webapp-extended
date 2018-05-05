var rootURL;
	
var findAll = function() {
	myFunction();
	$.ajax({
		type : 'GET',
		url : rootURL,
		dataType : "json",
		success : renderList
	});
};

var theData;
var renderList = function(data) {
	var responseString = data.response;
	console.log(responseString);
	if(responseString == "customerrep"){
		$("#paragraph").empty();
		$("#paragraph").append("Logged in as " + responseString);
		sessionStorage.setItem('usertype', "customerrep");
		window.location.href = "customerrep.html";//Opens HTML Page
	}
	else if(responseString == "administrator"){
		$("#paragraph").empty();
		$("#paragraph").append("Logged in as " + responseString);
		sessionStorage.setItem('usertype', "admin");
		window.location.href = "adminhomepage.html";//Opens HTML Page
	}
	$("#userName1").click(function(){
		$("#paragraph").empty();
	  });
	$("#password").click(function(){
		$("#paragraph").empty();
	  });
};

function myFunction() {
	var userName = $("#userName1").val();
	var password = $("#password").val();
	rootURL = "rest/users/check/";
	rootURL += userName + "/" + password;	
}

$(document).ready(function(){
	$("#logInButton").click(function(){
		 findAll();
		 return false;
	  });
	$("#logoutbutton").click(function(){
		sessionStorage.setItem('usertype', "");
		window.location.href = "index.html";//Opens HTML Page
	  });
});
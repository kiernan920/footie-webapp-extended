$(document).ready(function(){
	$("#logoutbutton").click(function(){
		sessionStorage.setItem('usertype', "");
		window.location.href = "index.html";//Opens HTML Page
	  });
	var usertype = sessionStorage.getItem('usertype');
	console.log("session is " + usertype);
	if(usertype == "engineer"){
		$("#management").empty();
		$("#customer").empty();
		$("#admin").empty();
	}
	else if(usertype == "customerrep"){
		$("#management").empty();
		$("#engineer").empty();
		$("#admin").empty();
	}
	else if(usertype == "management"){
		$("#engineer").empty();
		$("#customer").empty();
		$("#admin").empty();
	}
	else if(usertype == "admin"){
		$("#engineer").empty();
		$("#customer").empty();
		$("#management").empty();
	}
});
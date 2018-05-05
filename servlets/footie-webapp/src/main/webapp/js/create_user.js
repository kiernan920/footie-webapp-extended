var rootURL = "";
	
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
	console.log(data);
	$("#paragraph").empty();
	$("#paragraph").append(data.response);
	
};

function myFunction() {
	var firstname = document.getElementById("firstname").value;
	var lastname = document.getElementById("lastname").value;
	
	var address = document.getElementById("address").value;
	var phone = document.getElementById("phone").value;
	var email = document.getElementById("email").value;
	var username = document.getElementById("username").value;
	var password = document.getElementById("password").value;
	var usertype = null;
	usertype = $("input[name='usertype']:checked").val();//RADIO BUTTON CODE
	var gender = $("input[name='gender']:checked").val();
	usertype = usertype.toString();
	console.log(password);
	if(firstname == "" || lastname == "" || address == ""|| phone == ""|| email == ""|| username == ""|| password == ""){
		$("#paragraph").empty();
		$("#paragraph").append("Invalid Information");
	}
	else{
		rootURL += firstname + "/" + lastname + "/" + gender + "/" + address + "/" + phone + "/" + email + "/" + username + "/" + password + "/" + usertype;	
	}
}

$(document).ready(function(){
	$( "#tabs" ).tabs();
	$("#doSearch").click(function(){
		rootURL = "rest/users/";
		 findAll();
		 return false;
	  });
	$("#logoutbutton").click(function(){
		sessionStorage.setItem('usertype', "");
		window.location.href = "index.html";//Opens HTML Page
	  });
	$("#clearbutton").click(function() {
		$("#paragraph").empty();
	});
});
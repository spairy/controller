$(document).ready(function(){
    $("#sign-up").click(signUp());
    $("#sign-in").click(signIn());
});

function signUp() {
	var data = $("#sign-form").serialize();
	var jsonData = $("#sign-form").serializeArray();
	alert(data);
	alert(jsonData);
	alert($("form").serialize());
    $.ajax({
	    type: "POST",
	    url: "/controller/auth/login.do",
	    data: "{\"username\":\"sunyong\"}",
	    success: function(msg){
	        alert( "Data Saved: " + msg );
	    }
	});
}

function signUp() {
	var data = $("#sign-form").serialize();
	var jsonData = $("#sign-form").serializeArray();
	alert(data);
	alert(jsonData);
	alert($("form").serialize());
    $.ajax({
	    type: "POST",
	    url: "/controller/auth/login.do",
	    data: "{\"username\":\"sunyong\"}",
	    success: function(msg){
	        alert( "Data Saved: " + msg );
	    }
	});
}

function submit() {
	$.get("auth/login.do", { username: "John", password: "2pm" } );

}
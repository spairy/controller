/*$(document).ready(function(){
   // $("#sign-up").click(signUp);
   // $("#sign-in").click(signIn);
});*/
/*
function signUp() {
	alert(JSON.stringify($("#sign-form").serializeJson()));
	
	$.ajax({
	    type: "POST",
	    url: "/controller/auth/login.do",
	    contentType: "application/json; charset=utf-8", 
	    data:  JSON.stringify($("#sign-form").serializeJson()),
	    dataType: "json",
	    success: (function(data){
	    	alert(data);
	    	if (null == data.error) {
		    	$("#sign-form").style.display="on";
		    	$("#sign-in-div").style.display="none";
		    	$("#sign-in-span").html=data.username;
	    	} else {
	    		$("#sign-in-div").style.display="none";
		    	$("#sign-in-span").html="login error";
	    	}

	    }),
	    complete: (function() {
    		
		}),
		error:(function(err){
        	
        })
	});
}*/

function signIn() {
	$.ajax({
	    type: "POST",
	    url: "/controller/auth/login.do",
	    contentType: "application/json; charset=utf-8",
	    data:  JSON.stringify($("#sign-form").serializeJson()),
	    dataType: "json",
	    success: function(data) {
	    	if (isNot(data.errors)) {
		    	$("#sign-form").hide();
		    	$("#sign-in-div").show();
		    	$("#sign-in-span").html(data.username);
	    	} else {
	    		$("#sign-in-div").show();
		    	$("#sign-in-span").html("login error");
	    	}
	    },
	    complete: function() {

		},
		error:function(err) {

        }
	});
}

function submit() {
	$.get("auth/login.do", { username: "John", password: "2pm" } );

}
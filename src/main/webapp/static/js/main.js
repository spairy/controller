$(document).ready(function(){
    $("#sign-up").click(signUp);
    $("#sign-in").click(signIn);
});

function signUp() {
	alert(JSON.stringify($("#sign-form").serializeJson()));
	
    $.ajax({
	    type: "GET",
	    url: "/auth/login.do",
	    data: "{}",// data: JSON.stringify($("#sign-form").serializeJson()),
	    //contentType: "application/json; charset=utf-8", 
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
        	
        }),
        dataType: "json"
	});
}

function signIn() {
	alert($("#sign-form").serializeJson());
	$.ajax({
	    type: "POST",
	    url: "/auth/login.do",
	    data: "{}",
	    contentType: "application/json; charset=utf-8", 
	    success: function(data){
	    	alert(data);
	    	if (null == data.error) {
		    	$("#sign-form").style.display="on";
		    	$("#sign-in-div").style.display="none";
		    	$("#sign-in-span").html=data.username;
	    	} else {
	    		$("#sign-in-div").style.display="none";
		    	$("#sign-in-span").html="login error";
	    	}
	    }
	});
}

function submit() {
	$.get("auth/login.do", { username: "John", password: "2pm" } );

}
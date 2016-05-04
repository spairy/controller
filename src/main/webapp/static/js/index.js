function doSubmit(){
	var data = $("#post-form").serialize();
    var jsonData = $("#post-form").serializeArray();
	alert(data);
	alert(jsonData);
	
    /*$.ajax({
	    type: "POST",
	    url: "/controller/auth/login.do",
	    data: "username="+$("#username-ajax").val()+"&password="+$("#password-ajax").val(),
	    success: function(msg){
	        alert( "Data Saved: " + msg.errors[0].errorCode );
	    }
	});*/
}
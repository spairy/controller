function doSubmit(){
	var data = $("#post-form").serialize();
    var jsonData = $("#post-form").serializeJson();
	//alert(data);
    JSON.stringify(jsonData);
	alert(JSON.stringify(jsonData));
	//alert(serializeNodeToJson("sign-div"));
	
    $.ajax({
	    type: "POST",
	    url: "/controller/auth/login.do",
	    contentType: "application/json; charset=utf-8", 
	    data:  JSON.stringify(jsonData),
	    dataType: "json",
	    success: function(msg){
	        alert( JSON.stringify(msg) );
	    }
	});
}
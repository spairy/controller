function serializeJson(id) {
	var data = $("#" + id).serialize();
	alert(data);
	data=data.replace(/&/g,"\",\"");
    data=data.replace(/=/g,"\":\"");
    data="{\""+data+"\"}";
    return data;
}


function serializeJson(id) {
	var data = $("#" + id).serialize();
	alert(data);
	data=data.replace(/&/g,"\",\"");
    data=data.replace(/=/g,"\":\"");
    data="{\""+data+"\"}";
    return data;
}

function isNot(thing) {
	var flag = false;
	if (null == thing) flag = true;
	    else if (0 == thing.length) flag = true;
	if (undefined == thing) flag = true;
	if ("" == thing) flag = true;
    return flag;
}

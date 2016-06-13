$(document).ready(function(){
	chatJDKInit();
    
    $('.to-login').click(function () {
    	window.location.href='index.html';
    });
    
    $('.no-friends a').click(function () {
    	$('.friends').addClass('am-hide');
    	$('.add-friends').removeClass('am-hide');
    });
});

function chatJDKInit() {
    $.ajax({
        type: 'GET',
        url: '/controller/auth/getSession.do',
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        success: function(data) {
            if (isNot(data.errors)) {
            	//$('.am-hide').addClass("am-block");
            	//$('.am-hide').removeClass("am-hide");
                initWebSocket();
            } else {
            	// TODO window.location.href='login.html';
            }
        },
        complete: function() {

        },
        error:function(err) {

        }
    });
}


function initWebSocket() {
	window.WebSocket = window.WebSocket || window.MozWebSocket;
	if (window.WebSocket) {
		websocket = new WebSocket(wsUri);
		writeToScreen("You have connectted to server, welcome");
		websocket.onopen = onOpen;
		websocket.onclose = onClose;
		websocket.onmessage = onMessage;
		websocket.onerror = onError;
		
		window.onbeforeunload = function(event){
			websocket.close();
	    };
	} else {
		alert("WebSockets not supported on your browser.");
	}
}

function writeToScreen(message) {
	var pre = document.createElement("li");
	var span = document.createElement("span");
	span.innerHTML = message;
	output.appendChild(span);
}

function reConnect () {
	if(websocket.readyState == WebSocket.CLOSED){
		console.log("Re-connect to Server");
		init();
	}else{
		writeToScreen("Alrady connect, please not re-connect");
	}
}

function onOpen(evt) {
	//called as soon as a connection is opened
	if(websocket.readyState == WebSocket.OPEN){
		console.log("Connect to server");
	}
}
function onClose(evt) {
	//called when connection is closed
	writeToScreen("DISCONNECTED");
}
function onMessage(evt) {
	//called on receipt of message
	var content = eval("(" + evt.data + ")").content;//$.parseJSON(evt.data);
	var userRes = eval("(" + content + ")");
	var onefriend = userRes.friend;
	if (null != onefriend && undefined != onefriend && "" != onefriend) {
		var input = document.createElement("input");
		input.type="checkbox";
		input.value=onefriend.memberID;
		input.name="friend";
		input.innerHTML = onefriend.memberID;
		friend.appendChild(input);
		var span = document.createElement("span");
		span.innerHTML = onefriend.memberID;
		friend.appendChild(span);
		var input1 = document.createElement("input");
		input1.type="checkbox";
		input1.value=onefriend.memberID;
		input1.name="friend";
		input1.innerHTML = onefriend.memberID;
		friend.appendChild(input1);
		var span1 = document.createElement("span");
		span1.innerHTML = onefriend.memberID;
		friend.appendChild(span1);
		//$('#friend1').val(evtRes.friend[0].content.memberId)
		//$('#friend2').val(evtRes.friend[1].content.memberId)
	} else {
		var message = JSON.parse(evt.data);
		writeToScreen(message)
	}
;
}
function onError(evt) {
	//called on error
	writeToScreen("ERROR:" + evt.data);
}
function sendMessage() {
	var msg = {
			"content": document.getElementById('text').value,
			"toMemberID": "10000002"
	};
	websocket.send(JSON.stringify(msg));
}

function closeWebSocket(){
	websocket.close(1000,"normaly close");
}

Date.prototype.format = function(format) {
    var o = {
        "M+": this.getMonth() + 1,
        // month
        "d+": this.getDate(),
        // day
        "h+": this.getHours(),
        // hour
        "m+": this.getMinutes(),
        // minute
        "s+": this.getSeconds(),
        // second
        "q+": Math.floor((this.getMonth() + 3) / 3),
        // quarter
        "S": this.getMilliseconds()
        // millisecond
    };
    if (/(y+)/.test(format) || /(Y+)/.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    for (var k in o) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
        }
    }
    return format;
};

function timestampformat(timestamp) {
    return (new Date(timestamp)).format("yyyy-MM-dd hh:mm:ss");
} 
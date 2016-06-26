$(document).ready(function(){
	chatJDKInit();
    
	$('.to-login').click(function () {
    	window.location.href='login.html';
    });
    
    $('.sign-out').click(function () {
		$.ajax({
		    type: 'GET',
		    url: '/controller/auth/logout.do',
		    contentType: 'application/json; charset=utf-8',
		    //data:  JSON.stringify($('.sign-form').serializeJson()),
		    dataType: 'json',
		    success: function(data) {
		    	if (isEmpty(data.errors)) {
		    	//	$('.sign-out').addClass('am-hide');
	            //	$('.sign-out').removeClass('am-block');
	            //	$('.to-login').addClass('am-blocks');
	            //	$('.to-login').removeClass('am-hide');
	            	window.location.href='login.html';
		        } else {
		        	window.location.href='error.html';
		        }
		    },
		    complete: function() {
		
		    },
		    error:function(err) {
		
		    }
		});
    });
    
    $('.sendMessage').click(function () {
    	sendMessage();
    });
    
    $('.no-friends a').click(function () {
    	$('.no-friends').addClass('am-hide');
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
        	if (isEmpty(data.errors)) {
            	$('.username').html(data.username);
            	$('.sign-out').addClass('am-block');
            	$('.sign-out').removeClass('am-hide');
            	$('.to-login').addClass('am-hide');
            	$('.to-login').removeClass('am-block');
            	initWebSocket();
            } else if (data.errors[0].errorCode == 'ERR_SYS_002') {
            	window.location.href='login.html';
            } else {
            	window.location.href='error.html';
            }
        },
        complete: function() {

        },
        error:function(err) {

        }
    });
}


function initWebSocket() {
	var wsUri = 'ws://localhost:8080/controller/chatJDK';
	window.WebSocket = window.WebSocket || window.MozWebSocket;
	if (window.WebSocket) {
		websocket = new WebSocket(wsUri);
		// writeToScreen("You have connectted to server, welcome");
		// alert('You have connectted to server, welcome');
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

function onOpen(evt) {
	//called as soon as a connection is opened
	//alert('open');
	if(websocket.readyState == WebSocket.OPEN){
		console.log("Connect to server");
	}
}

function onMessage(evt) {
	//called on receipt of message
	var msgEvt = eval("(" + evt.data + ")");
	var msgType = msgEvt.msgEvtType;
	
	switch (msgType) {
		case 'NEWMSG':
		    shouNewMsg(msgEvt); break;
		case 'FRIEND':
			shouFrdMsg(msgEvt); break;
		case 'OLDMSG':
			shouOldMsg(msgEvt); break;
		case 'SFLAG':
			shouFlgMsg(msgEvt); break;
		default:
			shouDltMsg(msgEvt);
	}
}

function shouNewMsg(msgEvt){
	var msgEvtList = msgEvt.msgEvtList;
	var message = $('.message-li').clone(true, true);
	message.removeClass('message-li');
	message.removeClass('am-hide');
	message.addClass('am-block');
	message.find('.message-datetime').html(msgEvtList[0].dateTime);
	message.find('.message-from').html(msgEvt.fromUsername);
	message.find('.message-to').html(msgEvt.toUsername);
	message.find('.message-content').html(msgEvtList[0].content);
	message.appendTo('.message-ul');
}

function shouFrdMsg(msgEvt) {
	var msgEvtList = msgEvt.msgEvtList;
	var frinedList = eval("(" + msgEvtList[0].content + ")");
	if (isEmpty(frinedList)) {
		$('.no-friends').removeClass('am-hide');
		$('.no-friends').addClass('am-block');
	} else {
		$('.friend-opt0').find('input').val(frinedList[0].friendMemberID);
		$('.friend-opt0').find('span').html(frinedList[0].friendMemberID);
		for (var i = 1; i < frinedList.length; i++) {
			var friendOpt = $('.friend-opt0').clone(true, true);
			friendOpt.removeClass('friend-opt' + (i - 1));
			friendOpt.addClass('friend-opt' + i);
			friendOpt.find('input').val(frinedList[i].friendMemberID);
			friendOpt.find('span').html(frinedList[i].friendMemberID);
			$('.friend-opt' + (i - 1)).after(friendOpt);
		}
		$('.friend-radio').removeClass('am-hide');
		$('.friends-radio').addClass('am-block');
		
		$('.friends-radio').removeClass('am-hide');
		$('.friends-radio').addClass('am-block');
		$('.message').removeClass('am-hide');
		$('.message').addClass('am-block');
	}
}

function shouOldMsg(msgEvt){
	var msgEvtList = msgEvt.msgEvtList;
	for(var i = 0; msgEvtList.length; i++) {
		var message = $('.message-li').clone(true, true);
		message.removeClass('am-hide');
		message.addClass('am-block');
		message.find('.message-datetime').html(msgEvtList[i].datetime);
		message.find('.message-from').html(msgEvtList.fromUsername);
		message.find('.message-to').html(msgEvtList.fromUsername);
		message.find('.message-content').html(msgEvtList[i].content);
		message.appendTo('.message-ul');
	}
}

function shouFlgMsg(msgEvt){
	
}

function shouDltMsg(msgEvt) {
	
}

function onClose(evt) {
	//called when connection is closed
	writeToScreen("DISCONNECTED");
}

function onError(evt) {
	//called on error
	writeToScreen("ERROR:" + evt.data);
}


function writeToScreen(message) {
	var pre = document.createElement("li");
	var span = document.createElement("span");
	span.innerHTML = message;
	output.appendChild(span);
}

function sendMessage() {
	var msg = {
			"msgEvtType":"NEWMSG",
			"fromMemberID":"",
			"toMemberID":$("input[name='friend-opt']:checked").val(),
			"msgEvtList": [{
				"content":$('.content').val(),
				"dateTime":"",
				"isSend": "false"}]
	};
	websocket.send(JSON.stringify(msg));
}

function reConnect () {
	if(websocket.readyState == WebSocket.CLOSED){
		console.log("Re-connect to Server");
		init();
	}else{
		writeToScreen("Alrady connect, please not re-connect");
	}
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
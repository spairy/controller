$(document).ready(function(){
    loginInit();
    
    $('.sign-in-btn').click(function () {
		$.ajax({
		    type: 'POST',
		    url: '/controller/auth/login.do',
		    contentType: 'application/json; charset=utf-8',
		    data:  JSON.stringify($('.sign-form').serializeJson()),
		    dataType: 'json',
		    success: function(data) {
		    	if (isEmpty(data.errors)) {
		        	window.location.href='index.html';
		        } else {
		        	$('.am-hide').addClass("am-block");
		        	$('.am-hide').removeClass("am-hide");
		        }
		    },
		    complete: function() {
		
		    },
		    error:function(err) {
		
		    }
		});
    });
    
    $('.sign-up-btn').click(function () {
		$.ajax({
		    type: 'POST',
		    url: '/controller/auth/enroll.do',
		    contentType: 'application/json; charset=utf-8',
		    data:  JSON.stringify($('.sign-form').serializeJson()),
		    dataType: 'json',
		    success: function(data) {
		    	if (isEmpty(data.errors)) {
		        	window.location.href='index.html';
		        } else {
		        	$('.am-hide').addClass("am-block");
		        	$('.am-hide').removeClass("am-hide");
		        }
		    },
		    complete: function() {
		
		    },
		    error:function(err) {
		
		    }
		});
    });
});

function loginInit() {
    $.ajax({
        type: 'GET',
        url: '/controller/auth/getSession.do',
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        success: function(data) {
            if (isEmpty(data.errors)) {
            	window.location.href='index.html';
            } else if (data.errors[0].errorCode != "ERR_SYS_002") {
            	$('.am-hide').addClass("am-block");
            	$('.am-hide').removeClass("am-hide");
            }
        },
        complete: function() {

        },
        error:function(err) {

        }
    });
}

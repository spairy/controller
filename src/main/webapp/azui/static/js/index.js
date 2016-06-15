$(document).ready(function(){
    indexInit();
    
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
});

function indexInit() {
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
            } else if (data.errors[0].errorCode == 'ERR_SYS_002') {
            	$('.sign-out').addClass('am-hide');
            	$('.sign-out').removeClass('am-block');
            	$('.to-login').addClass('am-blocks');
            	$('.to-login').removeClass('am-hide');
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

$(document).ready(function(){
    indexInit();
    
    $('.sign-in-btn').click(function () {
		$.ajax({
		    type: 'POST',
		    url: '/controller/auth/login.do',
		    contentType: 'application/json; charset=utf-8',
		    data:  JSON.stringify($('.sign-form').serializeJson()),
		    dataType: 'json',
		    success: function(data) {
		    	if (isNot(data.errors)) {
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

function indexInit() {
    $.ajax({
        type: 'GET',
        url: '/controller/auth/getSession.do',
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        success: function(data) {
            if (isNot(data.errors)) {
            	//$('.am-hide').addClass("am-block");
            	//$('.am-hide').removeClass("am-hide");
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

/*
function signUp() {
    alert(JSON.stringify($('#sign-form').serializeJson()));
    
    $.ajax({
        type: 'POST',
        url: '/controller/auth/login.do',
        contentType: 'application/json; charset=utf-8', 
        data:  JSON.stringify($('#sign-form').serializeJson()),
        dataType: 'json',
        success: (function(data){
            alert(data);
            if (null == data.error) {
                $('#sign-form').style.display='on';
                $('#sign-in-div').style.display='none';
                $('#sign-in-span').html=data.username;
            } else {
                $('#sign-in-div').style.display='none';
                $('#sign-in-span').html='login error';
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
        type: 'POST',
        url: '/controller/auth/login.do',
        contentType: 'application/json; charset=utf-8',
        data:  JSON.stringify($('.sign-form').serializeJson()),
        dataType: 'json',
        success: function(data) {
        	if (isNot(data.errors)) {
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
}

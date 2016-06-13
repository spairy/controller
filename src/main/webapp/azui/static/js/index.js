$(document).ready(function(){
    indexInit();
    
    $('.to-login').click(function () {
    	window.location.href='index.html';
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

$(document).ready(function(){
    $(".sign-up").click(signUp());
    $(".sign-in").click(signIn());
});

function signUp() {
	var json = Json($(".form-name"));
    $.ajax({
	    type: "POST",
	    url: "/controller/auth/login.do",
	    data: "name=John&location=Boston",
	    success: function(msg){
	        alert( "Data Saved: " + msg );
	    }
	});
}

function signUp() {
  
}

function submit() {
	$.get("auth/login.do", { username: "John", password: "2pm" } );

}
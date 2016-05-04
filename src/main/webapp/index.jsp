<html>
<head>
    <link href="static/css/main.css" rel="stylesheet">

</head>
<body>
<h2>Hello World!</h2>

<form id="get-form" action="/controller/auth/login.do" method="get">
	<span>Username:</span><input type="text" name="username" value="" /><br/>
	<span>Password:</span><input type="text" name="password" value="" /><br/>
	<input type="submit" name="submit" value="submit" />
</form>
<br />
<form id="post-form" action="/controller/auth/login.do" method="post">
	<span>Username:</span><input type="text" name="username" value="" /><br/>
	<span>Password:</span><input type="text" name="password" value="" /><br/>
	<input type="submit" name="submit-post" value="submit" />
</form>

<div id="sign-div" >
	<div><p>Ajax</p></div>
	<div>
		<input id="username-ajax" type="text" name="username" />
	</div>
	<div>
		<input id="password-ajax" type="password" name="password" />
	</div>
	<div><input type="button" name="submit" value="button" onclick="doSubmit()"/></div>
</div>
    <script src="bootstrap/jquery/jquery-1.12.2.min.js"></script>
    <script src="static/js/index.js"></script>
</body>
</html>
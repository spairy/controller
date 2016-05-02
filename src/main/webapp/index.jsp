<html>
<head>

<script>
function doSubmit(){

}
</script>
</head>
<body>
<h2>Hello World!</h2>

<form action="/controller/auth/login.do" method="get">
	<span>Username:</span><input type="text" name="username" value="" /><br/>
	<span>Password:</span><input type="text" name="password" value="" /><br/>
	<input type="submit" name="submit" value="submit" />
</form>
<br />
<form action="/controller/auth/login.do" method="post">
	<span>Username:</span><input type="text" name="username" value="" /><br/>
	<span>Password:</span><input type="text" name="password" value="" /><br/>
	<input type="submit" name="submit-post" value="submit" />
</form>
</body>
</html>
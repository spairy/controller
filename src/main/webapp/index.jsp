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
<div >

	<div><input type="text" name="a" value="1" id="a" /></div>
	<div><input type="text" name="b" value="2" id="b" /></div>
	<div><input type="hidden" name="c" value="3" id="c" /></div>
	<div>
	  <textarea name="d" rows="8" cols="40">4</textarea>
	</div>
	<div><select name="e">
	  <option value="5" selected="selected">5</option>
	  <option value="6">6</option>
	  <option value="7">7</option>
	</select></div>
	<div>
	  <input type="checkbox" name="f" value="8" id="f" />
	</div>
	<div>
	  <input type="submit" name="g" value="Submit" id="g" />
	</div>





    <span>button:</span><input type="button" name="obj[0].username" value="" /><br/>
	<span>checkbox:</span><input type="checkbox" name="obj[1].username" value="" /><br/>
	<span>file:</span><input type="file" name="obj[0].username" value="" /><br/>
	<span>hidden:</span><input type="hidden" name="obj[1].username" value="" /><br/>
	<span>image:</span><input type="image" name="obj[0].username" value="" /><br/>
	<span>password:</span><input type="password" name="obj[1].username" value="" /><br/>
	<span>radio:</span><input type="radio" name="obj[0].username" value="" /><br/>
	<span>reset:</span><input type="reset" name="obj[1].username" value="" /><br/>
	<span>submit:</span><input type="submit" name="obj[0].username" value="" /><br/>
	<span>text:</span><input type="text" name="obj[1].username" value="" /><br/>




	<span>Username:</span><input type="text" name="obj[0].username" value="" /><br/>
	<span>Username:</span><input type="text" name="obj[1].username" value="" /><br/>
	<span>Password:</span><input type="text" name="password" value="" /><br/>
</div>
	<input type="submit" name="submit-post" value="submit" />
</form>

<div id="sign-div" >
	<div><p>Ajax</p></div>
	
	<div><input type="text" name="a" value="1" id="a" /></div>
	<div><input type="text" name="b" value="2" id="b" /></div>
	<div><input type="hidden" name="c" value="3" id="c" /></div>
	<div>
	  <textarea name="d" rows="8" cols="40">4</textarea>
	</div>
	<div><select name="e">
	  <option value="5" selected="selected">5</option>
	  <option value="6">6</option>
	  <option value="7">7</option>
	</select></div>
	<div>
	  <input type="checkbox" name="f" value="8" id="f" />
	</div>
	<div>
	  <input type="submit" name="g" value="Submit" id="g" />
	</div>

	
	<div>
		<input id="username-ajax" type="text" name="username" />
	</div>
	<div>
		<input id="password-ajax" type="password" name="password" />
	</div>
	<div><input type="button" name="submit" value="button" onclick="doSubmit()"/></div>
</div>
    <script src="bootstrap/jquery/jquery-1.12.2.min.js"></script>
    <script src="bootstrap/jquery/jquery-extension-0.1.js"></script>
    <script src="static/js/index.js"></script>
    <script src="static/js/utils.js"></script>
</body>
</html>
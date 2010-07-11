<#import "/spring.ftl" as spring />

<html>
<head>
<title>Helianto Admin Application Login Page</title>
<link rel="icon" type="image/png" href="../favicon.png" />
<style type="text/css">

    body {
        color: #444;
		font-family: "Trebuchet MS", sans-serif;"
	}
	
	#sflogo { 
	    float: right;
	}
	
	#loginForm { 
        color: #fff;
	    background-color: #155882;
	    padding: 10px 24px 10px 24px;
	}
	
	#loginError {
		float: right;
		margin: 20px;
		padding: 20px;
		color: #444;
		background-color: #fff;
	}
	
</style>
</head>
<body onload='document.f.j_username.focus();'>

<div id="sflogo">
<a target="_new" href="http://www.sourceforge.net">
<img src="http://sflogo.sourceforge.net/sflogo.php?group_id=161555&type=11" alt="" />
</a>
</div>

<img src="/helianto/helianto.png" />


<p>This is a sample application built on  the Helianto Project, an open source API developed
to help developers embrace leading technologies and best practices with a flexible learning curve.</p>

<h3>You can use this application as an entry point to explore and learn how to
develop your own. Add value, starting from Helianto artifacts!</h3>

<p>For example, it is possible to create and expose more than one operator or
namespace and select them to filter entities, although this is out of the scope of this
application. If you are a programmer, a good start would be to uncomment certain parts
of this source code and modify them to show a target list placed in memory
just for demostration purposes.</p>

<div id="loginForm">

<#if Session.SPRING_SECURITY_LAST_EXCEPTION?? && Session.SPRING_SECURITY_LAST_EXCEPTION.message?has_content>
	<div id="loginError">${Session.SPRING_SECURITY_LAST_EXCEPTION.message}</div>
</#if>

<h3>Welcome to the Helianto Admin Application.</h3>

<h3>Login with Username and Password</h3>
<form name='f' action='/helianto/j_spring_security_check' method='POST'>
<table>
	<tr>
		<td>User:</td>
		<td><input type='text' name='j_username' value=''></td>
	</tr>
	<tr>
		<td>Password:</td>
		<td><input type='password' name='j_password' /></td>
	</tr>
	<tr>
		<td colspan='2'><input name="submit" type="submit" /></td>
	</tr>
	<tr>
		<td colspan='2'><input name="reset" type="reset" /></td>
	</tr>
</table>
</form>
</div>
</body>
</html>
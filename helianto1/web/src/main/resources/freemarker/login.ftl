<#import "/spring.ftl" as spring />

<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
</head>
<body>
<form name="loginForm" method="POST" action="j_acegi_security_check" >
<table>
<tr>
<td style="width: 200px; height: 320px;">
	<table>
	<tr>
	<td>&#160;</td>
	<td><@spring.messageText "login.warn", "In order to proceed, your identification will be required."/></td>
	</tr>
	</table>
</td>
<td style="background-image: url(img/arrow-bg.png);">
	<table style="width: 200px;">
	<tr>
	<td><@spring.messageText "login.user", "Username: "/></td>
	<td><input type="text" name="j_username" size="16"/></td>
	</tr><tr>
	<td><@spring.messageText "login.password", "Password: "/></td>
	<td><input type="password" name="j_password" size="10"/></td>
	</tr><tr colspan="2">
	<td><input type="submit" alignment="center" value="<@spring.messageText "login.enter", "Enter"/>" class="btn"></td>
	</tr>
	</table>
</td>
</tr>
</table>
		<input type="hidden" name="_flowExecutionKeyy 
		       value="${flowExecutionKey}"/>
		<input type="hidden" name="_eventId" value="enter"/>
</form>
</body>
<html>
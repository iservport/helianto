<#import "/macros/head.ftl" as hd />
<#import "/macros/box.ftl" as bx />
<#import "/macros/swf.ftl" as fl />
<#import "/spring.ftl" as spring />

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<@hd.head "Login">
	  <link href="style-blue.css" rel="stylesheet" />
</@hd.head> 
<body>
    <#--
     # SIDEBAR
     #-->
	<div id="sidebar">
		<p>In order to proceed, your identification will be required.</p>
	</div>
    <#--
     # MAIN
     #-->
	<div id="main">
	<form name="loginForm" method="POST" action="j_acegi_security_check" >
		<table>
		
		<tr>
		<td>Username:</td>
		<td><input type="text" name="j_username" size="16"/></td>
		</tr>
		
		<tr>
		<td>Old password:</td>
		<td><input type="password" name="j_password" size="10"/></td>
		</tr>
		
		<tr>
		<td>New password:</td>
		<td><input type="password" name="j_password1" size="10"/></td>
		</tr>
		
		<tr>
		<td>Confirm new password:</td>
		<td><input type="password" name="j_password2" size="10"/></td>
		</tr>
		
		<tr>
		<td colspan="2"><input type="submit" alignment="center" value="Enter"></td>
		</tr>
		
		<tr>
		<td colspan="2"><@fl.anchor "configuration">Need a username?</@fl.anchor></td>
		</tr>
		
		</table>
		<input type="hidden" name="_flowExecutionKey
		       value="${flowExecutionKey}"/>
		<input type="hidden" name="_eventId" value="enter"/>
	</form>
	</div>
</body>
<html>
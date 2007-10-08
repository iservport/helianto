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
		<p>Please, change your password.</p>
	</div>
    <#--
     # MAIN
     #-->
	<div id="main">
	<form name="loginForm" method="POST" action="admin.htm" >
		<table>
		
		<@bx.group >
			<@bx.row >Username:</@bx.row>
			<@bx.row>
			<@spring.formInput "credentialForm.credential.identity.principal", 'size="16" maxlength="64"'/>
			</@bx.row>
		</@bx.group>

		<@bx.group >
			<@bx.row >Current password:</@bx.row>
			<@bx.row>
			<@spring.formInput "credentialForm.currentPassword", 'size="16" maxlength="64"'/>
			</@bx.row>
		</@bx.group>

		<@bx.group >
			<@bx.row >New password:</@bx.row>
			<@bx.row>
			<@spring.formInput "credentialForm.newPassword", 'size="16" maxlength="64"'/>
			</@bx.row>
		</@bx.group>

		<@bx.group >
			<@bx.row >Confirm new password:</@bx.row>
			<@bx.row>
			<@spring.formInput "credentialForm.verifyPassword", 'size="16" maxlength="64"'/>
			</@bx.row>
		</@bx.group>

		<tr>
		<td colspan="2"><input type="submit" alignment="center" value="Enter"></td>
		</tr>
		
		<tr>
		<td colspan="2"><@fl.anchor "configuration">Need a username?</@fl.anchor></td>
		</tr>
		
		</table>
		<input type="hidden" name="_flowExecutionKey
		       value="${flowExecutionKey}"/>
		<input type="hidden" name="_eventId" value="verifyPassword"/>
	</form>
	</div>
</body>
<html>
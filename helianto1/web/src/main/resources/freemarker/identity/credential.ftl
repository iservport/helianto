<#import "/spring.ftl" as spring />
<@spring.bind "identityForm.credential.*" /> 

<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
</head>
<body>
<table>
	<tr>
	<td style="width: 35%; vertical-align: top; border: 1px dotted #cccccc;">
	<p><@spring.messageText "identity.password.header", "Enter your password"/></p>
	</td>
	<td style="width: 65%; vertical-align: top;">
		<!-- 
		 ! Forms
		 !-->
		<form action="admin.htm" method="POST">
		<table>
		
			<tr class="t_title">
			<td colspan="3"><@spring.messageText "identity.header1", "Password"/></td>
			</tr>
			
			<tr>
			<td><@spring.messageText "identity.password1", "Password"/>:</td>
			<td><@spring.formInput "identityForm.credential.password", 'size="32"'/></td>
			</tr>

			<tr>
			<td><@spring.messageText "identity.password2", "Password confirmation"/>:</td>
			<td><@spring.formInput "identityForm.credential.verifyPassword", 'size="32"'/></td>
			</tr>

			<tr class="t_title">
			<td colspan="3">
			<input type="submit" name="_eventId_next" value="<@spring.messageText "button.next", "Next"/>" class="btn" /></td>
			</tr>

			<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}"/>
			

		</table>
		</form>
		<form action="admin.htm" method="POST">
		<p style="font-weight: bold; text-align: right;">
			<input type="submit" name="_eventId_cancel" value="<@spring.messageText "button.cancel", "Cancel"/>"  class="btn" />
			<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}"/>
		</p>
		</form>
	</td>
	</tr>
</table>
</body>
</html>

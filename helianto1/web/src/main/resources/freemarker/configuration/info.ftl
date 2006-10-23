<#import "/spring.ftl" as spring />

<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
</head>
<body>
<table>
	<tr>
	<td style="width: 35%; vertical-align: top; border: 1px dotted #cccccc;">
	<p><@spring.messageText "configuration.step1.header", "Configuration process - Start"/></p>
	<p><@spring.messageText "configuration.step1.welcome", "You were redirected to this
	    page because the system was not able to detect a previous configuration. Please, read
	    the follwing instructions carefully. If you dont feel secure to proceed or have any doubt, please,
	    ask your system administrator for assistance."/></p>
	<p><@spring.messageText "configuration.step1.warning", "The steps you are about to follow
	    will grant you unrestricted privileges over the system you are about to install. You will
	    become the system manager, which has control over the top level admin Service. Please, read the
	    documentation if you are not yet aware of the system manager responsibilities."/></p>
	<p><@spring.messageText "configuration.step1.whatsnext", "You will be required to:"/></p>
	<ol>
		<li><@spring.messageText "configuration.step1.desc1", "Provide a system identity to yourself;"/></li>
		<li><@spring.messageText "configuration.step1.desc2", "Select a namespace identifier that will be
		   assigned to a system operator and to a default entity;"/></li>
		<li><@spring.messageText "configuration.step1.desc3", "Login to the system for the first time as a confirmation
		   of a successfull configuration."/></li>
	</ol>
	</td>
	<td style="width: 65%; vertical-align: top;">
		<!-- 
		 ! Forms
		 !-->
		<form action="admin.htm" method="POST">
		<table>
		
			<tr class="t_title">
			<td colspan="3"><@spring.messageText "configuration.header1", "Go to first step"/></td>
			</tr>
			
			<tr>
			<td>!</td>
			<td><@spring.messageText "configuration.caption1", "Identity registration."/></td>
			<td><input type="submit" name="_eventId_next" value="<@spring.messageText "button.go", "Go"/>" class="btn" /></td>
			</tr>

			<tr>
			<td>-</td>
			<td><@spring.messageText "configuration.caption2", "Operator and Entity name."/></td>
			<td>&#160;</td>
			</tr>

			<tr>
			<td>-</td>
			<td><@spring.messageText "configuration.caption3", "Login and confirm."/></td>
			<td>&#160;</td>
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

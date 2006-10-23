<#import "/spring.ftl" as spring />

<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
</head>
<body>
<table>
	<tr>
	<td style="width: 35%; vertical-align: top; border: 1px dotted #cccccc;">
	<p><@spring.messageText "configuration.step2.header", "Configuration process - Operator's name"/></p>
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
			<td>+</td>
			<td><@spring.messageText "configuration.caption1", "Identity registration."/></td>
			<td><@spring.messageText "configuration.done", "Done."/></td>
			</tr>

			<tr>
			<td>!</td>
			<td><@spring.messageText "configuration..caption2", "Operator and Entity name."/></td>
			<td><input type="submit" name="_eventId_next" value="<@spring.messageText "button.go", "Go"/>" class="btn" /></td>
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

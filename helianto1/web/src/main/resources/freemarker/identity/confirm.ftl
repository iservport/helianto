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
	<p><@spring.messageText "identity.step4.header", "Confirmation"/></p>
	<p><@spring.messageText "identity.step4.info", "The password required to protect your identity may 
	    be updated either on-line or automatically."/></p>
	<p><@spring.messageText "identity.step4.option1", "You may receive your data and current password by email."/></p>
		<!-- 
		 ! Form 1
		 !-->
		<form action="admin.htm" method="POST">
			<p style="font-weight: bold; text-align: right; border: 1px solid white;">
				<input type="submit" name="_eventId_sendCurrent" value="<@spring.messageText "identity.button.sendCurrent", "Send current password"/>"  class="btn" />
			</p>
			<p><@spring.messageText "identity.step4.option2", "The system operator may generate a new password and send it by email."/></p>
			<p style="font-weight: bold; text-align: right;">
				<input type="submit" name="_eventId_sendNew" value="<@spring.messageText "identity.button.sendNew", "Send new password"/>"  class="btn" />
			</p>
			<p><@spring.messageText "identity.step4.option3", "Update your password on-line. Please, consider the options above as they are more secure."/></p>
			<p style="font-weight: bold; text-align: right;">
				<input type="submit" name="_eventId_password" value="<@spring.messageText "identity.button.passwordUpdate", "Password update"/>"  class="btn" />
			</p>
			<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}"/>
		</form>
	</td>
	<td style="width: 65%; vertical-align: top;">
		<!-- 
		 ! Form 2
		 !-->
		<form action="admin.htm" method="POST">
		<table>
		
			<tr class="t_title">
			<td colspan="3"><@spring.messageText "identity.header4", "Confirm"/></td>
			</tr>
			
			<tr>
			<td><@spring.messageText "identity.email", "E-mail"/>:</td>
			<td>${identityForm.credential.identity.principal}</td>
			</tr>

			<tr>
			<td><@spring.messageText "identity.optionalAlias", "Optional alias"/>:</td>
			<td>${identityForm.credential.identity.optionalAlias}</td>
			</tr>

			<#assign notificationA><@spring.messageText "identity.notification.A", "auto"/></#assign>
			<#assign notificationR><@spring.messageText "identity.notification.R", "by request"/></#assign>
			
			<#assign notificationTypes={
			    'A': "${notificationA}"
			  , 'R': "${notificationR}" 
			  } />

			<tr>
			<td><@spring.messageText "identity.notification", "Notification type"/>:</td>
			<td>${notificationTypes[identityForm.credential.identity.notification]}</td>
			</tr>

			<#assign typeO><@spring.messageText "identity.type.O", "organizational"/></#assign>
			<#assign typeP><@spring.messageText "identity.type.P", "personal"/></#assign>
			
			<#assign identityTypes={
			    'O': "${typeO}"
			  , 'P': "${typeP}" 
			  } />

			<tr>
			<td><@spring.messageText "identity.type", "Identity type"/>:</td>
			<td>${identityTypes[identityForm.credential.identity.identityType]}</td>
			</tr>
			
			<#if identityForm.credential.identity.identityType='P' >
			<tr>
			<td><@spring.messageText "identity.personalData.firstName", "First name"/>:</td>
			<td>${identityForm.credential.identity.personalData.firstName}</td>
			</tr>

			<tr>
			<td><@spring.messageText "identity.personalData.lastName", "Last name"/>:</td>
			<td>${identityForm.credential.identity.personalData.lastName}</td>
			</tr>
			
			</#if>

			<tr class="t_title">
			<td colspan="3">
			<input type="submit" name="_eventId_page2" value="<@spring.messageText "button.previous", "Previous"/>" class="btn" /> | 
			<input type="submit" name="_eventId_confirm" value="<@spring.messageText "button.next", "Next"/>" class="btn" /></td>
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


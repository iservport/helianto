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
	<p><@spring.messageText "identity.step2.header", "Registration details"/></p>
	<p><@spring.messageText "identity.step2.info", "Your identity has been successfully 
	    registered. Now, select your preferred profile or accept the defaults and advance 
	    directly to the last step."/></p>
	</td>
	<td style="width: 65%; vertical-align: top;">
		<!-- 
		 ! Forms
		 !-->
		<form action="admin.htm" method="POST">
		<table>
		
			<tr class="t_title">
			<td colspan="3"><@spring.messageText "identity.header2", "Notification and type"/></td>
			</tr>
			
			<tr>
			<td><@spring.messageText "identity.email", "E-mail"/>:</td>
			<td>${identityForm.credential.identity.principal}</td>
			</tr>

			<tr>
			<td><@spring.messageText "identity.optionalAlias", "Optional alias"/>:</td>
			<td><@spring.formInput "identityForm.credential.identity.optionalAlias", 'size="32"'/></td>
			</tr>

			<tr>
			<td colspan="3"><@spring.messageText "identity.notification.info", "The system operator may
			     redirect some notification messages to your email at your discretion. Please,
			     select \"auto\" if you can receive messages regularly, or \"by request\" if
			     you wish to request the messages manually."/></td>
			</tr>
			
			<#assign notificationA><@spring.messageText "identity.notification.A", "auto"/></#assign>
			<#assign notificationR><@spring.messageText "identity.notification.R", "by request"/></#assign>
			
			<#assign notificationTypes={
			    'A': "${notificationA}"
			  , 'R': "${notificationR}" 
			  } />

			<tr>
			<td><@spring.messageText "identity.notification", "Notification type"/>:</td>
			<td><@spring.formSingleSelect "identityForm.credential.identity.notification", notificationTypes /></td>
			</tr>

			<tr>
			<td colspan="3"><@spring.messageText "identity.type.info", "If you share the email
			     previously supplied as your unique identity with some other people or organization, 
			     you may not wish to supply personal data. Otherwise, please, choose  \"personal\" to 
			     make the personal data form available."/></td>
			</tr>
			
			<#assign typeO><@spring.messageText "identity.type.O", "organizational"/></#assign>
			<#assign typeP><@spring.messageText "identity.type.P", "personal"/></#assign>
			
			<#assign identityTypes={
			    'O': "${typeO}"
			  , 'P': "${typeP}" 
			  } />

			<tr>
			<td><@spring.messageText "identity.type", "Identity type"/>:</td>
			<td><@spring.formSingleSelect "identityForm.credential.identity.identityType", identityTypes /></td>
			</tr>

			<tr class="t_title">
			<td colspan="3">
			<input type="submit" name="_eventId_page1" value="<@spring.messageText "button.previous", "Previous"/>" class="btn" /> | 
			<input type="submit" name="_eventId_page3" value="<@spring.messageText "button.next", "Next"/>" class="btn" /></td>
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


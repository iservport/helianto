<#import "/spring.ftl" as spring />
<@spring.bind "identityForm.identity.*" /> 

<table>
	<tr>
	<td style="width: 35%; vertical-align: top; border: 1px dotted #cccccc;">
	<p><@spring.messageText "identity.step1.header", "Registration process"/></p>
	<p><@spring.messageText "identity.step1.welcome", "Welcome to the registration  
	    process. Please, follow the required steps bellow to input your identity
	    and credentials."/></p>
	</td>
	<td style="width: 65%; vertical-align: top;">
		<!-- 
		 ! Forms
		 !-->
		<form action="home.htm" method="POST">
		<table>
		
			<tr class="t_title">
			<td colspan="3"><@spring.messageText "identity.header", "Identification"/></td>
			</tr>
			
			<tr>
			<td><@spring.messageText "identity.email", "E-mail"/>:</td>
			<td><@spring.formInput "identityForm.identity.principal", 'size="32"'/></td>
			</tr>

			<tr>
			<td><@spring.messageText "identity.optionalAlias", "Optional alias"/>:</td>
			<td><@spring.formInput "identityForm.identity.optionalAlias", 'size="32"'/></td>
			</tr>

			<tr class="t_title">
			<td colspan="3">
			<input type="submit" name="_eventId_page2" value="<@spring.messageText "button.next", "Next"/>" class="btn" /></td>
			</tr>

			<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}"/>
			

		</table>
		</form>
		<form action="home.htm" method="POST">
		<p style="font-weight: bold; text-align: right;">
			<input type="submit" name="_eventId_cancel" value="<@spring.messageText "button.cancel", "Cancel"/>"  class="btn" />
			<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}"/>
		</p>
		</form>
	</td>
	</tr>
</table>

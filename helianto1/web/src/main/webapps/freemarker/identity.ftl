<#import "/spring.ftl" as spring />
<@spring.bind "identityForm.identity.*" /> 

<table>
	<tr>
	<td style="width: 50%; vertical-align: top;">
	</td>
	<td style="width: 50%; vertical-align: top;">
		<!-- 
		 ! Forms
		 !-->
		<form action="home.htm" method="POST">
		<table>
		
			<tr class="t_title">
			<td colspan="3"><@spring.messageText "identity.header", "Identification"/></td>
			</tr>
			
			<tr>
			<td><@spring.messageText "identity.principal", "Principal"/>:</td>
			<td><@spring.formInput "identityForm.identity.principal", 'size="32"'/></td>
			</tr>

			<tr>
			<td><@spring.messageText "identity.optionalAlias", "Optional alias"/>:</td>
			<td><@spring.formInput "identityForm.identity.optionalAlias", 'size="32"'/></td>
			</tr>

			<tr class="t_title">
			<td colspan="3">
			<input type="image" src="icons/bullet.gif" name="_eventId_page2" value="page2">
				<@spring.messageText "button.next", "Next"/>
			</input></td>
			</tr>

			<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}"/>
			

		</table>
		</form>
		<form action="home.htm" method="POST">
		<p style="font-weight: bold;">
			<input type="image" src="icons/bullet.gif" name="_eventId_cancel" value="cancel">
				<@spring.messageText "button.cancel", "Cancel"/>
			</input>
			<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}"/>
		</p>
		</form>
	</td>
	</tr>
</table>

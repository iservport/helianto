<#import "/spring.ftl" as spring />
<@spring.bind "identityForm.identity.*" /> 

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
		<form action="home.htm" method="POST">
		<table>
		
			<tr class="t_title">
			<td colspan="3"><@spring.messageText "identity.header3", "Personal data"/></td>
			</tr>
			
			<tr>
			<td><@spring.messageText "identity.email", "E-mail"/>:</td>
			<td>${identityForm.identity.principal}</td>
			</tr>

			<tr>
			<td colspan="3"><@spring.messageText "identity.personalData.info", "Please, provide your personal data."/></td>
			</tr>
			
			<tr>
			<td><@spring.messageText "identity.personalData.firstName", "First name"/>:</td>
			<td><@spring.formInput "identityForm.identity.personalData.firstName", 'size="32"' /></td>
			</tr>

			<tr>
			<td><@spring.messageText "identity.personalData.lastName", "Last name"/>:</td>
			<td><@spring.formInput "identityForm.identity.personalData.lastName", 'size="32"' /></td>
			</tr>
			
			<#assign genderM><@spring.messageText "identity.gender.M", "male"/></#assign>
			<#assign genderF><@spring.messageText "identity.gender.F", "female"/></#assign>
			<#assign genderN><@spring.messageText "identity.gender.N", "not supplied"/></#assign>
			
			<#assign gender={
			    'M': "${genderM}"
			  , 'F': "${genderF}" 
			  , 'N': "${genderN}" 
			  } />

			<tr>
			<td><@spring.messageText "identity.personalData.gender", "Gender"/>:</td>
			<td><@spring.formSingleSelect "identityForm.identity.personalData.gender", gender /></td>
			</tr>

			<#assign appellation0><@spring.messageText "identity.appellation.0", "not supplied"/></#assign>
			<#assign appellation1><@spring.messageText "identity.appellation.1", "Miss"/></#assign>
			<#assign appellation2><@spring.messageText "identity.appellation.2", "Mr. ou Mrs."/></#assign>
			<#assign appellation3><@spring.messageText "identity.appellation.3", "Ms."/></#assign>
			
			<#assign appellation={
			    '0': "${appellation0}"
			  , '1': "${appellation1}" 
			  , '2': "${appellation2}" 
			  , '3': "${appellation3}" 
			  } />

			<tr>
			<td><@spring.messageText "identity.personalData.appellation", "Appellation"/>:</td>
			<td><@spring.formSingleSelect "identityForm.identity.personalData.appellation", appellation /></td>
			</tr>

			<tr class="t_title">
			<td colspan="3">
			<input type="submit" name="_eventId_page2" value="<@spring.messageText "button.previous", "Previous"/>" class="btn" /> | 
			<input type="submit" name="_eventId_page4" value="<@spring.messageText "button.next", "Next"/>" class="btn" /></td>
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

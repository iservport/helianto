<#import "/spring.ftl" as spring />
<#import "/macros/head.ftl" as hd />
<#import "/macros/cancelForm.ftl" as cf />
<@spring.bind "identityForm.credential.*" /> 

<html>
<@hd.head />
<body>
<table>
	<tr>
	<td style="width: 35%; vertical-align: top; border: 1px dotted #cccccc;">
	<p>Registration details</p>
	<p>Your identity has been successfully 
	    registered. Now, select your preferred profile or accept the defaults and advance 
	    directly to the last step.</p>
	</td>
	<td style="width: 65%; vertical-align: top;">
		<!-- 
		 ! Forms
		 !-->
		<form action="admin.htm" method="POST">
		<table>
		
			<tr class="t_title">
			<td colspan="3"><@spring.messageText "identity.header3", "Personal data"/></td>
			</tr>
			
			<tr>
			<td>E-mail:</td>
			<td>${identityForm.credential.identity.principal}</td>
			</tr>

			<tr>
			<td colspan="3"><@spring.messageText "identity.personalData.info", "Please, provide your personal data."/></td>
			</tr>
			
			<tr>
			<td>First name:</td>
			<td><@spring.formInput "identityForm.credential.identity.personalData.firstName", 'size="32"' /></td>
			</tr>

			<tr>
			<td>Last name:</td>
			<td><@spring.formInput "identityForm.credential.identity.personalData.lastName", 'size="32"' /></td>
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
			<td>Gender:</td>
			<td><@spring.formSingleSelect "identityForm.credential.identity.personalData.gender", gender /></td>
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
			<td>Appellation:</td>
			<td><@spring.formSingleSelect "identityForm.credential.identity.personalData.appellation", appellation /></td>
			</tr>

			<tr class="t_title">
			<td colspan="3">
			<input type="submit" name="_eventId_page2" value="<@spring.messageText "button.previous", "Previous"/>" class="btn" /> | 
			<input type="submit" name="_eventId_page4" value="<@spring.messageText "button.next", "Next"/>" class="btn" /></td>
			</tr>

			<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}"/>
			

		</table>
		</form>
		<@cf.cancelForm "admin.htm"/>
	</td>
	</tr>
</table>
</body>
</html>


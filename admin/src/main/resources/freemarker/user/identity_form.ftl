<div id="panel">
<table>
	<form action="home.htm" method="POST">
	
	<tr>
		<td>Optional alias</td>
		<td><@spring.formInput "formObject.target.identity.optionalAlias", "size='20'"/></td>
	</tr>
	<tr>
		<td>First name</td>
		<td><@spring.formInput "formObject.target.identity.personalData.firstName", "size='20'"/></td>
	</tr>
	<tr>
		<td>Last name</td>
		<td><@spring.formInput "formObject.target.identity.personalData.lastName", "size='20'"/></td>
	</tr>
	<tr>
		<td>Gender</td>
		<td><@spring.formRadioButtons  "formObject.target.identity.personalData.gender", gender, " ", "size='3'"/></td>
	</tr>
	<tr>
		<td>Appellation</td>
		<td><@spring.formRadioButtons "formObject.target.identity.personalData.appellation", appellation, " ", "size='2'"/></td>
	</tr>
	
	<#if formObject.target.class=='class org.helianto.core.User'>
	
	<tr>
		<td>Identity type</td>
		<td><@spring.formRadioButtons "formObject.target.identity.identityType", identityType, " ", "size='2'"/></td>
	</tr>
	<tr>
		<td>Notification</td>
		<td><@spring.formRadioButtons "formObject.target.identity.notification", notification, " ", "size='2'"/></td>
	</tr>
	
	</#if>
	
	<tr>
		<td colspan="2"><@submit2 /></td>
	</tr>
	
	<@flowKey />
	</form>
</table>
</div>
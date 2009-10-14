<div id="panel">
<table>
	<form method="POST">
	
	<tr>
		<td>Optional alias</td>
		<td><@spring.formInput "formObject.target.child.identity.optionalAlias", "size='20'"/></td>
	</tr>
	<tr>
		<td>First name</td>
		<td><@spring.formInput "formObject.target.child.identity.personalData.firstName", "size='20'"/></td>
	</tr>
	<tr>
		<td>Last name</td>
		<td><@spring.formInput "formObject.target.child.identity.personalData.lastName", "size='20'"/></td>
	</tr>
	<tr>
		<td>Gender</td>
		<td><@spring.formRadioButtons  "formObject.target.child.identity.personalData.gender", gender, " ", "size='3'"/></td>
	</tr>
	<tr>
		<td>Appellation</td>
		<td><@spring.formRadioButtons "formObject.target.child.identity.personalData.appellation", appellation, " ", "size='2'"/></td>
	</tr>
	
	<tr>
		<td>Identity type</td>
		<td><@spring.formRadioButtons "formObject.target.child.identity.identityType", identityType, " ", "size='2'"/></td>
	</tr>
	<tr>
		<td>Notification</td>
		<td><@spring.formRadioButtons "formObject.target.child.identity.notification", notification, " ", "size='2'"/></td>
	</tr>
	
	<tr>
		<td colspan="2"><@submit2 /></td>
	</tr>
	
	<@flowKey />
	</form>
</table>
</div>
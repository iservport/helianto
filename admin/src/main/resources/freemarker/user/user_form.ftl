<div id="panel">
<table>
	<form action="home.htm" method="POST">
	
	<#if formObject.target.id == 0 >
	
	<tr>
		<td>Alias</td>
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
		<td><@spring.formSingleSelect "formObject.target.identity.personalData.gender", gender, "size='3'"/></td>
	</tr>
	<tr>
		<td>Appellation</td>
		<td><@spring.formSingleSelect "formObject.target.identity.personalData.appellation", appellation, "size='2'"/></td>
	</tr>

	</#if>
	
	<tr>
		<td>Status</td>
		<td><@spring.formSingleSelect "formObject.target.userState", userState, "size='2'"/></td>
	</tr>
	
	<tr>
		<td colspan="2"><@submit2 /></td>
	</tr>
	
	<@flowKey />
	</form>
</table>
</div>
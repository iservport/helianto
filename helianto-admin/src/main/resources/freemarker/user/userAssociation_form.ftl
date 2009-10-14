<div id="panel">
<table>
	<form method="POST">
	
	<tr style="background: #cccccc;">
		<td colspan="2">User data</td>
	</tr>
	
	<#if formObject.target.child.userKeyEmpty >
	
	<tr>
		<td>User key</td>
		<td><@spring.formInput "formObject.target.child.userKey", 'size="32" maxlength="40"'/></td>
	</tr>

	<tr>
		<td>Create identity if does not exist</td>
		<td><input type="checkBox" name="createIdentity" value="1" /></td>
	</tr>

	<#else>

	<tr>
		<td>Identity [${formObject.target.child.identity.id}]</td>
		<td>${formObject.target.child.identity.principal}</td>
	</tr>

	</#if>
	<tr>
		<td>Status</td>
		<td><@spring.formRadioButtons "formObject.target.child.userState", userState, " ", "size='2'"/></td>
	</tr>
	
	<tr>
		<td colspan="2"><@submit2 /></td>
	</tr>
	
	<@flowKey />
	</form>
</table>
</div>
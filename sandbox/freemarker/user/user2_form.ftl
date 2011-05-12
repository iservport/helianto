<div id="panel">
<table>
	<form method="POST">
	
	<tr style="background: #cccccc;">
		<td colspan="2">User data</td>
	</tr>
	
	<#if userAssociation.child.userKeyEmpty >
	
	<tr>
		<td>User key</td>
		<td><@spring.formInput "userAssociation.child.userKey", 'size="32" maxlength="40"'/></td>
	</tr>

	<#if userAssociation.child.identity?exists >
	<tr>
		<td>Create identity if does not exist</td>
		<td><input type="checkBox" name="createIdentity" value="1" /></td>
	</tr>
	</#if>

	<#elseif userAssociation.child.identity?exists >

	<tr>
		<td>Identity [${userAssociation.child.identity.id}]</td>
		<td>${userAssociation.child.identity.principal}</td>
	</tr>

	</#if>
	<tr>
		<td>Status</td>
		<td><@spring.formRadioButtons "userAssociation.child.userState", userState, " ", "size='2'"/></td>
	</tr>
	
	<tr>
		<td colspan="2"><@submit2 "storeUserAssociation"/></td>
	</tr>
	
	<@flowKey />
	</form>
</table>
</div>
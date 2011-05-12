<div id="panel">
<table>
	<form method="POST">
	
	<tr style="background: #cccccc;">
		<td colspan="2">User data</td>
	</tr>
	
	<#if formObject.target.userKeyEmpty >
	
	<tr>
		<td>User key</td>
		<td><@spring.formInput "formObject.target.userKey", 'size="32" maxlength="40"'/></td>
	</tr>

	<#else>

	<tr>
		<td>User key</td>
		<td>${formObject.target.userKey}</td>
	</tr>

	</#if>
	<tr>
		<td>Status</td>
		<td><@spring.formRadioButtons "formObject.target.userState", userState, " ", "size='2'"/></td>
	</tr>
	
	<tr>
		<td colspan="2"><@submit2 /></td>
	</tr>
	
	<@flowKey />
	</form>
</table>
</div>
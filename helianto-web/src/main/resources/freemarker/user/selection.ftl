<div id="panel">
	<div class="toolbar">
		<@secure "ROLE_ADMIN_MANAGER" ><span class="button grey1"><@anchor "toSelection">> Authorizing entities</@anchor></span></@secure>
	</div>
	<#if userGroup?exists>
	<h2>Users in ${userGroup.userKey}</h2>
	<table>
		<thead>
		<tr>
		  <td colspan="2">User name</td>
		  <td >Status</td>
		</tr>
		</thead>
		<tbody>
		<#list userModel.pages['user'].list?if_exists as user >
		<tr class="row${user_index%2}">
		  <@selectModelTr "${user_index}", "user">${user.id?c}</@selectModelTr>
		  <td >
		      ${user.userKey}<br />
		      ${user.userPrincipal?if_exists}
		  </td>
		  <td >${user.userState?if_exists}</td>
		</tr>
		</#list>
		</tbody>
	</table>
	</#if>
</div>


<div id="panel">

<div class="toolbar">
	<@secure "ROLE_ADMIN_MANAGER" ><span class="button grey1"><@anchor "toSelection">> Authorizing entities</@anchor></span></@secure>
</div>

<h2>Groups</h2>
<table>
<thead>
<tr>
  <td colspan="2">Group name</td>
</tr>
</thead>
<tbody>
<#list userModel.pages['userGroup'].list?if_exists as userGroup >
	<tr class="row${userGroup_index%2}">
	  <@selectModel "${userGroup_index}">${userGroup.id?c}</@selectModel>
	  <td >${userGroup.userKey}</td>
	</tr>
</#list>
</tbody>
</table>
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
  <@selectModel "${user_index}">${user.id?c}</@selectModel>
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


<div id="panel">

	<div id="toolbar">
		<@anchor "editUserGroup">Edit</@anchor> 
		<@anchor "toSelection"><<</@anchor>
	</div>

	<h2>Roles assigned to user group: ${userGroup.userKey}</h2>
	<div class="selectionList">
	<table>
	<thead>
	<tr>
	  <td colspan="2">Service</td>
	  <td >Role</td>
	</tr>
	</thead>
	<tbody>
	<#list userModel.pages['userGroupRole'].list?if_exists as userRole >
	<tr class="row${userRole_index%2}">
		<td><@selectModel "${userRole_index}", "userGroupRole">${userRole.id?c}</@selectModel></td>
		<td >${userRole.serviceName}</td>
		<td >${userRole.serviceExtension}</td>
	</tr>
	</#list>
	</tbody>
	</table>
	</div>

</div>
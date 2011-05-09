<div id="panel">

	<div id="toolbar">
		<@anchor "editUser">Edit</@anchor> 
		<@anchor "toSelection"><<</@anchor>
	</div>
	
	<h2>${user.identity.principal}</h2>
	<p>First Name: <b>${user.identity.personalData.firstName}</b></p>
	<p>Last Name: <b>${user.identity.personalData.lastName}</b></p>
	<p>Appellation: <b>${appellation[user.identity.personalData.appellation]}</b></p>
	<p>Gender: <b>${gender[user.identity.personalData.gender]}</b></p>
	
	<h2>Roles</h2>
	<div class="selectionList">
	<table>
	<thead>
	<tr>
	  <td colspan="2">Service</td>
	  <td >Role</td>
	  <td >Source</td>
	</tr>
	</thead>
	<tbody>
	<#list userModel.pages['userRole'].list?if_exists as userRole >
	<tr class="row${userRole_index%2}">
		<td><@selectModel "${userRole_index}", "userRole">${userRole.id?c}</@selectModel></td>
		<td >${userRole.serviceName}</td>
		<td >${userRole.serviceExtension}</td>
		<td >${userRole.userGroup.userKey}</td>
	</tr>
	</#list>
	</tbody>
	</table>
	</div>

</div>
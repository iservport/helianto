<div id="panel">

	<div class="toolbar">
		<@secure "ROLE_ADMIN_MANAGER" ><@create "Authorization">+ Authorizing entity</@create></@secure>
	</div>
	<p>
		${authorizationModel.pages['authorization'].listSize!"0"} authorization(s) found.
	</p>
	
	<div class="selectionList">
	
		<table>
		<thead>
			<tr >
				<td colspan="2">Authorizing entity</td>
				<td >Authorized user</td>
				<td >Authorization date</td>
			</tr>
		</thead>
		<tbody>
		<#list authorizationModel.pages['authorization'].list?if_exists as authorization >
			<tr class="row${authorization_index%2}">
				<td><@selectModel "${authorization_index}", "Authorization", "index", "", "Select authorization", "ROLE_ADMIN, ROLE_USER">${authorization.entity.id}</@selectModel></td>
				<td><@selectModel "${authorization_index}", "Authorization", "index", "", "Select authorization", "ROLE_ADMIN, ROLE_USER">${authorization.entity.alias?if_exists}</@selectModel></td>
				<td>${authorization.userKey}</td>
				<td>${authorization.entity.installDate?date}</td>
			</tr>
		</#list>
		
		</tbody>
		</table>
	
	</div>
</div>

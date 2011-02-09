<h2>Available authorizations</h2>

<div class="toolbar">
	<@secure "ROLE_ADMIN_MANAGER" ><@create "Authorization", "class='button grey1'">+ Authorizing entity</@create></@secure>
</div>

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
	<#list authorizationModel.list?if_exists as authorization >
		<tr>
			<@select "${authorization_index}">${authorization.entity.id}</@select>
			<@select "${authorization_index}">${authorization.entity.alias?if_exists}</@select>
			<td>${authorization.userKey}</td>
			<td>${authorization.entity.installDate?date}</td>
		</tr>
	</#list>
	
	</tbody>
	</table>

</div>


<table >
<thead>
	<tr >
	<td colspan="3">Groups</td>
	</tr>
</thead>
<tbody>
<#list userGroupList?if_exists as item >
<tr >
	<@select "${item_index}", "UserGroup", "parentIndex", "&syncParent=true" >${item.id}</@select>
	<@select "${item_index}", "UserGroup", "parentIndex", "&syncParent=true" >${item.userKey}</@select>
</tr>
</#list>
</tbody>
</table>
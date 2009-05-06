<table bgcolor="#cccccc">
<thead>
<tr>
	<td>Id</td>
	<td>Alias</td>
</tr>
</thead>
<tbody>
<#list targetList?if_exists as target>
<tr>
	<td>${target.id}</td>
	<td>${target.alias}</td>
</tr>
</#list>
</tbody>
</table>
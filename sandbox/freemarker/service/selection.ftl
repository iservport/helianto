<div id="panel">
<p>Found ${targetListSize?if_exists} service(s) under this namespace.</p>
<table>
<thead>
<tr>
	<td>Id</td>
	<td>Name</td>
</tr>
</thead>
<tbody>
<#list targetList?if_exists as target>
<tr class="row${target_index%2}">
	<@select "${target_index}">${target.id}</@select>
	<td>${target.serviceName?if_exists}</td>
</tr>
</#list>
</tbody>
</table>
</div>
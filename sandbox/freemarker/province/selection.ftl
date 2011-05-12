<div id="mainbar">
	<@create>New province</@create>
</div>

<div id="panel">
<p>Found ${provinceFilter.listSize?if_exists} province(s) under this namespace.</p>
<table>
<thead>
<tr>
	<td>Id</td>
	<td>Code</td>
	<td>Name</td>
</tr>
</thead>
<tbody>
<#list provinceFilter.list?if_exists as item >
<tr class="row${item_index%2}">
	<@select "${item_index}">${item.id}</@select>
	<@select "${item_index}">${item.provinceCode?if_exists}</@select>
	<td>${item.provinceName?if_exists}</td>
</tr>
</#list>
</tbody>
</table>
</div>
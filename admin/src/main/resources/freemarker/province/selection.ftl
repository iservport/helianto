<table bgcolor="#cccccc">
<p>Found ${targetListSize?if_exists} province(s) under this namespace.</p>
<thead>
<tr>
	<td>Id</td>
	<td>Code</td>
	<td>Name</td>
</tr>
</thead>
<tbody>
<#list targetList?if_exists as target>
<tr>
    <#-- check the macros supplied with frame.ftl to see how they work -->
	<@select "${target_index}">${target.id}</@select>
	<td>${target.provinceCode?if_exists}</td>
	<td>${target.provinceName?if_exists}</td>
</tr>
</#list>
</tbody>
</table>
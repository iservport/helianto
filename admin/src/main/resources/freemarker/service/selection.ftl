<table bgcolor="#cccccc">
<p>Found ${targetListSize?if_exists} service(s) under this namespace.</p>
<thead>
<tr>
	<td>Id</td>
	<td>Name</td>
</tr>
</thead>
<tbody>
<#list targetList?if_exists as target>
<tr>
    <#-- check the macros supplied with frame.ftl to see how they work -->
	<@select "${target_index}">${target.id}</@select>
	<td>${target.serviceName?if_exists}</td>
</tr>
</#list>
</tbody>
</table>
<div id="panel">
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
    <#-- check the macros supplied with frame.ftl to see how they work -->
	<@select "${target_index}">${target.id}</@select>
	<td>${target.alias?if_exists}</td>
</tr>
</#list>
</tbody>
</table>
</div>
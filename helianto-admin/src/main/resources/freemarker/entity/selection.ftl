<!--<div id="mainbar">-->
<!--	<@create "createEntity">New entity</@create>-->
<!--</div>-->
<div id="panel">

	<h2 >Entities</h2>
	<p>Here is a list of entities you are authorized as user.</p>
	
	<table >
	<thead>
	<tr>
		<td>Entity Id</td>
		<td>User group</td>
		<td>Entity alias</td>
		<td>Since</td>
		<td>State</td>
	</tr>
	</thead>
	<tbody>
	<#list userAssociationFilter.list?if_exists as item >
	<tr class="row${item_index%2}">
	    <#-- check the macros supplied with frame.ftl to see how they work -->
		<td>${item.parent.entity.id}</td>
		<@select "${item_index}">${item.parent.id}</@select>
		<td>${item.parent.entity.alias?if_exists}</td>
		<td>${item.parent.lastEvent?string}</td>
		<td>${userState[item.parent.userState]}</td>
	</tr>
	</#list>
	</tbody>
	</table>

</div>
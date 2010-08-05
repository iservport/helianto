<div id="mainbar">
	<@create "userAssociation">New user group</@create> | 
	<@anchor "toSelection">Back</@anchor>
</div>

<div id="panel">

	<h2>Users and groups</h2>
	
	<table>
	<thead>
	<tr>
	  <td colspan="2">User principal</td>
	  <td >Status</td>
	  <td >Type</td>
	  <td >Personal data</td>
	</tr>
	</thead>
	<tbody>
	<#list userAssociationList?if_exists as item >
	<tr class="row${item_index%2}">
	  <#-- the @select macro, embedded in frame.ftl, is appropriate 
	       to generate the select transition -->
	  <@select "${item_index}", "userAssociation" >${item.id?c}</@select>
	  <@select "${item_index}", "userAssociation" >${item.child.userKey}</@select>
	  <td >${userState[item.child.userState]}</td>
	  <td >${type[item.child.class]}</td>
	  <td >${item.child.userName?if_exists}</td>
	</tr>
	</#list>
	</tbody>
	</table>

</div>
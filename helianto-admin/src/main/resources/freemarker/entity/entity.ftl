<div id="mainbar">
	<#if true >
	<@create "userGroupAssociation">New top level user group</@create> | 
	</#if>
	<@create "userAssociation">New user</@create> | 
	<@anchor "toSelection">Back</@anchor>
</div>

<div id="panel">

	<h2>Users and groups</h2>
	<p>Users and groups are created in four different situations: (1) top level groups that require the manager to be the
	in the association, (2) sub groups having a top level group as parent, (3) users related to the top level USER group, 
	and (4) users related to any other group.</p>
	
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
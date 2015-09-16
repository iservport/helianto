<div id="panel">
<h2>User <b>${userAssociation.child.userKey}</b> <small>[${userAssociation.child.id}]</small></h2>
<#if userAssociation.child.userName?exists>
<p>Personal data: <b>${userAssociation.child.userName}</b></p>
</#if>
<h3>Assigned roles</h3>
<table>
<thead>
<tr>
  <td colspan="2">Service</td>
  <td >Role</td>
  <td >Source</td>
</tr>
</thead>
<tbody>
<#list userAssociation.child.roleList?if_exists as target >
<tr class="row${target_index%2}">
  <@select "${target_index}", "editUserRole" >${target.id?c}</@select>
  <td >${target.serviceName}</td>
  <td >${target.serviceExtension}</td>
  <td >${target.userGroup.userKey}</td>
</tr>
</#list>
</tbody>
</table>

</div>
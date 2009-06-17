<div id="panel">
<h2>User <b>${userAssociation.child.userKey}</b> <small>[${userAssociation.child.id}]</small></h2>
<#if userAssociation.child.userName?exists>
<p>Personal data: <b>${userAssociation.child.userName}</b></p>
</#if>
<table>
<thead style="background: #cccccc;">
<tr>
  <td colspan="4" style="text-align: center;"><b>Assigned roles</b></td>
</tr>
<tr>
  <td colspan="2">Service</td>
  <td >Role</td>
  <td >Source</td>
</tr>
</thead>
<tbody>
<#list userAssociation.child.roleList?if_exists as target >
<tr>
  <#-- this macro, embedded in frame.ftl, is appropriate 
       to generate the select transition -->
  <@select "${target_index}", "editUserRole" >${target.id?c}</@select>
  <td >${target.serviceName}</td>
  <td >${target.serviceExtension}</td>
  <td >${target.userGroup.userKey}</td>
</tr>
</#list>
</tbody>
</table>

</div>
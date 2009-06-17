<div id="panel">
<h2>User group <b>${userGroup.userKey}</b> <small>[${userGroup.id}]</small></h2>
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
<#list userGroup.roleList?if_exists as target >
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
<p>&nbsp;</p>
<table>
<thead style="background: #cccccc;">
<tr>
  <td colspan="4" style="text-align: center;"><b>Assigned users</b></td>
</tr>
<tr>
  <td colspan="2">User principal</td>
  <td >Status</td>
  <td >Personal data</td>
</tr>
</thead>
<tbody>
<#list userGroup.childAssociationList?if_exists as target >
<tr>
  <#-- this macro, embedded in frame.ftl, is appropriate 
       to generate the select transition -->
  <@select "${target_index}", "selectUser" >${target.child.id?c}</@select>
  <td >${target.child.userKey}</td>
  <td >${userState[target.child.userState]}</td>
  <td >${target.child.userName?if_exists}</td>
</tr>
</#list>
</tbody>
</table>

</div>
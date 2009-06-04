<div id="panel">
<h2>User <b>${user.id}</b></h2>
<p>Principal: <b>${user.userPrincipal}</b></p>
<p>Personal data: <b>${user.userName}</b></p>
<p>Assigned roles:</p>
<table>
<thead style="background: #cccccc;">
<tr>
  <td colspan="2">Service</td>
  <td >Role</td>
  <td >Source</td>
</tr>
</thead>
<tbody>
<#list user.roleList?if_exists as target >
<tr>
  <#-- this macro, embedded in frame.ftl, is appropriate 
       to generate the select transition -->
  <@select "${target_index}", "editUserRole" >${target.id?c}</@select>
  <td >${target.serviceName}</td>
  <td >${target.serviceExtension}</td>
  <td >${target.userGroup.userPrincipal}</td>
</tr>
</#list>
</tbody>
</table>

</div>
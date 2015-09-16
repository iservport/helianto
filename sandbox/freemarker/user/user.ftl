<div id="panel">
<h2>User <b>${user.userKey}</b> <small>[${user.id}]</small></h2>
<#if user.userName?exists>
<p>Personal data: <b>${user.userName}</b></p>
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
<#list user.roleList?if_exists as target >
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
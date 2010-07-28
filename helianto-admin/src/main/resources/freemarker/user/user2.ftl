<div id="panel">
<h2><@sayGroup /><b>${user.userKey}</b> <small>[${user.id}]</small></h2>
<#if user.discriminator!='G' >
<p>Personal data: <b>${user.userName}</b></p>
</#if>
<h2>Assigned roles</h2>
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
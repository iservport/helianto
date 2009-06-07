<div id="panel">
<h2>User <b>${user.userKey}</b> <small>[${user.id}]</small></h2>
<#if user.userName?exists>
<p>Personal data: <b>${user.userName}</b></p>
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
<#list user.roleList?if_exists as target >
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
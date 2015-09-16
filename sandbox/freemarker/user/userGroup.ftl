<div id="panel">
<h2>User <#if !userGroup.identity?exists>group </#if><b>${userGroup.userKey}</b> <small>[${userGroup.id}]</small>
<#if userGroup.userState=='I'><span style="color: red;">INATIVO</span></#if>
</h2>
<h3>Assigned roles</h3>
<table>
<thead>
<tr>
  <td colspan="2">Service</td>
  <td >Role</td>
  <td >Inherited from</td>
</tr>
</thead>
<tbody>
<#list userGroup.roleList?if_exists as target >
<tr class="row${target_index%2}">
  <@select "${target_index}", "editUserRole" >${target.id?c}</@select>
  <td >${target.serviceName}</td>
  <td >${target.serviceExtension}</td>
  <td >${target.userGroup.userKey}</td>
</tr>
</#list>
</tbody>
</table>
<p>&nbsp;</p>
<#if !userGroup.identity?exists>
<h3>Assigned users</h3>
<table>
<thead>
<tr>
  <td colspan="2">User key</td>
  <td >Status</td>
  <td >Personal data</td>
</tr>
</thead>
<tbody>
<#list userGroup.childAssociationList?if_exists as target >
<tr class="row${target_index%2}">
  <@select "${target_index}", "selectUser" >${target.child.id?c}</@select>
  <td >${target.child.userKey}</td>
  <td >${userState[target.child.userState]}</td>
  <td >${target.child.userName?if_exists}</td>
</tr>
</#list>
</tbody>
</table>
</#if>

</div>
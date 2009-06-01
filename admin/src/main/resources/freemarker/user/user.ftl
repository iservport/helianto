<div id="panel">
<h2>Identity principal: <b>${user.userPrincipal}</b></h2>
<p>Assigned roles:</p>
<table>
<thead style="background: #cccccc;">
<tr>
  <td colspan="2">Service</td>
  <td >Role</td>
</tr>
</thead>
<tbody>
<#list user.roleList?if_exists as target >
<tr>
  <#-- this macro, embedded in frame.ftl, is appropriate 
       to generate the select transition -->
  <@select "${target_index}", "selectRole" >${target.id?c}</@select>
  <td >xxx</td>
  <td >xxx</td>
</tr>
</#list>
</tbody>
</table>

</div>
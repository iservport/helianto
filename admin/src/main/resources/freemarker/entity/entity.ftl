<div id="panel">
<h2>Entity alias: <b>${entity.alias}</b></h2>
<p>Users sharing this entity:</p>
<table>
<thead style="background: #cccccc;">
<tr>
  <td colspan="2">User principal</td>
  <td >Status</td>
  <td >Type</td>
  <td >Personal data</td>
</tr>
</thead>
<tbody>
<#list entity.userList?if_exists as target >
<tr>
  <#-- this macro, embedded in frame.ftl, is appropriate 
       to generate the select transition -->
  <@select "${target_index}", "selectUser" >${target.id?c}</@select>
  <td >${target.userPrincipal}</td>
  <@select "${target_index}", "editUser" >${userState[target.userState]}</@select>
  <td >${type[target.class]}</td>
  <td >${target.userName}</td>
</tr>
</#list>
</tbody>
</table>

</div>
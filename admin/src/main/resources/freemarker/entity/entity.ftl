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
  <#if target.class=='class org.helianto.core.UserGroup' >
  	<@select "${target_index}", "selectUserGroup" >${target.id?c}</@select>
  <#else>
  	<@select "${target_index}", "selectUser" >${target.id?c}</@select>
  </#if>
  <td >${target.userKey}</td>
  <@select "${target_index}", "editUser" >${userState[target.userState]}</@select>
  <td >${type[target.class]}</td>
  <td >${target.userName?if_exists}</td>
</tr>
</#list>
</tbody>
</table>

</div>
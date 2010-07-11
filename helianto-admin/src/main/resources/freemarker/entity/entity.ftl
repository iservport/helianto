<div id="mainbar">
	<@anchor "createUserGroup">New user group</@anchor> | 
	<@anchor "toSelection">Voltar</@anchor>
</div>

<div id="panel">

	<h2>Users and groups</h2>
	
	<table>
	<thead>
	<tr>
	  <td colspan="2">User principal</td>
	  <td >Status</td>
	  <td >Type</td>
	  <td >Personal data</td>
	</tr>
	</thead>
	<tbody>
	<#list userList?if_exists as target >
	<tr class="row${target_index%2}">
	  <#-- this macro, embedded in frame.ftl, is appropriate 
	       to generate the select transition -->
	  <#if target.class=='class org.helianto.core.UserGroup' >
	  	<@select "${target_index}", "selectUserGroup" >${target.id?c}</@select>
	  <#else>
	  	<@select "${target_index}", "selectUser" >${target.id?c}</@select>
	  </#if>
	  <td >${target.userKey}</td>
	  <td >${userState[target.userState]}</td>
	  <td >${type[target.class]}</td>
	  <td >${target.userName?if_exists}</td>
	</tr>
	</#list>
	</tbody>
	</table>

</div>
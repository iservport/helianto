<div id="panel">
	<h5>
		${userModel.pages['user'].listSize!"0"} user(s) found<#if userGroup?exists > in group <@anchor "showUserGroup">${userGroup.userKey}</@anchor></#if>.
		<div class="toolbar">
		<#if userGroup.userKey=='user'>
			<@secure "ROLE_USER_ALL" ><@create "User" >+ User</@create></@secure>
		</#if>
		<@secure "ROLE_USER_ALL" ><@create "UserGroup" >+ UserGroup</@create></@secure>
		</div>
	</h5>
	<#if userGroup?exists>
	<table>
		<thead>
		<tr>
		  <td colspan="2">User name</td>
		  <td >Status</td>
		</tr>
		</thead>
		<tbody>
		<#list userModel.pages['user'].list?if_exists as user >
		<tr class="row${user_index%2}">
		  <td ><@selectModel "${user_index}", "user">${user.id?c}</@selectModel></td>
		  <td >
		      <@selectModel "${user_index}", "user">${user.userName}</@selectModel><br />
		      <#if user.identity?exists >${user.identity.optionalAlias}</#if>
		  </td>
		  <td><@selectModel "${user_index}", "user">${user.userKey}</@selectModel></td>
		  <td >${userState[user.userState?if_exists]}</td>
		</tr>
		</#list>
		</tbody>
	</table>
	</#if>
</div>


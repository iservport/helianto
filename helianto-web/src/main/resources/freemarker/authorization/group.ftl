<div class="groupSelection" >
	<#list authorizationModel.list?if_exists as authorization >
		<a href="${flowExecutionUrl}&_eventId=selectAuthorization&index=${authorization_index}"><img src="../images/content/users.png"  width="128" height="128" /></a>
	</#list>
</div>
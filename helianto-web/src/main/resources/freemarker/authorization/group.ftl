<div class="groupSelection" >
	<#list authorizationModel.pages['authorization'].list?if_exists as authorization >
		<a href="${flowExecutionUrl}&_eventId=selectAuthorization&pages['authorization'].index=${authorization_index}"><img src="../images/content/users.png"  width="128" height="128" /></a>
	</#list>
</div>
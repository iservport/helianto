<h4>Current entity: ${currentUser.principal.entity.alias}</h4>

<#if authorizationModel.item?exists && authorizationModel.item.id!=currentUser.principal.user.id >
<form method="POST">

	<p>Authorize this entity:</p>
	<p><@spring.formInput "authorizationModel.item.entity.alias", 'size="20" readonly="readonly"' /></p>
	
	<@perr/>
	<input type="hidden" name="_eventId" value="authorize" />
	<input type="submit" value="AUTHORIZE" />
	<@flowKey/>
	
</form>
</#if>


<h2>${currentUser.principal.entity.alias}</h2>

<#if authorizationModel.item?exists && authorizationModel.item.id!=currentUser.principal.user.id >
<form method="POST">

	<@spring.formHiddenInput "authorizationModel.item.entity.alias" />
	
	<@perr/>
	<input type="hidden" name="_eventId" value="authorize" />
	<input type="submit" value="Switch to ${authorizationModel.item.entity.alias}" />
	<@flowKey/>
	
</form>

<p>To user:</p>
<p>${authorization.userKey}</p>

<p>Authorized since:</p>
<p>${authorization.entity.installDate?date}</p>

</#if>


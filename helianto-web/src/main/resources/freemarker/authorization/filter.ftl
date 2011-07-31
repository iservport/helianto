<h2>${currentUser.principal.entity.alias}</h2>

<#if authorization?exists && authorization.id!=currentUser.principal.user.id >

<p>To user:</p>
<p>${authorization.userKey}</p>

<p>Authorized since:</p>
<p>${authorization.entity.installDate?date}</p>

<form method="POST">

	<input type="hidden" name="_eventId" value="authorize" />
	<input type="submit" value="Switch to ${authorization.entity.alias}" />
	<@flowKey/>
	
</form>

</#if>


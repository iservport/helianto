<div id="mainbar">
		<@anchor "cancelForm">Cancel</@anchor>
</div>

<div id="panel">

	<form method="post" >
		<p>Entity alias:</p>
		<p><@spring.formInput "entity.alias", 'size="20" maxlength="20"'/></p>
		<@perr/>
		<input type="hidden" name="_eventId" value="storeEntity" />
		<input type="submit" value="Instalar" />
		<@flowKey/>
	
	</form>

</div>
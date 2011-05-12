<div id="panel">

	<div id="toolbar">
		<@anchor "cancelForm">Cancel</@anchor>
	</div>
	
	<form method="post" >
		<p>Entity alias:</p>
		<p><@spring.formInput "entity.alias", 'size="20" maxlength="20"'/></p>

		<@perr/>
		<input type="hidden" name="_eventId" value="storeEntity" />
		<input type="submit" value="Install" />
		<@flowKey/>
	
	</form>

</div>
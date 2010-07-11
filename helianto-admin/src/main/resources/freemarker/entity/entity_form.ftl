<div id="mainbar">
		<@anchor "cancelForm">Cancel</@anchor>
</div>

<div id="panel">

	<form method="post" >
		<p>Entity alias:</p>
		<p><@spring.formInput "userGroup.entity.alias", 'size="20" maxlength="20"'/></p>
		<@perr/>
		<@submit2 />
		<@flowKey />
	</form>

</div>
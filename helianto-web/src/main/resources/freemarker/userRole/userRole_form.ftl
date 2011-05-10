<div id="panel">

	<div id="toolbar">
		<@anchor "cancelForm">Cancel</@anchor>
	</div>
	
	<h2>User group: ${userRole.userGroup.userKey}</h2>

	<form method="POST">
	
		<p>Service extension</p>
		<p><@spring.formInput "userRole.serviceExtension", "size='20'"/></p>
		
		<@perr/>
		<input type="hidden" name="_eventId" value="storeUserRole" />
		<input type="submit" value="Update" />
		<@flowKey/>
	
	</form>
	
	<hr />
	
	<form method="POST">
	
		<p><input type="checkbox" name="confirmRemoval" value="R"/> Remove</p>
		<@perr/>
		<input type="hidden" name="_eventId" value="removeUserRole" />
		<input type="submit" value="Remove" />
		<@flowKey/>
	
	</form>
	
	
</div>
<div id="panel">

	<div id="toolbar">
		<@anchor "cancelForm">Cancel</@anchor>
	</div>
	
	<h2>Confirm password</h2>

	<form method="POST">
	
		<p>Password</p>
		<p><@spring.formInput "credential.password", "size='20'", "password"/></p>
		
		<p>Confirmation</p>
		<p><@spring.formInput "credential.verifyPassword", "size='20'", "password"/></p>
		
		<@perr/>
		<input type="hidden" name="_eventId" value="storeCredential" />
		<input type="submit" value="Update" />
		<@flowKey/>
	
	</form>
	
</div>
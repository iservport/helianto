<div id="panel">

	<div id="toolbar">
		<@anchor "cancelForm">Cancel</@anchor>
	</div>
	
	<h2>${user.userKey}</h2>

	<p>Optional alias: ${user.identity.optionalAlias}</p>
	<p>Full name:  ${user.identity.identityName}</p>
	
	<form method="POST">
	
		<p>Status</p>
		<p><@spring.formRadioButtons "user.userState", userState, " ", "size='2'"/></p>
		
		<@perr/>
		<input type="hidden" name="_eventId" value="storeUser" />
		<input type="submit" value="Update" />
		<@flowKey/>

	</form>

</div>
<div id="panel">

	<div id="toolbar">
		<@anchor "cancelForm">Cancel</@anchor>
	</div>
	
	<h2>${userAssociation.child.userKey}</h2>

	<p>Optional alias: ${userAssociation.child.identity.optionalAlias}</p>
	<p>Full name:  ${userAssociation.child.identity.identityName}</p>
	
	<form method="POST">
	
		<p>Status</p>
		<p><@spring.formRadioButtons "userAssociation.child.userState", userState, " ", "size='2'"/></p>
		
		<@perr/>
		<input type="hidden" name="_eventId" value="storeUserAssociation" />
		<input type="submit" value="Update" />
		<@flowKey/>

	</form>

</div>
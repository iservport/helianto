<div id="panel">

	<div id="toolbar">
		<@anchor "cancelForm">Cancel</@anchor>
	</div>
	
	<form method="POST">
	
		<p>Key</p>
		<p><@spring.formInput "userGroup.userKey", "size='20'"/></p>
		
		<p>Status</p>
		<p><@spring.formRadioButtons  "userGroup.userState", userState, " ", "size='2'"/></p>
		
		<@perr/>
		<input type="hidden" name="_eventId" value="storeUserGroup" />
		<input type="submit" value="Update" />
		<@flowKey/>
	
	</form>
	
</div>
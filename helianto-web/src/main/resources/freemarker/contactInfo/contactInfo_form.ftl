<div id="panel">

	<div id="toolbar">
		<@anchor "cancelForm">Cancel</@anchor>
	</div>
	
	<form method="post" >
		<p>Address:</p>
		<p><@spring.formInput "contactInfo.contactAddress", 'size="40" maxlength="64"'/></p>

		<p>Type:</p>
		<p><@spring.formRadioButtons  "contactInfo.contactType", contactType, " " /></p>

		<@perr/>
		<input type="hidden" name="_eventId" value="storeContactInfo" />
		<input type="submit" value="Update" />
		<@flowKey/>
	
	</form>

</div>
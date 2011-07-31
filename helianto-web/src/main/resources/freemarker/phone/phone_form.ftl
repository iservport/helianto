<div id="panel">

	<div id="toolbar">
		<@anchor "cancelForm">Cancel</@anchor>
	</div>
	
	<form method="post" >
		<p>Area code:</p>
		<p><@spring.formInput "phone.areaCode", 'size="4" maxlength="4"'/></p>

		<p>Phone number:</p>
		<p><@spring.formInput "phone.phoneNumber", 'size="12" maxlength="12"'/></p>

		<p>Branch:</p>
		<p><@spring.formInput "phone.branch", 'size="6" maxlength="6"'/></p>

		<p>Type:</p>
		<p><@spring.formRadioButtons  "phone.phoneType", phoneType, " ", "size='3'"/></p>

		<@perr/>
		<input type="hidden" name="_eventId" value="storePhone" />
		<input type="submit" value="Update" />
		<@flowKey/>
	
	</form>

</div>
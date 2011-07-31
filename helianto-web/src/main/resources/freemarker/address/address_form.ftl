<div id="panel">

	<div id="toolbar">
		<@anchor "cancelForm">Cancel</@anchor>
	</div>
	
	<h2>${address.cityName}</h2>
	
	<form method="post" >
		<p>Type:</p>
		<p><@spring.formRadioButtons  "address.addressType", addressType, " " /></p>
		
		<p>Address:</p>
		<p><@spring.formInput "address.address1", 'size="40" maxlength="64"'/></p>
		<p><@spring.formRadioButtons  "address.addressClassifier", addressClassifier, " " /></p>
		
		<p>Number, additional info:</p>
		<p><@spring.formInput "address.addressNumber", 'size="8" maxlength="8"'/><@spring.formInput "address.addressDetail", 'size="16" maxlength="24"'/></p>
		
		<p>County or district:</p>
		<p><@spring.formInput "address.address2", 'size="30" maxlength="32"'/></p>
		
		<p>Other address data:</p>
		<p><@spring.formInput "address.address3", 'size="20" maxlength="32"'/></p>
		
		<p>Post office box:</p>
		<p><@spring.formInput "address.postOfficeBox", 'size="10" maxlength="10"'/></p>
		
		<@perr/>
		<input type="hidden" name="_eventId" value="storeAddress" />
		<input type="submit" value="Update" />
		<@flowKey/>
		
	</form>

</div>
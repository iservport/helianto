<div id="panel">

	<div id="toolbar">
		<@anchor "cancelForm">Cancel</@anchor>
	</div>
	
	<h2>Principal: ${identity.principal}</h2>

	<form method="POST">
	
		<p>Optional alias</p>
		<p><@spring.formInput "identity.optionalAlias", "size='20'"/></p>
		
		<p>First name</p>
		<p><@spring.formInput "identity.personalData.firstName", "size='20'"/></p>
		
		<p>Last name</p>
		<p><@spring.formInput "identity.personalData.lastName", "size='20'"/></p>
		
		<p>Gender</p>
		<p><@spring.formRadioButtons  "identity.personalData.gender", gender, " ", "size='3'"/></p>
		
		<p>Appellation</p>
		<p><@spring.formRadioButtons "identity.personalData.appellation", appellation, " ", "size='2'"/></p>
		
		<p>Identity type</p>
		<p><@spring.formRadioButtons "identity.identityType", identityType, " ", "size='2'"/></p>
		
		<p>Notification</p>
		<p><@spring.formRadioButtons "identity.notification", notification, " ", "size='2'"/></p>
		
		<@perr/>
		<input type="hidden" name="_eventId" value="storeIdentity" />
		<input type="submit" value="Atualizar" />
		<@flowKey/>
	
	</form>
	
</div>
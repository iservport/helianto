<div id="panel">

		<h2>Existing principal: ${identity.principal}</h2>
	
		<p>Optional alias: ${identity.optionalAlias}</p>
		
		<p>Name: ${identity.personalData.firstName} ${identity.personalData.lastName}</p>
		
		<p>Gender: ${gender[identity.personalData.gender]}</p>
		
		<p>Appellation: ${identity.personalData.appellation}</p>
		
		<p>Identity type: ${identityType[identity.identityType]}</p>
		
		<p>Notification: ${notification[identity.notification]}</p>

		<h4>Confirm?</h4>
		<p><@anchor "confirm">YES</@anchor> | <@anchor "discard">NO</@anchor></p>
	

</div>

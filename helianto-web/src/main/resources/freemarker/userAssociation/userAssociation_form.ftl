<div id="panel">

	<form method="POST">
	
	<#if userAssociation.child.userKeyEmpty >
	
		<p>User key:</p>
		<p><@spring.formInput "userAssociation.child.userKey", 'size="32" maxlength="40"'/></p>
	
		<p>Create identity if does not exist</p>
		<p><input type="checkBox" name="createIdentity" value="1" /></p>

	<#else>

		<p>Identity [${userAssociation.child.identity.id}]</p>
		<p>${userAssociation.child.identity.principal}</p>

	</#if>
	
	<p>Status</p>
	<p><@spring.formRadioButtons "userAssociation.child.userState", userState, " ", "size='2'"/></p>
	
	<@perr/>
	<input type="hidden" name="_eventId" value="storeUserAssociation" />
	<input type="submit" value="Atualizar" />
	<@flowKey/>

	</form>

</div>
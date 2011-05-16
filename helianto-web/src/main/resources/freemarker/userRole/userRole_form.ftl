<div id="panel">

	<div id="toolbar">
		<@anchor "cancelForm">Cancel</@anchor>
	</div>
	
	<h2>User group: ${userRole.userGroup.userKey}</h2>

	<#if userGroup.id==userRole.userGroup.id >
	<#-- edit service extensions only if the group calling this flow is the same as in the user role -->
	<form method="POST">
	
		<p>Service extensions</p>
		<p><@spring.formInput "userRole.serviceExtension", "size='20'"/></p>
		
		<@perr/>
		<input type="hidden" name="_eventId" value="storeUserRole" />
		<input type="submit" value="Update" />
		<@flowKey/>
	
	</form>
	<hr />
	
	<#else>
		<p>Service extensions: ${userRole.serviceExtension}</p>
		
	</#if>
	
	<form method="POST">
	
		<p><input type="checkbox" name="confirmRemoval" value="R"/> Remove</p>
		
		<input type="hidden" name="_eventId" value="removeUserRole" />
		<input type="submit" value="Remove" />
		<@flowKey/>
	
	</form>
	
	
</div>
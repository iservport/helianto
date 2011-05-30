<div id="panel">

	<div id="toolbar">
		<@anchor "cancelForm">Cancel</@anchor>
	</div>
	
	<h2>${privateDocument.docCode}</h2>

	<form method="POST">
	
		<p>Type</p>
		<p><@spring.formSingleSelect "privateDocument.contentType", privateDocumentType, 'size="3"' /> </p>
		
		<p>Content</p>
		<p><input type="file" name="file"/></p>
		
		<@perr/>
		<input type="submit" name="_eventId_storePrivateDocument" value="Save" />
		<input type="submit" name="_eventId_uploadPrivateDocument" value="Updload and Save" />
		<@flowKey/>

	</form>

</div>
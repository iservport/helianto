<form method="POST">

	<h4>Type:</h4>
	<p><@spring.formSingleSelect "privateDocumentModel.form.contentType", privateDocumentTypeFilter, 'size="3"' /> </p>

	<p><@submit2 "applyFilter", "Search" /></p>
	<@flowKey/>
</form>

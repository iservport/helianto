<form method="POST">

	<h4>Status:</h4>
	<p><@spring.formInput "identity.principal" 'size="40"' /> </p>

	<p><@submit2 "applyFilter", "Search" /></p>
	<@flowKey/>
</form>

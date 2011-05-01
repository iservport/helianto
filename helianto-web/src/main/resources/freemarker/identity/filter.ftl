<div id="panel">

	<form method="POST">

		<h4>Principal:</h4>
		<p><@spring.formInput "identity.principal" 'size="40"' /> </p>
	
		<@perr/>
		<input type="hidden" name="_eventId" value="applyFilter" />
		<input type="submit" value="Search" />
		<@flowKey/>

	</form>

</div>

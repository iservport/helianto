<form method="POST">

	<h4>Status:</h4>
	<p><@spring.formSingleSelect "userModel.filter.userState", userStateFilter, 'size="3"' /> </p>

	<p><@submit2 "applyFilter", "Search" /></p>
	<@flowKey/>
</form>
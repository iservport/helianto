<form action="home.htm" method="POST">

	<h4>Status:</h4>
	<p><@spring.formSingleSelect "filter.userState", userState, 'size="2"' /> </p>

	<h4>Search name:</h4>
	<p><@spring.formInput "filter.identityPrincipal", 'size="20" maxlength="20"'/> </p>

	<p><@submit2 "applyFilter", "Search" /></p>
	<@flowKey/>
</form>

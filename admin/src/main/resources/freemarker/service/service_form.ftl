<form action="home.htm" method="post" >
	<p>Service name:</p>
	<p><@spring.formInput "formObject.target.serviceName", 'size="20" maxlength="32"'/></p>
	<@perr/>
	<@submit2 />
	<@flowKey />
</form>
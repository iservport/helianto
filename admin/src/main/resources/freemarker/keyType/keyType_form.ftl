<form action="home.htm" method="post" >
	<p>Key code:</p>
	<p><@spring.formInput "formObject.target.keyCode", 'size="20" maxlength="20"'/></p>
	<@perr/>
	<p>Key name:</p>
	<p><@spring.formInput "formObject.target.keyName", 'size="32" maxlength="32"'/></p>
	<p>Purpose:</p>
	<p><@spring.formTextarea "formObject.target.purpose", 'rows="2" style="width: 95%;"'/></p>
	<@submit2 />
	<@flowKey />
</form>
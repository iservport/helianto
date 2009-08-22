<form method="post" >
	<p>Province code:</p>
	<p><@spring.formInput "formObject.target.provinceCode", 'size="3" maxlength="3"'/></p>
	<@perr/>
	<p>Province name:</p>
	<p><@spring.formInput "formObject.target.provinceName", 'size="20" maxlength="32"'/></p>
	<@submit2 />
	<@flowKey />
</form>
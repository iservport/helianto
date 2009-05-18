<form action="home.htm" method="post" >
	<p>Province code:</p>
	<p><@spring.formInput "formObject.target.provinceCode" /></p>
	<@perr/>
	<p>Province name:</p>
	<p><@spring.formInput "formObject.target.provinceName" /></p>
	<@submit2 />
	<@flowKey />
</form>
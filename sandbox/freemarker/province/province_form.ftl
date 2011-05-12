<div id="mainbar">
	<@anchor "cancelForm">Cancel</@anchor>
</div>

<div id="panel">

<form method="post" >
	<p>Province code:</p>
	<p><@spring.formInput "province.provinceCode", 'size="3" maxlength="3"'/></p>
	<@perr/>
	<p>Province name:</p>
	<p><@spring.formInput "province.provinceName", 'size="20" maxlength="32"'/></p>
	<@submit2 />
	<@flowKey />
</form>

</div>

<div id="panel">
<table>
	<form action="home.htm" method="POST">
	
	<tr>
		<td>Service</td>
		<td><@spring.formSingleSelect  "formObject.target.service", serviceNameMap, "size='8'"/></td>

		<td>Service extension</td>
		<td><@spring.formInput "formObject.target.serviceExtension", "size='12'"/></td>
	</tr>
	
	<tr>
		<td colspan="2"><@submit2 /></td>
	</tr>
	
	<@flowKey />
	</form>
</table>
</div>
<div id="summary">
<table>

	<tr class="smTitle">
	<td colspan="2">Summary</td>
	</tr>

	<@mark 0 >Unique identification</@mark>
	<@mark 1 >Registration details</@mark>
	<@mark 2 >Personal data</@mark>
	<@mark 3 >Password selection</@mark>
	<@mark 4 >Confirmation</@mark>
	
</table>
</div>

<#macro mark value=0 >
	<tr>
	<#if page &gt;=value>
		<td>+</td>
	<#else>
		<td>-</td>
	</#if>
		<td><#nested/></td>
	</tr>
</#macro>

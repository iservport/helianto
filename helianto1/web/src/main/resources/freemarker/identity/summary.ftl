<div id="summary">
<table>

	<@mark 0 >Unique identification</@mark>
	<@mark 1 >Registration details</@mark>
	<@mark 2 >Personal data</@mark>
	<@mark 3 >Personal data</@mark>
	<@mark 4 >Confirmation</@mark>
	<@mark 5 >Password</@mark>
	<@mark 6 >Conclusion</@mark>
	
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

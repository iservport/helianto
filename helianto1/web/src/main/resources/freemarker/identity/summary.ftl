<table>

	<@mark 0 >Unique identification</@mark>
	<@mark 1 >Details</@mark>
	<@mark 2 >Details</@mark>
	<@mark 3 >Personal data</@mark>
	<@mark 4 >Confirmation</@mark>
	<@mark 5 >Password</@mark>
	<@mark 6 >Conclusion</@mark>
	
</table>

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

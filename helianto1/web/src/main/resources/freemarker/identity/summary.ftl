<table style="width: 16em;">
	<@mark 0 >Unique identification</@mark>
	<@mark 1 >Registration details</@mark>
	<@mark 2 >Personal data</@mark>
	<@mark 3 >Password selection</@mark>
	<@mark 4 >Confirmation</@mark>
</table>

<#macro mark value=0 >
	<tr>
	<td>
		<#if page &gt;=value>
			[+]
		<#else>
			[-]
		</#if>
	</td>
	<td>
		<#if page=value+1>
			<@fl.anchor "previous"><#nested/></@fl.anchor>
		<#elseif page=value-1>
			<@fl.anchor "next"><#nested/></@fl.anchor>
		<#else>
			<#nested/>
		</#if>
	</td>
	</tr>
</#macro>

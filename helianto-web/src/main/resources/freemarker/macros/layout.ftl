<#--
 # Basic Helianto layout.
 #
 # @author Mauricio Fernandes de Castro
-->
<#macro layout >
<body>
<div id="layout">
<table>
	<tr>
	<#nested />
	</tr>
</table>
</div>
</body>
</#macro>

<#--
 # North panel.
 #
 # @author Mauricio Fernandes de Castro
-->
<#macro north >
	<td colspan="3" id="north"><#nested /></td>
	</tr>
	<tr>
</#macro>

<#--
 # South panel.
 #
 # @author Mauricio Fernandes de Castro
-->
<#macro south >
	</tr>
	<tr>
	<td colspan="3" id="south"><#nested /></td>
</#macro>

<#--
 # East panel.
 #
 # @author Mauricio Fernandes de Castro
-->
<#macro east >
	<!--
	 ! east panel
	 !-->
	<td id="east"><#nested /></td>
</#macro>

<#--
 # West panel.
 #
 # @author Mauricio Fernandes de Castro
-->
<#macro west >
	<!--
	 ! west panel
	 !-->
	<td id="west"><#nested /></td>
</#macro>


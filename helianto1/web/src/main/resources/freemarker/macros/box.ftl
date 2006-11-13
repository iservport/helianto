<#--
 # Convenience to create box tables.
 #
 # @author Mauricio Fernandes de Castro
-->
<#macro table title="">
	<table >

	<@row "ctit">${title}</@row>
	
	<#nested />
	
	</table>
</#macro>	

<#--
 # Convenience to create box lines.
 #
 # @author Mauricio Fernandes de Castro
-->
<#macro row classOrStyle="">
	<tr>
    <#if classOrStyle?index_of(":") == -1><#assign attr="class"><#else><#assign attr="style"></#if>
	<td ${attr}="${classOrStyle}" ><#nested /></td>
	</tr>
</#macro>	

<#--
 # Convenience to create box error lines.
 # Refactored from spring macro showErrors.
 #
 # @author Mauricio Fernandes de Castro
-->
<#macro showErrors errorsPath classOrStyle="error">
    <#list errorsPath as error>
    <@row classOrStyle >
    ${error}
    </@row>
    </#list>
</#macro>	

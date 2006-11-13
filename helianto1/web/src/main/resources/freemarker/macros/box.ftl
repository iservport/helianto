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
	<td <#if classOrStyle!="" >${attr}="${classOrStyle}" </#if> ><#nested /></td>
	</tr>
</#macro>	

<#--
 # Convenience to group box lines and show errors.
 #
 # @author Mauricio Fernandes de Castro
-->
<#macro group classOrStyle="">
	<#nested />
	<#if spring?exists && spring.status.errorMessages?has_content >
	<@row "bxError" >
    <@spring.showErrors "<br/>" />
    </@row>
    </#if>
</#macro>	


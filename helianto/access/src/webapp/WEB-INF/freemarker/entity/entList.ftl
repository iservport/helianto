<#import "main.ftl" as main />
<#import "spring.ftl" as spring />


<@main.main src="img/iserv-id.png" lastupdate="20050308">

	<#--
	 # Content line
	 #-->
	<tr style="height: 300px;">

    <#-- 
	 # left side 
	 #-->
	<td style="width: 180px;">
        ENTLIST
	</td>
    <#-- 
	 # right side 
	 #-->
	<td colspan="2">
	    LIST ENTITIES
	</td>

	</tr>
	<#--
	 # Bottom line
	 #-->
	<tr>
	
    <td>&#160;</td>
	<td>
	    <hr/>
	    <@main.submitForm "select" />
	    <@main.submitButton "select", "Selecionar"/>
	</td>
	
	</tr>
	
</@main.main>


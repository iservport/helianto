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
        NOENTITY
	</td>
    <#-- 
	 # right side 
	 #-->
	<td colspan="2">
	    WARNING
	</td>

	</tr>
	<#--
	 # Bottom line
	 #-->
	<tr>
	
    <td>&#160;</td>
	<td>
	    <hr/>
	    <@main.submitForm "create" />
	    <@main.submitButton "create", "Criar usuário"/>
	</td>
	
	</tr>
	
</@main.main>


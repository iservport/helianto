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
        WELCOME
	</td>
    <#-- 
	 # right side 
	 #-->
	<td colspan="2">
	    USER DATA: ${access.currentEntity.alias}
	    <br/>
	    <a href="credential.htm">Cadastro</a>
	</td>

	</tr>
	<#--
	 # Bottom line
	 #-->
	<tr>
	
    <td>&#160;</td>
	<td>
	    <hr/>
	    <@main.submitForm "continue" />
	    <@main.submitButton "continue", "Bem vindo"/>
	</td>
	
	</tr>
	
</@main.main>


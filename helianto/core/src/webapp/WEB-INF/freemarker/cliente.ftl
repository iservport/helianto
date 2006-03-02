<#import "main.ftl" as main />
<#import "spring.ftl" as spring />


<@main.main src="img/iserv-id.png" lastupdate="20050308">
<tr style="height: 300px;">
<form action="" method="POST">
    <#-- 
	 * right side 
	-->
	<td style="width: 220px;">
	A ordem de serviço ainda não foi vinculada a um cliente.
	</td>

    <#-- 
	 * left side 
	-->
	<td colspan="2">
			<table>
			  <tr>
    			<td> Ordem de Serviço: </td>
                <td>${command.ocorrencia.ordemDeServico.ordemNumero?string("#")}</td>
              </tr>
			  <tr>
    			<td> Cliente: </td>
                <td>
                  <@spring.formInput "command.ocorrencia.ordemDeServico.cliente", 'size="20"'/>
                  <@spring.showErrors "<br/>"/>
                </td>
              </tr>
            </table>
	</td>
</tr>
<tr>
    <td>&#160;</td>
    <td>
        <hr/>
        <@main.bprev 1/>
        <@main.bnext 3/>
    </td>
    <td style="text-align: right;">
        <hr/>
        <@main.bcanc/>
    </td>
</tr>
</form>
</@main.main>
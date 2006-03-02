<#import "main.ftl" as main />
<#import "spring.ftl" as spring />


<@main.main src="img/iserv-id.png" lastupdate="20050308">
<tr>
<form action="" method="POST">
    <#-- 
	 * right side 
	-->
	<td style="width: 220px;">
	Atualize...<br/>
	
	</td>

    <#-- 
	 * left side 
	-->
	<td colspan="2">
			<table>
			  <@main.formInput "command.ocorrencia.ordemDeServico.ordemNumero" />
			  <@main.formInput "command.ocorrencia.dataDaOcorrencia" />
			  <@main.formTextarea "command.ocorrencia.descricao", 'rows="5" style="width: 100%;"' />
			  <tr>
    			<td> Tipo: </td>
                <td>
                  <@main.formSingleSelect "command.ocorrencia.tipoDaOcorrencia", tipos/>
                </td>
              </tr>
			  <tr>
    			<td> Análise crítica: </td>
                <td>
                  <@main.formRadioButtons "command.ocorrencia.analiseCritica", analises, "&#160;"/>
                </td>
              </tr>
              <tr>
              <td><@spring.showErrors "<br/>"/></td>
              </tr>
            </table>
	</td>
</tr>
<tr>
    <td>&#160;</td>
    <td>
        <hr/>
        <@main.bprev 0/>
        <@main.bnext 2/>
    </td>
    <td style="text-align: right;">
        <hr/>
        <@main.bcanc/>
    </td>
</tr>
</form>
</@main.main>


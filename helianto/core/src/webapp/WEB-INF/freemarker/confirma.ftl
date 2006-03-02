<#import "main.ftl" as main />
<#import "spring.ftl" as spring />


<@main.main src="img/iserv-id.png" lastupdate="20050308">
<tr style="height: 300px;">
<form action="" method="POST">
    <#-- 
	 * right side 
	-->
	<td style="width: 220px;">
	    <table style="width: 100%;">
	        <tr>
	            <td>Ocorrência: </td>
	            <td>${command.ocorrencia.id?if_exists}</td>
	        </tr>
	        <tr>
	            <td>Data: </td>
	            <td>${command.ocorrencia.dataDaOcorrencia?date}</td>
	        </tr>
	        <tr>
	            <td>Tipo: </td>
	            <td>${command.ocorrencia.tipoDaOcorrencia}</td>
	        </tr>
	        <tr>
	            <td>Análise Crítica: </td>
	            <td>
	                <#switch command.ocorrencia.analiseCritica?string>
	                    <#case "-1">Em aberto<#break>
	                    <#case "0">Improcedente<#break>
	                    <#case "1">Procedente<#break>
	                </#switch>
	            </td>
	        </tr>
	    </table>
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
              <#if command.ocorrencia.ordemDeServico.cliente?default("")?string == "">
                  <@spring.formInput "command.ocorrencia.ordemDeServico.cliente", 'size="20"'/>
                  <@spring.showErrors "<br/>"/>
              <#else>
                  ${command.ocorrencia.ordemDeServico.cliente?default("")}
              </#if>
                </td>
              </tr>
			  <tr>
    			<td> Descrição: </td>
                <td>${command.ocorrencia.descricao}</td>
              </tr>
			  <tr>
    			<td> Resultado: </td>
                <td>
                  <@spring.formTextarea "command.ocorrencia.resultado", 'rows="5" style="width: 100%;"'/>
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
        <button type="submit" name="_target1" value="Anterior" style="margin: 0pt;">
            <table style="border-spacing: 0pt;">
                <tr>
                    <td><img src="img/bprev.gif"/></td>
                    <td>Anterior</td>
                </tr>
            </table>
        </button>
        <button type="submit" name="_finish" value="Concluir" style="margin: 0pt;">
            <table style="border-spacing: 0pt;">
                <tr>
                    <td><img src="img/bconf.gif"/></td>
                    <td>Concluir</td>
                   </tr>
               </table>
        </button>
	</td>
    <td style="text-align: right;">
        <hr/>
        <button type="submit" name="_cancel" value="Cancelar" style="margin: 0pt;">
            <table style="border-spacing: 0pt;">
                <tr>
                    <td><img src="img/bcanc.gif"/></td>
                    <td>Cancelar</td>
                   </tr>
               </table>
        </button>
    </td>
</tr>
</form>
</@main.main>
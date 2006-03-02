<#import "main.ftl" as main />
<#import "spring.ftl" as spring />


<@main.main src="img/iserv-id.png" lastupdate="20050308">

    <#-- 
	 * right side 
	-->
	<tr style="height: 300px;">
	<td style="width: 180px;">
        <form method="POST">
			<table>
			<tr>
			<td>In�cio: </td>
            <td>
              <@spring.formInput "command.start", 'size="10"'/>
            </td>
            </tr>
			<tr>
			<td>Fim: </td>
            <td>
              <@spring.formInput "command.end", 'size="10"' />
            </td>
            </tr>
			<tr>
			<td colspan="2">
	        <button type="submit" name="_target0" value="Atualizar lista" style="margin: 0pt;">
	            <table style="border-spacing: 0pt;">
	                <tr>
	                    <td><img src="img/bupd.gif"/></td>
	                    <td>Atualizar lista</td>
                    </tr>
                </table>
	        </button>
            </td>
            </tr>
			<tr>
			<td colspan="2">
              <@spring.showErrors "<br/>"/>
            </td>
            </tr>
            </table>
        </form>
	</td>

    <#-- 
	 * left side 
	-->
	<td>
	    <table cellspacing="0" style="border: 1pt solid;">
			<tr style="background-color: #cccccc;">
				<td> Item </td>
				<td> N�mero<br/> da OS </td>
				<td> Data da<br/>Ocorr�cia </td>
				<td colspan="2"> Descri��o </td>
				<td> Registro <br/>de A��o</td>
			</tr>
		<#list occurrences as item>
			<tr style="padding: 10px; <#if item_index % 2 == 1>background-color: #d1f1ff;</#if>">
				<td> <@link item.id /> </td>
				<td> <#if item.ordemDeServico?exists>
				    ${item.ordemDeServico.ordemNumero?string("#")} <br/>
				    ${item.ordemDeServico.cliente}</#if></td>
				<td> ${item.dataDaOcorrencia} </td>
				<td> ${item.descricao?if_exists} </td>
				<td style="text-align: right;"> <img src="img/tipo${item.tipoDaOcorrencia}.gif"/><img src="img/ac${item.analiseCritica}.gif"/> </td>
				<td> ${item.registroDeAcaoNr?if_exists} </td>
			</tr>
		</#list>
		</table>
	</td>
	</tr>
	<tr>
    <td>&#160;</td>
	<td>
	    <hr/>
	    <form method="POST">
            <input type="hidden" name="update" value="0"
	        <button type="submit" name="_target1" value="Nova Ocorr�ncia" style="margin: 0pt;">
	            <table style="border-spacing: 0pt;" >
	                <tr>
	                    <td><img src="img/bnew.gif"/></td>
	                    <td>Nova Ocorr�ncia</td>
                    </tr>
                </table>
	        </button>
	    </form>
	</td>
	</tr>
	
</@main.main>

<#macro link href>
   <form method="POST">
   <input type="hidden" name="update" value="${href}"
   <input border="0" type="image" src="img/go.gif" name="_target1" value=">"/>
   ${href}
   </form>
</#macro>


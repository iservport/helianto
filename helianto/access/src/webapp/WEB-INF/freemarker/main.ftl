<#--
 * A macro library to use with FreeMarker. It also has a login form.
 * @author mauricio@iservport.com


<@main src="img/iserv-id.png" lastupdate="20050308">
  <form name="loginForm" method="POST" action="j_acegi_security_check" >
  <tr>
    <td style="width: 200px; height: 320px;">
      <table>
        <tr>
          <td>[...]<img src="img/arrow.gif"/></td>
        </tr>
      </table>
    </td>
    <td style="background-image: url(img/arrow-bg.gif);">
      <table style="width: 200px;">
        <tr>
          <td>Usuário: </td>
          <td><input type="text" name="j_username" size="16"/></td>
        </tr>
        <tr>
          <td>Senha: </td>
          <td><input type="password" name="j_password" size="10"/></td>
        </tr>
        <tr colspan="2">
          <td>
            <input type="submit" alignment="center" value="Entrar" class="btn">
          </td>
        </tr>
      </table>
    </td>
  </tr>
  </form>
</@main>
-->

<#--
 # A macro to render html head element with nested
 # custom javascript.
 #-->
<#macro head>
	<head>
		<@compress single_line=true>
			<title>
				<@spring.message "title"/>
			</title>
		</@compress>
		<meta content="text/html; charset=iso-8859-1" 
			http-equiv="Content-Type"/>
		<link rel="stylesheet" href="iservport.css"/>
		<script type="text/javascript">
			<#nested/>
		</script>
	</head>
</#macro>

<#--
 # A macro to render html body element with nested
 # custom html content.
 #
 # @param src An image to display as logo.
 # @param lastupdate A date to be displayed in footer.
 #-->
<#macro body src="iserv.htm" lastupdate="">
	<body style="background-color:#ffffff; text-align: center;">
		<div align="center">
		    <@menu src="${src}"/>
			<table id="frametable" 
				   style="background-color: #f0f0f0;">
				<#nested/>
			</table>
			<@footer lastupdate="${lastupdate}"/>
		</div>
	</body>
</#macro>

<#--
 # Basic html document formatting macro, using head 
 # and body with nested content.
 #
 # @param src An image to display as logo.
 # @param lastupdate A date to be displayed in footer.
 #-->
<#macro main src="iserv.htm" lastupdate="">

<#import "spring.ftl" as spring />
<html>

	<@head/>
	<@body src, lastupdate >
		<#nested>
	</@body>
	
</html>

</#macro>

<#--
 * Standard menu.
-->
<#macro menu src="iserv.htm">
<table id="menutable"
	   style="background-color: #f0f0f0;">
	<tbody>
		<tr valign="middle">
			<td align="left" class="menu" rowspan="2" height="80">
         		<#if org?exists>
    				<img border="0" src="logo.htm?orgAlias=${org.uniqueAlias}"/>
				<#else>
		    		<img border="0" src="${src}"/>
				</#if>
			</td>
			<td class="menu"></td>
		</tr>
		<tr>
			<td valign="bottom" class="menu"> <a target="_new" 
				href="http://www.iservport.com/about.htm" 
				class="menu"><@spring.message "menu.about"/></a> | <a 
				target="_new" 
				href="http://www.iservport.com/policies/privacy.htm" 
				class="menu"><@spring.message "menu.privacy"/></a> 
				<br/> <a href="mailto:info@iservport.com" 
				class="menu"><@spring.message "menu.contact"/></a> 
				</td>
		</tr>
		<#if org?exists>
		<tr>
			<td colspan="2" 
				style="border-top: 2px solid #ffffff; background-color: #cccccc; font-size: large;">
				<span id="orgname">${org.orgName}</span>
			</td>
		</tr>
		</#if>
	</tbody>
</table>
</#macro>

<#--
 * Basic formatting macro.
 -->
<#macro pane>
    <td style="width: 220px;">
		<#nested/>
	</td>
</#macro>

<#--
 * Basic formatting macro.
 -->
<#macro footer lastupdate="">
<table align="center" border="0" width="780" cellpadding="2" 
	cellspacing="4">
	<tbody>
		<tr>
			<td style="background-color: #0072bc; color: white;" 
				colspan="2">
				<b>
					<@spring.message "footer.reserved"/>
				</b>
			</td>
		</tr>
		<tr>
			<td><@spring.message "footer.warn1"/> <a 
				style="text-decoration: underline;" 
				href="mailto:info@iservport.com?subject=Autorizacao para reproduzir conteudo web"> 
				<@spring.message "footer.prior"/></a>.<br/> </td>
			<td align="right"> 
				<#if lastupdate?exists && lastupdate?has_content>
				<@spring.message "footer.lastupdate"/><br/>
				 ${lastupdate}</#if>
			</td>
		</tr>
	</tbody>
</table>
</#macro>

<#--
 * Basic module 2 column.
 -->
<#macro moduletype bg="#cccccc" keybase="" typelist="">
<table id="${keybase}table"style="width: 100%; border: 1pt solid #cccccc;">
	<thead>
		<tr style="background-color: ${bg};">
			<td colspan="2">
				<@spring.message "${keybase}.caption"/>
			</td>
		</tr>
	</thead>
	<tbody>
		<#list typelist?split(",") as digit>
			<tr>
				<td>${digit}</td>
				<td><@spring.message "${keybase}${digit?trim}"/></td>
			</tr>
		</#list>
	</tbody>
</table>
<br style="font-size: 6px;"/>
</#macro>

<#macro bnew bname page="1">
    <button type="submit" name="_target${page}" value="${bname}" style="margin: 0pt;">
        <table style="border-spacing: 0pt;" >
            <tr>
                <td><img src="img/bnew.gif"/></td>
                <td>${bname}</td>
            </tr>
        </table>
    </button>
</#macro>

<#macro bprev page value="Anterior">
    <button type="submit" name="_target${page}" value="${value}" style="margin: 0pt;">
        <table style="border-spacing: 0pt;">
            <tr>
                <td><img src="img/bprev.gif"/></td>
                <td>${value}</td>
            </tr>
        </table>
    </button>
</#macro>

<#macro bnext page value="Próxima">
    <button type="submit" name="_target${page}" value="${value}" style="margin: 0pt;">
        <table style="border-spacing: 0pt;">
            <tr>
                <td>${value}</td>
                <td><img src="img/arrowEW.png"/></td>
            </tr>
        </table>
    </button>
</#macro>

<#macro bcanc>
    <button type="submit" name="_cancel" value="Cancelar" style="margin: 0pt;">
        <table style="border-spacing: 0pt;">
            <tr>
                <td><img src="img/bcanc.gif"/></td>
                <td>Cancelar</td>
            </tr>
        </table>
    </button>
</#macro>

<#macro bfin value="Concluir">
    <button type="submit" name="_finish" value="${value}" style="margin: 0pt;">
        <table style="border-spacing: 0pt;">
            <tr>
                <td><img src="img/bconf.gif"/></td>
                <td>${value}</td>
            </tr>
        </table>
    </button>
</#macro>

<#macro formInput object attrib='size="10"'>
	<tr>
    	<td><@spring.message object/>: </td>
        <td><@spring.formInput object?string, attrib/></td>
	</tr>
</#macro>

<#macro formInputDate object attrib='size="20"'>
	<@spring.bind object />
	<tr>
    	<td><@spring.message object/>: </td>
        <td><input type="text" name="${spring.status.expression}" value="${spring.stringStatusValue?date}" ${attrib}</td>
	</tr>
</#macro>

<#macro showInput object>
	<tr>
    	<td><@spring.message object/>: </td>
        <td>${springMacroRequestContext.getBindStatus(object).getValue()}</td>
	</tr>
</#macro>

<#macro formTextarea object attrib='rows="5" style="width: 100%;"'>
	<tr>
    	<td><@spring.message object/>: </td>
        <td><@spring.formTextarea object, attrib/></td>
	</tr>
</#macro>

<#macro alphaList >
	    <#assign alphaList1 = [ "A", "B", "C", "D", "E", "F", "G",
	        "H", "I", "J", "K", "L", "M" ]>
	    <#assign alphaList2 = [ "N", "O", "P", "Q", "R", "S", "T", 
	        "U", "V", "W", "X", "Y", "Z" ]>
	    <table cellspacing="0" style="border: 1pt solid;">
			<tr>
				<#list alphaList1 as item>
					<@searchString item />
				</#list>
			</tr>
			<tr>
				<#list alphaList2 as item>
					<@searchString item />
				</#list>
			</tr>
		</table>
</#macro>

<#macro searchString href>
	<td>
    <form method="POST">
		<input type="hidden" name="_flowExecutionId" value="${flowExecutionId}">
	    <input type="hidden" name="_currentStateId" value="${currentStateId}">
    	<input type="hidden" name="_eventId" value="filter">
    	<input type="hidden" name="searchString" value="${href}"/>
    	<input border="0" type="submit" name="_refresh" value="${href}"/>
    </form>
    </td>
</#macro>

<#--
 # A macro to render standard flow parameters.
 #-->
<#macro submitParams eventId="submit">
	<input type="hidden" name="_flowExecutionId" value="${flowExecutionId}">
    <input type="hidden" name="_currentStateId" value="${currentStateId}">
    <input type="hidden" name="_eventId" value="${eventId}">
</#macro>

<#--
 # A macro to render a standard form.
 #-->
<#macro submitForm eventId="submit">
	<form name="${eventId}Form" method="POST">
		<@submitParams eventId />
    	<#nested/>
    </form>
</#macro>

<#--
 # A macro to render a button attached to a javascript function. 
 #-->
<#macro submitButton eventId="submit" value="Concluir" img="img/bconf.gif">
    <button 
        type="submit" 
        name="_${eventId}" 
        value="${value}" 
        onclick="javascript:document.${eventId}Form.submit()"
        style="margin: 0pt;">
        <table style="border-spacing: 0pt;">
            <tr>
                <td><img src="${img}"/></td>
                <td>${value}</td>
            </tr>
        </table>
    </button>
</#macro>


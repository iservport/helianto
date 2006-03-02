<#--
 * A macro library to use with FreeMarker. It also has a login form.
 * @author mauricio@iservport.com
-->

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

<#--
 * Basic formatting macro.
-->
<#macro main src="iserv.htm" lastupdate="">
	<#import "spring.ftl" as spring />

<html>

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
		function formSubmit() {
		  document.forms.loginForm.submit()
		}
		</script>
</head>
	</head>	
	
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

<#--
 * formSingleSelect
 *
 * OVERRIDES macro with the same name in spring ftl
 * SEE TOPIC 1341 in Spring Forum
 *
 * Show a selectbox (dropdown) input element allowing a single value to be chosen
 * from a list of options.
 *
 * @param path the name of the field to bind to
 * @param options a map (value=label) of all the available options
 * @param attributes any additional attributes for the element (such as class
 *        or CSS styles or size
-->
<#macro formSingleSelect path options attributes="">
    <@spring.bind path/>
    <select name="${spring.status.expression}" ${attributes}>
        <#list options?keys as optionKey>
            <option 
                <#if spring.status.value?default("")?string == optionKey?string>selected="true"</#if>
                value="${optionKey}"
            >${options[optionKey]}</option>
        </#list>
     </select>
</#macro>

<#--
 * formRadioButtons
 *
 * OVERRIDES macro with the same name in spring ftl
 * SEE TOPIC 1341 in Spring Forum
 *
 * Show radio buttons.
 *
 * @param path the name of the field to bind to
 * @param options a map (value=label) of all the available options
 * @param separator the html tag or other character list that should be used to
 *        separate each option.  Typically '&nbsp;' or '<br>'
 * @param attributes any additional attributes for the element (such as class
 *        or CSS styles or size
-->
<#macro formRadioButtons path options separator attributes="">
	<@spring.bind path/>
	<#list options?keys as optionKey>
	    <input type="radio" name="${spring.status.expression}" value="${optionKey}"
		    <#if spring.status.value?default("")?string == optionKey?string>checked="checked"</#if> ${attributes}
	    <@spring.closeTag/>
	    ${options[optionKey]}${separator}
	</#list>
</#macro>

<#--
 * formCheckboxes
 *
 * OVERRIDES macro with the same name in spring ftl
 * SEE TOPIC 1341 in Spring Forum
 *
 * Show checkboxes.
 *
 * @param path the name of the field to bind to
 * @param options a map (value=label) of all the available options
 * @param separator the html tag or other character list that should be used to
 *        separate each option.  Typically '&nbsp;' or '<br>'
 * @param attributes any additional attributes for the element (such as class
 *        or CSS styles or size
-->
<#macro formCheckboxes path options separator attributes="">
	<@spring.bind path/>
	<#list options?keys as optionKey>
	    <#assign isSelected = contains(spring.status.value?default([""]), optionKey)>
	    <input type="checkbox" name="${spring.status.expression}" value="${optionKey}"
		<#if isSelected>checked="checked"</#if> ${attributes}
	    <@spring.closeTag/>
	    ${options[optionKey]}${separator}
	</#list>
</#macro>

<#--
 * listContains
 *
 * Macro to return true if the list contains the scalar, false if not.
 * Surprisingly not a FreeMarker builtin. This function is used internally but
 * can be accessed by user code if required.
 *
 * @param list the list to search for the item
 * @param item the item to search for in the list
 * @return true if item is found in the list, false otherwise.
-->
<#function contains list item>
	<#list list as nextInList>
	<#if nextInList?string == item?string><#return true></#if>
	</#list>
	<#return false>
</#function>

<#macro bprev page>
    <button type="submit" name="_target${page}" value="Anterior" style="margin: 0pt;">
        <table style="border-spacing: 0pt;">
            <tr>
                <td><img src="img/bprev.gif"/></td>
                <td>Anterior</td>
            </tr>
        </table>
    </button>
</#macro>

<#macro bnext page>
    <button type="submit" name="_target${page}" value="Próxima" style="margin: 0pt;">
        <table style="border-spacing: 0pt;">
            <tr>
                <td>Próxima</td>
                <td><img src="img/bnext.gif"/></td>
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

<#macro formInput object attrib='size="10"'>
	<tr>
    	<td><@spring.message object/>: </td>
        <td><@spring.formInput object, attrib/></td>
	</tr>
</#macro>

<#macro formTextarea object attrib='size="10"'>
	<tr>
    	<td><@spring.message object/>: </td>
        <td><@spring.formTextarea object, attrib/></td>
	</tr>
</#macro>


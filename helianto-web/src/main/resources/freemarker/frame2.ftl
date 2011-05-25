<#--
 # Basic frame
 #
 # @author Mauricio Fernandes de Castro
 #-->
<#import "/spring.ftl" as spring />
<#include "${import!\"/import\"}.ftl"/>
<#if assign?exists ><#include "${basePath!\"\"}${assign}.ftl"/></#if>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<#include "${head!\"/head\"}.ftl"/>
		<#include "${script!\"/script\"}.ftl"/>
	</head>
	<body class="tundra">
		<#include "body.ftl" />
	</body>
</html>

<#--
 #
 # Macro section.
 #
-->

<#--
 # This frame have a couple of macros to help manage Spring Web Flow 
 # conversation state.
 #
 # @author Mauricio Fernandes de Castro
-->
<#macro flowKey>
<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}"/>
</#macro>

<#macro submit2 event="storeTarget", caption="Store" >
<input type="submit" name="_eventId_${event}" value="${caption}" class="btn" />
</#macro>

<#--
 # Macro to create a selection link.
 #
 # @param event - the name of the event.
 # @param param - parameters to be appended starting with &amp;
 # @param title - to be shown as a hint.
 #
  -->
<#macro anchor event="createTarget", param="", title="">
<a <#if title!="" >title="${title}"</#if>href="${flowExecutionUrl}&_eventId=${event}${param}"><#nested/></a>
</#macro>

<#--
 # Macro to create a selection link, or a return link if filterOption 
 # is enabled.
 #
 # @deprecated, prefer select (below)
  -->
<#macro select2 targetIndex="0", event="selectTarget", param="", title=""><a href="${flowExecutionUrl}&_eventId=cancel">Cancel</a>
<td>
<#if filterOption?if_exists='returnOption' >
[<a <#if title!="" >title="${title}"</#if> 
href="${flowExecutionUrl}&_eventId=returnTarget&target_index=${targetIndex}${param}"><#nested/></a>]
<#else>
<a <#if title!="" >title="${title}"</#if> 
href="${flowExecutionUrl}&_eventId=${event}&target_index=${targetIndex}${param}"><#nested/></a>
</#if>
</td>
</#macro>

<#--
 # Macro to create a selection link, or a return link if filterOption 
 # is enabled.
 #
 # The macro will automatically create a transition using the model variable 
 # ${targetName}, either 'select${name}' or 'return${name}', 
 # followed by '&index=${targetIndex}${param}' passed as macro parameters.
 # The spring webflow ${flowExecutionKey} will be appended, if exists.
 # 
 # The return option also creates extra [] around the link to distinguish it
 # from selection.
 #
 # @param targetIndex - the index to select, defaults to zero,
 # @param name - name to be prepended with 'select' or 'return' to create the eventId, 
 #               defaults to the current targetName from the model,
 # @param indexName - the name of the first parameter, defaults to 'index',
 # @param param - any aditional parameter, must start with '&',
 # @param title - to be shown as a hint.
 # @param role - the required role to allow selection.
  -->
<#macro select targetIndex="0", name="${targetName}", indexName="index", param="", title="", role="ROLE_USER">
<td>
<#if filterOption?if_exists='returnOption' >
	[<a <#if title!="" >title="${title}"</#if> 
	href="${flowExecutionUrl}&_eventId=return${name?cap_first}&${indexName}=${targetIndex}${param}"><#nested/></a>]
<#else>
		<a <#if title!="" >title="${title}"</#if> 
		href="${flowExecutionUrl}&_eventId=select${name?cap_first}&${indexName}=${targetIndex}${param}"><#nested/></a>
		<#--
	<#if isInRole(role) >
	<#else>
		<#nested/>
	</#if>
	-->
</#if>
</td>
</#macro>

<#macro perr classOrStyle="">
	<#if spring?exists && spring.status.errorMessages?has_content >
		<p><@spring.showErrors " " /></p>
    </#if>
</#macro>	

<#--
 # Macro to create a selection link to a PageModel , or a return link if filterOption 
 # is enabled.
 #
 # The macro will automatically create a transition using the model variable 
 # ${targetName}, either 'select${name}' or 'return${name}', 
 # followed by '&pages['name'].index=${targetIndex}${param}' passed as macro parameters.
 # The spring webflow ${flowExecutionUrl} is included.
 # 
 # The return option also creates extra [] around the link to distinguish it
 # from selection.
 #
 # @param targetIndex - the index to select, defaults to zero,
 # @param name - name to be prepended with 'select' or 'return' to create the eventId, 
 #               defaults to the current targetName from the model,
 # @param indexName - the name of the first parameter, defaults to 'index',
 # @param param - any aditional parameter, must start with '&',
 # @param title - to be shown as a hint.
 # @param role - the required role to allow selection.
  -->
<#macro selectModel targetIndex="0", name="${targetName}", indexName="index", param="", title="", role="ROLE_USER">
<#if filterOption?if_exists='returnOption' >
	[<a <#if title!="" >title="${title}"</#if> 
	href="${flowExecutionUrl}&_eventId=return${name?cap_first}&pages['${name?uncap_first}'].${indexName}=${targetIndex}${param}#${name}${targetIndex}"><#nested/></a>]
<#else>
		<a <#if title!="" >title="${title}"</#if> 
		href="${flowExecutionUrl}&_eventId=select${name?cap_first}&pages['${name?uncap_first}'].${indexName}=${targetIndex}${param}#${name}${targetIndex}"><#nested/></a>
		<#--
	<#if currentUser?exists && currentUser.authorities?seq_contains(role) >
	<#else>
		<#nested/>
	</#if>
	-->
</#if>
</#macro>

<#--
 # Macro to create a selection link (enclosed by the tag <tr>) to a PageModel , 
 # or a return link if filterOption is enabled.
 #
 # The macro will automatically create a transition using the model variable 
 # ${targetName}, either 'select${name}' or 'return${name}', 
 # followed by '&pages['name'].index=${targetIndex}${param}' passed as macro parameters.
 # The spring webflow ${flowExecutionUrl} is included.
 # 
 # The return option also creates extra [] around the link to distinguish it
 # from selection.
 #
 # @param targetIndex - the index to select, defaults to zero,
 # @param name - name to be prepended with 'select' or 'return' to create the eventId, 
 #               defaults to the current targetName from the model,
 # @param indexName - the name of the first parameter, defaults to 'index',
 # @param param - any aditional parameter, must start with '&',
 # @param title - to be shown as a hint.
 # @param role - the required role to allow selection.
  -->
<#macro selectModelTr targetIndex="0", name="${targetName}", indexName="index", param="", title="", role="ROLE_USER_ALL">
<td>
<@selectModel targetIndex, name, indexName, param, title, role ><#nested/></@selectModel>
</td>
</#macro>

<#--
 # Function to help with security roles.
 #
 # @param roles - a comma separated list of roles.
 # @return true if any role of the roles is assigned to the current user
-->
<#function isInRole roles="ROLE_USER">
	<#if !currentUser?exists>
		<#return false />
	<#else>
		<#assign isInRole = false />
		<#list roles?split(",") as r >
			<#if currentUser.authorities?seq_contains(r?trim) >
				<#assign isInRole = true />
			</#if>
		</#list>
		<#if isInRole >
			<#return true />
		</#if>
	</#if>
</#function>

<#--
 # Macro to help with security roles.
 #
 # When wrapping an anchor, causes it to be displayed only if the user has
 # the appropriate privileges.
 # 
 # @param roles - a comma separated list of roles.
-->
<#macro secure roles="ROLE_USER">
	<#if !currentUser?exists>
		<#nested />
	<#else>
		<#assign isInRole = false />
		<#list roles?split(",") as r >
			<#if currentUser.authorities?seq_contains(r?trim) >
				<#assign isInRole = true />
			</#if>
		</#list>
		<#if isInRole >
			<#nested />
		</#if>
	</#if>
</#macro>

<#--
 # Macro to help with security roles.
 #
 # When wrapping an anchor, causes it to be displayed only if the user has NOT
 # the appropriate privileges.
 # 
 # @param roles - a comma separated list of roles.
-->
<#macro unsecure roles="ROLE_USER">
	<#if currentUser?exists>
		<#assign isInRole = false />
		<#list roles?split(",") as r >
			<#if currentUser.authorities?seq_contains(r?trim) >
				<#assign isInRole = true />
			</#if>
		</#list>
		<#if !isInRole >
			<#nested />
		</#if>
	</#if>
</#macro>

<#--
 # Macro to trigger object creation using the index = -1.
-->
<#macro create name="${targetName}", title="Create ${name?cap_first}">
<a title="${title}" href="${flowExecutionUrl}&_eventId=create${name?cap_first}&index=-1"><#nested/></a>
</#macro>

<#macro remove caption="Remove", target="target", action="home.htm">
	<table id="remove">
	<thead>
	<tr>
		<td>${caption}</td>
	</tr>
	</thead>
	<tbody>
	<tr>
		<td>
			<form action="${action}" method="POST">
				<input type="checkbox" name="${target}RemovalConfirmation" value="R"/>
				<input type="hidden" name="task" value="${formObject.target.id?c}"/>
				<input type="submit" name="_eventId_removeTarget" value="${caption}" class="btn" />
				<@fl.flowKey/>
			</form>
		</td>
	</tr>
	</tbody>
	</table>
</#macro>

<#--
 # Convenience to group box lines and show errors as table cells.
 # Modifies cgroup to accept args like colspan, etc
 #
 # @author Mauricio Fernandes de Castro
-->
<#macro input label="", attr="", separator=":<br />", classOrStyle="">
	<td ${attr}>
	<b>${label}</b>${separator}
	<#nested />
	<#if spring?exists && spring.status.errorMessages?has_content >
	<div class="bxError" >
    <@spring.showErrors "<br/>" />
    </div>
    </#if>
    </td>
</#macro>	

<#macro formDate path >
     <@spring.bind path/>
	    <input type="text" id="${spring.status.expression}" 
	        name="${spring.status.expression}" 
	        value="${spring.stringStatusValue}" size="8"
	        style="width:7em; padding: 1px;"
    <@spring.closeTag/>
	<script type="text/javascript">
        Spring.addDecoration(
        new Spring.ElementDecoration({
        	elementId : '${spring.status.expression}', 
        	widgetType : 'dijit.form.DateTextBox', 
        	widgetAttrs : {
        		required: false, 
        		value : dojo.date.locale.parse('${spring.stringStatusValue}', {selector : 'date', datePattern : 'dd/MM/yy'}), 
        		displayedValue : '${spring.stringStatusValue}',
        		serialize : function(d, options){
					return dojo.date.locale.format(d, {selector : 'date', datePattern : 'MM/dd/yy'});
				},
				lang : 'pt-br'
        	}
        })); 
    </script>
</#macro>

<#macro formDateTime path >
     <@spring.bind path/>
	    <input type="text" id="${spring.status.expression}" 
	        name="${spring.status.expression}" 
	        value="${spring.stringStatusValue?date?string('dd/MM/yy')}" size="8"
	        style=" width:7em; padding: 1px;"
    <@spring.closeTag/>
	<script type="text/javascript">
        Spring.addDecoration(
        new Spring.ElementDecoration({
        	elementId : '${spring.status.expression}', 
        	widgetType : 'dijit.form.DateTextBox', 
        	widgetAttrs : {
        		required: false, 
        		value : dojo.date.locale.parse('${spring.stringStatusValue?date?string('dd/MM/yy')}', {selector : 'date', datePattern : 'dd/MM/yy'}), 
        		displayedValue : '${spring.stringStatusValue?date?string('dd/MM/yy')}',
        		serialize : function(d, options){
					return dojo.date.locale.format(d, {selector : 'date', datePattern : 'MM/dd/yy'});
				},
				constraints : {fullYear:false},
				lang : 'pt-br'
        	}
        })); 
    </script>
</#macro>

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
<div id="layout">
<body>
<#if forward?exists ><#include "${bodyscript!\"/empty\"}.ftl"/></#if>
<#-- a top navigation line -->
<div id="entityLine">
<p>
        <#if search?exists ><#include "${search}.ftl"/></#if>
        <#include "${home!\"/home\"}.ftl"/>
        <#if navigate?exists ><#include "${navigate}.ftl"/></#if>
    	<b>${title!"Selection"}</b>
</p>
</div>
<div id="navigationLine">
<p>
		<#if forward?exists ><#include "${basePath!\"\"}${forward}.ftl"/></#if>
</p>
</div>
<div id="headLine">
		<#if announcement?exists ><#include "${basePath!\"\"}${announcement}.ftl"/></#if>
</div>
<table name="bodyTable">
<#-- actual content starts here -->
<tr id="content">
<td id="sidebar">
        <#if sidenav?exists ><#include "${basePath!\"\"}${sidenav}.ftl"/></#if>
        <#if sidebar?exists ><#include "${basePath!\"\"}${sidebar}.ftl"/></#if>
        <#if siderel?exists ><#include "${basePath!\"\"}${siderel}.ftl"/></#if>
</td>
<td id="main">
		<div id="mainnav"><#if mainnav?exists ><#include "${basePath!\"\"}${mainnav}.ftl"/></#if></div>
		<div id="mainbar"><#if mainbar?exists ><#include "${basePath!\"\"}${mainbar}.ftl"/></#if></div>
		<div id="tempnav"><#if tempnav?exists ><#include "${basePath!\"\"}${tempnav}.ftl"/></#if></div>
		<#include "${basePath!\"\"}${template!\"/empty\"}.ftl"/>
</td>
<td id="info">
		<#if info?exists ><#include "${basePath!\"\"}${info}.ftl"/></#if>
</td>
</tr>
<tr >
<td id="footer" colspan="3">
        <#if footer?exists ><#include "${basePath!\"\"}${footer}.ftl"/></#if>
</td>
</tr>
</table> <!-- end of bodyTable -->
</body>
</div>
</html>

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
 # ${targetName}, either 'select${targetName}' or 'return${targetName}', 
 # followed by '&index=${targetIndex}${param}' passed as macro parameters.
 # The spring webflow ${flowExecutionKey} will be appended, if exists.
 # 
 # The return option also creates extra [] around the link to distinguish it
 # from selection.
  -->
<#macro select targetIndex="0", param="", title="">
<td>
<#if filterOption?if_exists='returnOption' >
[<a <#if title!="" >title="${title}"</#if> 
href="${flowExecutionUrl}&_eventId=return${targetName?cap_first}&index=${targetIndex}${param}"><#nested/></a>]
<#else>
<a <#if title!="" >title="${title}"</#if> 
href="${flowExecutionUrl}&_eventId=select${targetName?cap_first}&index=${targetIndex}${param}"><#nested/></a>
</#if>
</td>
</#macro>

<#macro perr classOrStyle="">
	<#if spring?exists && spring.status.errorMessages?has_content >
		<p><@spring.showErrors " " /></p>
    </#if>
</#macro>	

<#--
 # Macro to help with security roles.
 #
 # When wrapping an anchor, causes it to be displayed only if the user has
 # the appropriate privileges.
-->
<#macro secure role="ROLE_USER_ALL">
	<#if !secureUser?exists>
		<#nested/>
	<#elseif secureUser.authorities?seq_contains(role) >
		<#nested/>
	</#if>
</#macro>

<#--
 # Macro to trigger object creation using the index = -1.
-->
<#macro create title="Create ${targetName?cap_first}">
<a title="${title}" href="${flowExecutionUrl}&_eventId=create${targetName?cap_first}&index=-1"><#nested/></a>
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
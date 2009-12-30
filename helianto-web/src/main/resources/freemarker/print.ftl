<#--
 # Print frame
 #
 # @author Mauricio Fernandes de Castro
 #-->
<#import "/spring.ftl" as spring />
<#include "${import!\"/import\"}.ftl"/>
<#include "${assign!\"/empty\"}.ftl"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<#include "${head!\"/head\"}.ftl"/>
<#include "${script!\"/script\"}.ftl"/>
</head>
<div id="layout">
<body>
<#-- a top navigation line -->
<div id="entityLine">
<p>
        <#include "${search!\"/empty\"}.ftl"/>
        <#include "${home!\"/home\"}.ftl"/>
        <#include "${navigate!\"/empty\"}.ftl"/>
    	<b>${title!"Selection"}</b>
</p>
</div>
<#-- navigationLine supressed -->
<div id="headLine">
		<#include "${announcement!\"/announcement\"}.ftl"/>
</div>
<table name="bodyTable">
<#-- actual content starts here -->
<tr id="content">
<#-- sidebar supressed -->
<td id="main">
		<#-- mainnav supressed -->
		<div id="mainbar"><#include "${mainbar!\"/empty\"}.ftl"/></div>
		<div id="tempnav"><#include "${tempnav!\"/empty\"}.ftl"/></div>
		<#include "${template!\"/empty\"}.ftl"/>
</td>
<td id="info">
		<#include "${info!\"/empty\"}.ftl"/>
</td>
</tr>
<tr >
<td id="footer" colspan="3">
        <#include "${footer!\"/empty\"}.ftl"/>
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
<#-- submit supressed -->
</#macro>

<#macro anchor event="createTarget", param="", title="">
<#-- anchor supressed -->
<b><#nested/></b>
</#macro>

<#macro select targetIndex="0", event="selectTarget", param="", title="">
<td>
<#-- anchor supressed -->
<b><#nested/></b>
</td>
</#macro>

<#macro perr classOrStyle="">
	<#if spring?exists && spring.status.errorMessages?has_content >
		<p><@spring.showErrors " " /></p>
    </#if>
</#macro>	

<#macro secure role="ROLE_USER_ALL">
	<#if !secureUser?exists>
		<#nested/>
	<#elseif secureUser.authorities?seq_contains(role) >
		<#nested/>
	</#if>
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
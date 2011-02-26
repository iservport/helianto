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

<#--
 # Basic frame body.
 #
 # @author Mauricio Fernandes de Castro
 #-->
<div class="container">
	<#if bodyscript?exists ><#include "${bodyscript!\"/empty\"}.ftl"/></#if>
	<div class="topContainer">
		<div class="menuLine">
		<#include "${menu!\"/empty\"}.ftl"/>
		</div>
		<div class="navigationLine">
				<#if forward?exists ><#include "${basePath!\"\"}${forward}.ftl"/></#if>
		</div>
		<div id="headLine">
				<#if announcement?exists ><#include "${basePath!\"\"}${announcement}.ftl"/></#if>
		</div>
	</div><!-- End of topContainer -->
	<table name="bodyTable">
		<#-- actual content starts here -->
		<tr id="content">
		<td id="sidebar">
		        <#if sidenav?exists ><#include "${basePath!\"\"}${sidenav}.ftl"/></#if>
		        <#if sidebar?exists ><#include "${basePath!\"\"}${sidebar}.ftl"/></#if>
		        <#if siderel?exists ><#include "${basePath!\"\"}${siderel}.ftl"/></#if>
		</td>
		<td id="main">
				<#include "${basePath!\"\"}${template!\"/empty\"}.ftl"/>
		</td>
		</tr>
		<tr >
		<td id="footer" colspan="3">
		        <#if footer?exists ><#include "${basePath!\"\"}${footer}.ftl"/></#if>
		</td>
		</tr>
	</table> <!-- end of bodyTable -->
</div><!-- End of container -->
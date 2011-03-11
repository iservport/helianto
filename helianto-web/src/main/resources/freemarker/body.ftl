<#--
 # Basic frame body.
 #
 # @author Mauricio Fernandes de Castro
 #-->
<div class="container">
	<#if bodyscript?exists ><#include "${bodyscript!\"/empty\"}.ftl"/></#if>
	<#-- top container -->
	<div class="topContainer">
		<div class="menuLine">
				<#include "${menu!\"/menu\"}.ftl"/>
		</div>
		<div class="navigationLine">
				<#if forward?exists ><#include "${basePath!\"\"}${forward}.ftl"/></#if>
		</div>
		<div class="headLine">
				<#if announcement?exists ><#include "${basePath!\"\"}${announcement}.ftl"/></#if>
		</div>
	</div><!-- End of topContainer -->
	<table name="bodyTable">
	<#if group?exists >
	<#-- group line -->
	<tr class="groupLine">
		<td id="sidegrp">
		        <#if sidegrp?exists ><#include "${basePath!\"\"}${sidegrp}.ftl"/></#if>
		</td>
		<td id="grp">
				<#include "${basePath!\"\"}${group}.ftl"/>
		</td>
	</tr>
	</#if>
	<#-- content line -->
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
	<#-- footer line -->
	<tr >
		<td id="footer" colspan="3">
		        <#if footer?exists ><#include "${basePath!\"\"}${footer}.ftl"/></#if>
		</td>
	</tr>
	</table> <!-- end of bodyTable -->
</div><!-- End of container -->

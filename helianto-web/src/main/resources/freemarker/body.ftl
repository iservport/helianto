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
	<#-- group line -->
	<tr >
		<td id="sidebar" rowspan="2">
		        <#if sidenav?exists ><#include "${basePath!\"\"}${sidenav}.ftl"/></#if>
		        <#if sidebar?exists ><#include "${basePath!\"\"}${sidebar}.ftl"/></#if>
		        <#if siderel?exists ><#include "${basePath!\"\"}${siderel}.ftl"/></#if>
		</td>
	<#-- start of conditional group block -->
		<#if group?exists >
		<td id="grp">
				<#include "${basePath!\"\"}${group}.ftl"/>
		</td>
	</tr>
	<#-- content line -->
	<tr >
		</#if>
	<#-- end of conditional group block -->
		<td id="main" style="vertical-align: top;">
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

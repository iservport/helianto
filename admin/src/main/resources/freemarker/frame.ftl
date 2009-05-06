<#--
 # Basic frame
 #
 # @author Mauricio Fernandes de Castro
 #-->
<#include "${import!\"/import\"}.ftl"/>
<#include "${assign!\"/empty\"}.ftl"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<#include "${head!\"/head\"}.ftl"/>
<div id="layout">
<body>
<table name="bodyTable">
<#-- a top navigation line -->
<tr id="entityline">
<td colspan="3" >
        <#include "${search!\"/empty\"}.ftl"/>
        <#include "${home!\"/empty\"}.ftl"/>
        <#include "${navigate!\"/empty\"}.ftl"/>
    	<b>${title!"Selection"}</b>
</td>
</tr>
<#-- a head line -->
<tr id="headline">
<td colspan="3" >
		<#include "${forward!\"/empty\"}.ftl"/>
</td>
</tr>
<#-- actual content starts here -->
<tr id="content">
<td id="sidebar2">
        <#include "${sidenav!\"/empty\"}.ftl"/>
        <#include "${sidebar!\"/empty\"}.ftl"/>
</td>
<td id="browse">
		<#include "${headline!\"/empty\"}.ftl"/>
		<#include "${template!\"/empty\"}.ftl"/>
</td>
<td id="info">
		<#include "${info!\"/empty\"}.ftl"/>
</td>
</tr>
</table> <!-- end of bodyTable -->
</body>
</div>
</html>


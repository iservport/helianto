<#macro flowKey>
<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}"/>
</#macro>

<#macro submit event="next", caption="Next" >
<td><input type="submit" name="_eventId_${event}" value="${caption}" class="btn" /></td>
</#macro>

<#macro anchor event="next", param="">
<a href="flowController.htm?_flowExecutionKey=${flowExecutionKey}&_eventId=${event}${param}"><#nested/></a>
</#macro>

<#macro panel>
<td><table><tr><#nested/></tr></table></td>
</#macro>
<h4>Services</h4>
<#list userModel.pages['service'].list?if_exists as service >
	<p>
	<#assign isCurrent=false />
	<#list userModel.pages['userGroupRole'].list?if_exists as currentRole >
		<#if currentRole.service.id==service.id >
			<#assign isCurrent=true />
		</#if>
	</#list>
	<#if !isCurrent >
	<div style="float: right;">></div>
	<@selectModel "${service_index}", "Service">${service.id?c}</@selectModel>
	<@selectModel "${service_index}", "Service">${service.serviceName}</@selectModel>
	</#if>
	</p>
</#list>

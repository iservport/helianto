<h4>User ${user.id} </h4>

<h4>Groups</h4>
<#list userModel.pages['userGroup'].list?if_exists as userGroup >
	<p>
	<#assign isInRole=false />
	<#list parentList as parent >
		<#if parent.id==userGroup.id >
			<#assign isInRole=true />
		</#if>
	</#list>
	<#if isInRole >
	${userGroup.id?c}
	${userGroup.userKey}
	<#else>
	<div style="float: right;">></div>
	<@selectModel "${userGroup_index}">${userGroup.id?c}</@selectModel>
	<@selectModel "${userGroup_index}">${userGroup.userKey}</@selectModel>
	</#if>
	</p>
</#list>

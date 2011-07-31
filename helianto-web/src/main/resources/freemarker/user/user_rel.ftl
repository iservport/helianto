<h4>Groups</h4>
<#list userModel.pages['userGroup'].list?if_exists as userGroup >
	<p>
		<div style="float: right;">></div>
		<@selectModel "${userGroup_index}", "UserGroupToAssociate">${userGroup.id?c}</@selectModel>
		<@selectModel "${userGroup_index}", "UserGroupToAssociate">${userGroup.userKey}</@selectModel>
	</p>
</#list>

	<h2>Groups</h2>
	<table>
		<thead>
		<tr>
		  <td colspan="2">Group name</td>
		</tr>
		</thead>
		<tbody>
		<#list userModel.pages['userGroup'].list?if_exists as userGroup >
			<tr class="row${userGroup_index%2}">
			  <@selectModelTr "${userGroup_index}">${userGroup.id?c}</@selectModelTr>
			  <td >${userGroup.userKey}</td>
			</tr>
		</#list>
		</tbody>
	</table>

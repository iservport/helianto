<div id="panel">
<table>
<thead>
<tr>
  <td colspan="2">User name</td>
  <td >Status</td>
</tr>
</thead>
<tbody>
<#list targetList?if_exists as target >
<tr>
  <@select "${target_index}">${target.id?c}</@select>
  <td >
      ${target.userName}<br />
      ${target.userPrincipal}
  </td>
  <td >${target.userState?if_exists}</td>
</tr>
</#list>
</tbody>
</table>
</div>

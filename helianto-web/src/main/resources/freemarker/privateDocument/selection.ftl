<div id="panel">
	<div class="toolbar">
		<@secure "ROLE_USER_WRITE" ><@create "PrivateDocument" >+ Document</@create></@secure>
	</div>
	<p>
		${privateDocument.listSize!"0"} document(s) found.
	</p>
	<table>
		<thead>
		<tr>
		  <td colspan="3">Private document</td>
		  <td >Type</td>
		</tr>
		</thead>
		<tbody>
		<#list privateDocument.list?if_exists as privateDocument >
		<tr class="row${privateDocument_index%2}">
		  <td ><@select "${privateDocument_index}", "privateDocument.">${privateDocument.id?c}</@select></td>
		  <td >
		      <@select "${privateDocument_index}", "privateDocument">${privateDocument.docCode}</@select>
		  </td>
		  <td>${privateDocument.docName}</td>
		  <td >${privateDocument.contentType}</td>
		</tr>
		</#list>
		</tbody>
	</table>
</div>


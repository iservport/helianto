<div id="panel">

	<h2>${privateDocument.docCode}</h2>
	<p>${privateDocument.docName?if_exists}</p>
	
	<p>Status</p>
	<p>${privateDocument.contentType}</p>
		
	<#if privateDocument.text>
		${privateDocument.contentAsString?if_exists}
	<#elseif privateDocument.image>
		<img src="privateDocument/code/${privateDocument.docCode}" />
	<#else>
		<object width="100%" height="600px" data="privateDocument/code/${privateDocument.docCode}" type="${privateDocument.multipartFileContentType}" />
	</#if>

</div>
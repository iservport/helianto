<#import "/spring.ftl" as spring />
<@spring.bind "operatorForm.operator.*" /> 

<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
</head>
<body>
<form name="operatorForm" method="POST" action="admin.htm" >
<table>
<tr>
<td style="width: 200px; height: 320px;">
	<table>
	<tr>
	<td>&#160;</td>
	<td>There is no Operator present. This is likely a
	    first time installation.</td>
	</tr>
	</table>
</td>
<td style="background-image: url(img/arrow-bg.png);">
	<table style="width: 200px;">
	<tr>
	<td>Name: </td>
	<td><@spring.formInput "operatorForm.operator.operatorName", 'size="20"  maxlength="20"'/></td>
	</tr><tr>
	<#assign operationModes={
	     "L":"LOCAL"
	    ,"E":"ENTERPRISE"
	    ,"D":"DELEGATED"
	    }>
	<td>Mode of operation: </td>
	<td><@spring.formSingleSelect "operatorForm.operator.operationMode", operationModes /> 
	</td> 
	</tr><tr>
	<td>Locale: </td>
	<td>${operatorForm.operator.locale}</td>
	</tr><tr colspan="2">
	<td><input type="submit" alignment="center" value="Next" class="btn"></td>
	</tr>
	</table>
</td>
</tr>
</table>
<input type="hidden" name="_flowExecutionKey"
       value="${flowExecutionKey}"/>
<input type="hidden" name="_eventId" value="enter"/>
</form>
</body>
<html>
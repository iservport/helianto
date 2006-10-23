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
<td style="width: 200px;">
	<table>
	<tr>
	<td>Available operators.</td>
	</tr>
	</table>
</td>
<td style="background-image: url(img/arrow-bg.png);">
	<table >
	<tr>
		<td>Id</td>
		<td>Operator Name</td>
		<td>Locale</td>
		<td>Operation Mode</td>
		<td>Delegated from</td>
	</tr>
	<#list operatorList as operator>
	<tr>
		<td>${operator.id}</td>
		<td>${operator.operatorName?if_exists}</td>
		<td>${operator.locale?if_exists}</td>
		<td>${operator.operationMode?if_exists}</td>
		<td>${operator.parent?if_exists.operatorName?if_exists}</td>
	</tr>
	</#list>
	</table>
</td>
</tr>
</table>
<input type="submit" alignment="center" value="Next" class="btn">
<input type="hidden" name="_flowExecutionKey"
       value="${flowExecutionKey}"/>
<input type="hidden" name="_eventId" value="enter"/>
</form>
</body>
<html>
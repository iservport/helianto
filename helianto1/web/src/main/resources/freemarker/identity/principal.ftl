<#import "/spring.ftl" as spring />
<#import "/macros/layout.ftl" as lo />
<#import "/macros/box.ftl" as bx />
<#import "/macros/head.ftl" as hd />
<#import "/macros/swf.ftl" as fl />
<#import "/macros/cancelForm.ftl" as cf />
<@spring.bind "identityForm.credential.*" /> 

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<@hd.head "Registration">
	  <link href="style-blue.css" rel="stylesheet" />
</@hd.head>
<body>
<div style="height: 120px;"></div>

<div id="layout">

	<div id="sidebar">
	
		<h2>Registration process</h2>
		
		<p>Welcome to the registration process.</p>
		<p>The system often requires you to present your <span class="me">Identity</span>
		and <span class="me">Credential</span>.</p>
		<p>The following steps will guide you through the registration process.</p>

		<#assign page=0/>
		<@cf.cancelForm "admin.htm"/>
		
	</div>
	
	<div id="main">
	
		<div id="navigator" style="position: relative; float: right;">
			<h3>Navigation</h3>
			<#include "summary.ftl"/>
		</div>
		
		<div id="detail" style="position: relative; float: right;">
			<h3>OR choose</h3>
			<p>a plain ID.</p>
		</div>
	
		<div id="detail">
			<h3>Provide your e-mail</h3>
			<p>By default, your identity will be connected to your e-mail.</p>
		</div>
	
		<p>Notice that your email is required to prevent duplicate 
		  identity registration.</p>
		  
		<form action="admin.htm" method="POST">
		<@bx.table "Identification">
	
		<@bx.group >
			<@bx.row>E-mail:</@bx.row>
			<@bx.row>
			<@spring.formInput "identityForm.credential.identity.principal", 'size="32"'/>
			</@bx.row>
		</@bx.group>

		<tr>
		<td>
		<input type="submit" name="_eventId_next" value="<@spring.messageText "button.next", "Next"/>" class="btn" /></td>
		</tr>

		<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}"/>
		
		</@bx.table>
		</form>
		
	</div>
</div>
</body>
</html>

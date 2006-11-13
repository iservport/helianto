<#import "/spring.ftl" as spring />
<#import "/macros/layout.ftl" as lo />
<#import "/macros/box.ftl" as bx />
<#import "/macros/head.ftl" as hd />
<#import "/macros/cancelForm.ftl" as cf />
<@spring.bind "identityForm.credential.*" /> 

<html>
<@hd.head />
<@lo.layout>
	<@lo.east>
	
		<@bx.table "Registration process - Start">
		
		<@bx.row>
		<p>Welcome to the registration process. Please, follow the 
		required steps bellow to input your identity and credentials.</p>
		</@bx.row>

		<@bx.row>
		<#assign page=0/>
		<#include "summary.ftl"/>
		</@bx.row>
	
		</@bx.table>
		
		<@cf.cancelForm "admin.htm"/>
		
	</@lo.east>
	<@lo.west>
	
		<form action="admin.htm" method="POST">
		<@bx.table "Identification">
	
		<@bx.row>
		<p>Notice that your email is required to prevent duplicate 
		  identity registration. If you do not want	to provide such 
		  information, please, read <a href=''>this</a>.</p>
		</@bx.row>

		<@bx.row>
		E-mail:
		</@bx.row>
		
		<@bx.err>xxx</@bx.err>

		<@bx.row>
		<@spring.formInput "identityForm.credential.identity.principal", 'size="32"'/>
		</@bx.row>

		<tr>
		<td>
		<input type="submit" name="_eventId_next" value="<@spring.messageText "button.next", "Next"/>" class="btn" /></td>
		</tr>

		<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}"/>
		
		</@bx.table>
		</form>
		
	</@lo.west>
</@lo.layout>
</html>
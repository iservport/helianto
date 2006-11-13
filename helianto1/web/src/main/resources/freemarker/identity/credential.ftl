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
	
		<@bx.table "Password selection">
		
		<@bx.row>
		<p>The password required to protect your identity may 
	    be updated either on-line or emailed to you.</p>
		</@bx.row>
		
		<@bx.row>
		<#assign page=3/>
		<#include "summary.ftl"/>
		</@bx.row>
	
		</@bx.table>

		<@cf.cancelForm "admin.htm"/>
		
	</@lo.east>
	<@lo.west>

		<form action="admin.htm" method="POST">
		<@bx.table "Password email">
		
			<#assign sendOptions={
				  "0" : "Send current password"
				, "1" : "Send new password"
			}
			/>
		
			<@bx.row>
			<@spring.formRadioButtons "identityForm.sendOption", sendOptions, "<br />"/>
			</@bx.row>

			<@bx.row>
			<input type="submit" name="_eventId_sendPassword" value="<@spring.messageText "identity.button.sendPassword", "Send"/>"  class="btn" />
			</@bx.row>

			<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}"/>

		</@bx.table>
		</form>

		<!-- 
		 ! Forms
		 !-->
		<form action="admin.htm" method="POST">
		<table>
		
			<tr class="t_title">
			<td colspan="3"><@spring.messageText "identity.header1", "Password"/></td>
			</tr>
			
			<tr>
			<td><@spring.messageText "identity.password1", "Password"/>:</td>
			<td><@spring.formPasswordInput "identityForm.credential.password", 'size="32"'/></td>
			</tr>

			<tr>
			<td><@spring.messageText "identity.password2", "Password confirmation"/>:</td>
			<td><@spring.formPasswordInput "identityForm.credential.verifyPassword", 'size="32"'/></td>
			</tr>

			<tr class="t_title">
			<td colspan="3">
			<input type="submit" name="_eventId_next" value="<@spring.messageText "button.next", "Next"/>" class="btn" /></td>
			</tr>

			<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}"/>

	</@lo.west>
</@lo.layout>
</html>

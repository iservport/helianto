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
	
		<@bx.table "Registration details">
		
		<@bx.row>
		<p>Your identity has been successfully registered. Now, select your 
		preferred profile or accept the defaults and advance directly to 
		the last step.</p>
		</@bx.row>

		<@bx.row>
		<#assign page=1/>
		<#include "summary.ftl"/>
		</@bx.row>
	
		</@bx.table>
	
		<@cf.cancelForm "admin.htm"/>
		
	</@lo.east>
	<@lo.west>

		<form action="admin.htm" method="POST">
		<@bx.table "${identityForm.credential.identity.principal}">
	
		<@bx.group>
			<@bx.row>Optional alias:</@bx.row>
			<@bx.row>
			<@spring.formInput "identityForm.credential.identity.optionalAlias", 'size="32"'/>
			</@bx.row>
		</@bx.group>

		<@bx.row>
		<p>The system operator may redirect some notification messages 
		to your email at your discretion. Please, select "auto" if 
		you can receive messages regularly, or "by request" if you 
		wish to request the messages manually.</p>
		</@bx.row>
		
		<#include "/core/options/notification.ftl" />
		
		<@bx.group>
			<@bx.row>Notification type:</@bx.row>
			<@bx.row>
			<@spring.formSingleSelect "identityForm.credential.identity.notification", notificationTypes />
			</@bx.row>
		</@bx.group>

		<@bx.row>
		<p>If you share the email previously supplied as your unique 
		identity with some other people or organization, you may not 
		wish to supply personal data. Otherwise, please, choose  
		"personal" to make the personal data form available.</p>
		</@bx.row>
		
		<#include "/core/options/identityType.ftl" />
		
		<@bx.group>
			<@bx.row>Identity type:</@bx.row>
			<@bx.row>
			<@spring.formSingleSelect "identityForm.credential.identity.identityType", identityTypes />
			</@bx.row>
		</@bx.group>
		
		<tr class="t_title">
		<td colspan="3">
		<input type="submit" name="_eventId_page1" value="<@spring.messageText "button.previous", "Previous"/>" class="btn" /> | 
		<input type="submit" name="_eventId_page3" value="<@spring.messageText "button.next", "Next"/>" class="btn" /></td>
		</tr>

		<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}"/>
		
		</@bx.table>
		</form>
		
	</@lo.west>
</@lo.layout>
</html>

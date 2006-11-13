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
	
		<@bx.table "Confirmation">
		
		<@bx.row>
		<p>Your credential has been successfully registered. Your next
		action may be a service request, where the supplied principal and 
		password will be required.</p>
		</@bx.row>
		
		<@bx.row>
		<#assign page=4/>
		<#include "summary.ftl"/>
		</@bx.row>
	
		</@bx.table>

		<@cf.cancelForm "admin.htm"/>
		
	</@lo.east>
	<@lo.west>

	
		<form action="admin.htm" method="POST">
		<@bx.table "${identityForm.credential.identity.principal}">
	
		<@bx.row>Optional alias:
		<b>${identityForm.credential.identity.optionalAlias}</b>
		</@bx.row>

		<#include "/core/options/notification.ftl"/>

		<@bx.row>Notification type: 
		<b>${notificationTypes[identityForm.credential.identity.notification]}</b>
		</@bx.row>

		<#include "/core/options/identityType.ftl"/>

		<@bx.row>Identity type: 
		<b>${identityTypes[identityForm.credential.identity.identityType]}</b>
		</@bx.row>

		<#if identityForm.credential.identity.identityType='P' >

		<@bx.row>First name: 
		<b>${identityForm.credential.identity.personalData.firstName}</b>
		</@bx.row>

		<@bx.row>Last name: 
		<b>${identityForm.credential.identity.personalData.lastName}</b>
		</@bx.row>

		<#include "/core/options/gender.ftl"/>
		
		<@bx.row>Gender: 
		<b>${gender[identityForm.credential.identity.personalData.gender]}</b>
		</@bx.row>
		
		<#include "/core/options/appellation.ftl"/>
		
		<@bx.row>Appellation: 
		<b>${appellation[identityForm.credential.identity.personalData.appellation?string]}</b>
		</@bx.row>

		</#if>

		<tr class="t_title">
		<td colspan="3">
		<input type="submit" name="_eventId_page2" value="<@spring.messageText "button.previous", "Previous"/>" class="btn" /> | 
		<input type="submit" name="_eventId_confirm" value="<@spring.messageText "button.next", "Next"/>" class="btn" /></td>
		</tr>

		<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}"/>
		
		</@bx.table>
		</form>
		
	</@lo.west>
</@lo.layout>
</html>

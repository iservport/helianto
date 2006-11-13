<#ftl strip_whitespace=true>
<#import "/spring.ftl" as spring />
<#import "/macros/layout.ftl" as lo />
<#import "/macros/box.ftl" as bx />
<#import "/macros/head.ftl" as hd />
<#import "/macros/cancelForm.ftl" as cf />
<@spring.bind "identityForm.*" />

<html>
<@hd.head />
<@lo.layout>
	<@lo.east>
	
		<@bx.table "Personal data">
		
		<@bx.row>
		<p>Please, input your personal identification data.</p>
		</@bx.row>

		<@bx.row>
		<#assign page=2/>
		<#include "summary.ftl"/>
		</@bx.row>
	
		</@bx.table>
		
		<@cf.cancelForm "admin.htm"/>
		
	</@lo.east>
	<@lo.west>
	
		<form action="admin.htm" method="POST">
		<@bx.table "${identityForm.credential.identity.principal}">
	
		<@bx.group >
			<@bx.row>First name:</@bx.row>
			<@bx.row>
			<@spring.formInput "identityForm.credential.identity.personalData.firstName", 'size="32"' />
			</@bx.row>
		</@bx.group>

		<@bx.group >
			<@bx.row>Last name:</@bx.row>
			<@bx.row>
			<@spring.formInput "identityForm.credential.identity.personalData.lastName", 'size="32"' />
			</@bx.row>
		</@bx.group>
		
		<#include "/core/options/gender.ftl"/>
		
		<@bx.group >
			<@bx.row>Gender:</@bx.row>
			<@bx.row>
			<@spring.formSingleSelect "identityForm.credential.identity.personalData.gender", gender />
			</@bx.row>
		</@bx.group>
		
		<#include "/core/options/appellation.ftl"/>
		
		<@bx.group >
			<@bx.row>Appellation:</@bx.row>
			<@bx.row>
			<@spring.formSingleSelect "identityForm.credential.identity.personalData.appellation", appellation />
			</@bx.row>
		</@bx.group>
		
		
		<tr class="t_title">
		<td colspan="3">
		<input type="submit" name="_eventId_page2" value="<@spring.messageText "button.previous", "Previous"/>" class="btn" /> | 
		<input type="submit" name="_eventId_page4" value="<@spring.messageText "button.next", "Next"/>" class="btn" /></td>
		</tr>

		<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}"/>

		</@bx.table>
		
	</@lo.west>
</@lo.layout>
</html>

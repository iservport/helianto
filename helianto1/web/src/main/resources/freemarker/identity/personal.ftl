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
	
	zzz
	<#list spring.status.errorCodes as error>
    <@row classOrStyle >
    x${error}x
    </@row>
    </#list>
	zzz
	
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
	
		<@bx.row>
		First name:
		</@bx.row>

		<@bx.row>
		<@spring.formInput "identityForm.credential.identity.personalData.firstName", 'size="32"' />
		</@bx.row>

		<@bx.row>
		<@bx.showErrors spring.status.errorMessages />
		</@bx.row>

		<@bx.row>
		Last name:
		</@bx.row>

		<@bx.row>
		<@spring.formInput "identityForm.credential.identity.personalData.lastName", 'size="32"' />
		</@bx.row>
		
		<#assign genderM><@spring.messageText "identity.gender.M", "male"/></#assign>
		<#assign genderF><@spring.messageText "identity.gender.F", "female"/></#assign>
		<#assign genderN><@spring.messageText "identity.gender.N", "not supplied"/></#assign>
		<#assign gender={
		    'M': "${genderM}"
		  , 'F': "${genderF}" 
		  , 'N': "${genderN}" 
		  } />

		<@bx.row>
		Gender:
		</@bx.row>

		<@bx.row>
		<@spring.formSingleSelect "identityForm.credential.identity.personalData.gender", gender />
		</@bx.row>
		
		<@bx.row>
		Appellation:
		</@bx.row>

		<#assign appellation0><@spring.messageText "identity.appellation.0", "not supplied"/></#assign>
		<#assign appellation1><@spring.messageText "identity.appellation.1", "Miss"/></#assign>
		<#assign appellation2><@spring.messageText "identity.appellation.2", "Mr. ou Mrs."/></#assign>
		<#assign appellation3><@spring.messageText "identity.appellation.3", "Ms."/></#assign>
		
		<#assign appellation={
		    '0': "${appellation0}"
		  , '1': "${appellation1}" 
		  , '2': "${appellation2}" 
		  , '3': "${appellation3}" 
		  } />

		<@bx.row>
		<@spring.formSingleSelect "identityForm.credential.identity.personalData.appellation", appellation />
		</@bx.row>
		
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

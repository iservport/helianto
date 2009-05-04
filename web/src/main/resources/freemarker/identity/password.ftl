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
		<p>Please, update on-line the password required to protect your 
		identity.</p>
		</@bx.row>
		
		<@bx.row>
		<#assign page=3/>
		<#include "summary.ftl"/>
		</@bx.row>
	
		</@bx.table>

		<@cf.cancelForm "admin.htm"/>
		
	</@lo.east>
	<@lo.west>

		<@bx.table "${identityForm.credential.identity.principal}">

			<@bx.row>
			<b>Important note!</b>
			</@bx.row>
	
			<@bx.row>
			<p>You did not supply an addressable identity so your password
			can't be emailed to you. If you have no restrictions to receive
			email, please, consider the recommended option and change to a new
			addressable	identity.</p>
			</@bx.row>
	
		</@bx.table>		
		
		<form action="admin.htm" method="POST">
		<#include "passwordForm.ftl" />
		</form>

	</@lo.west>
</@lo.layout>
</html>

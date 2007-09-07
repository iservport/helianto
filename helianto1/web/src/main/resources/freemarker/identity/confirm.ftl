<#import "/spring.ftl" as spring />
<#import "/macros/layout.ftl" as lo />
<#import "/macros/box.ftl" as bx />
<#import "/macros/head.ftl" as hd />
<#import "/macros/swf.ftl" as fl />
<#import "/macros/cancelForm.ftl" as cf />
 
<@spring.bind "identityForm.credential.*" /> 

<#include "/core/options/notification.ftl"/>
<#include "/core/options/identityType.ftl"/>
<#include "/core/options/gender.ftl"/>
<#include "/core/options/appellation.ftl"/>

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
		
		<p>Your <span class="me">Identity</span>
		and <span class="me">Credential</span> has been successfully registered.</p>

		<#assign page=0/>
		<@cf.cancelForm "admin.htm"/>
		
	</div>
	
	<div id="main">
	
		<div id="navigator" style="position: relative; float: right;">
			<h3>Summary</h3>
			<#include "summary.ftl"/>
		</div>
		
		
		<p>Please confirm your data.</p>
		
		<p>Optional alias:
		<b>${identityForm.credential.identity.optionalAlias}</b>
		</p>

		<p>Notification type: 
		<b>${notificationTypes[identityForm.credential.identity.notification]}</b>
		</p>

		<p>Identity type: 
		<b>${identityTypes[identityForm.credential.identity.identityType]}</b>
		</p>

		<#if identityForm.credential.identity.identityType='P' >

		<p>First name: 
		<b>${identityForm.credential.identity.personalData.firstName}</b>
		</p>

		<p>Last name: 
		<b>${identityForm.credential.identity.personalData.lastName}</b>
		</p>

		<p>Gender: 
		<b>${gender[identityForm.credential.identity.personalData.gender]}</b>
		</p>
		
		<p>Appellation: 
		<b>${appellation[identityForm.credential.identity.personalData.appellation?string]}</b>
		</p>

		</#if>

		<form action="admin.htm" method="POST">
		<@bx.table "${identityForm.credential.identity.principal}">

		<tr>
		<@fl.submit "page2", "Previous"/>
		<@fl.submit "confirm", "Confirm"/>
		</tr>

		</@bx.table>

		<@fl.flowKey/>
		
		</form>
		</div>
				
	</div>

</div>
</body>
</html>

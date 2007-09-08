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
			<h3>Summary</h3>
			<#include "summary.ftl"/>
		</div>
		
		<div id="detail">
			<h3>Provide your e-mail</h3>
			<div id="narrow_detail" style="float: right; margin: 4em -6em 0 1em;">
				<h3>OR choose</h3>
				<p>If you do not want to supply your e-mail, try
				one of the alternatives below:</p>
				<ul>
				<li><@fl.anchor "plain">a plain ID</@fl.anchor></li>
				<li><@fl.anchor "generated">a system generated ID</@fl.anchor></li>
				</ul>
			</div>
			<p><b>By default,</b> your identity will be connected to your e-mail.</p>
		</div>
	
		<p>Please, notice that e-mail ID prevents duplicate 
		  identity registration and can also keep you up to date with system 
		  notifications.</p>
		  
		<div id="wide_detail">
		<form action="admin.htm" method="POST">
		<@bx.table "Identification">
	
		<@bx.group >
			<@bx.row>E-mail:</@bx.row>
			<@bx.row>
			<@spring.formInput "identityForm.credential.identity.principal", 'size="32"'/>
			</@bx.row>
		</@bx.group>

		<#include "/core/options/notification.ftl" />
		
		<@bx.group>
			<@bx.row>Choose system notification option:</@bx.row>
			<@bx.row>
			<@spring.formSingleSelect "identityForm.credential.identity.notification", notificationTypes />
			</@bx.row>
		</@bx.group>

		<@bx.row>Now, you can:</@bx.row>
		
		<tr>
		<@fl.submit "personalData", "Complete your Personal Data"/>
		</tr>

		<tr>
		<@fl.submit "paswordSelection", "Skip Personal Data, go to Password Selection"/>
		</tr>

		<@fl.flowKey/>
		
		</@bx.table>
		</form>
		</div>
				
	</div>

	<div id="navigator" style="position: relative; float: right;">
		<h3>Privacy</h3>
		<p>Check our <@fl.anchor "privacy">privacy</@fl.anchor> policy.</p>
	</div>
		
</div>
</body>
</html>

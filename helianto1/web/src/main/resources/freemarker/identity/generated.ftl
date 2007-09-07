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
			<h3>Generate an ID</h3>
			<div id="narrow_detail" style="float: right; margin: 4em -6em 0 1em;">
				<h3>OR choose</h3>
				<p>If you want to supply your own ID, try
				one of the alternatives below:</p>
				<ul>
				<li><@fl.anchor "principal">an e-mail ID</@fl.anchor></li>
				<li><@fl.anchor "generated">a plain ID</@fl.anchor></li>
				</ul>
			</div>
			<p>Let the system generate an ID for you. It will use the
			personal data you supply on next page.</p>
		</div>
	
		<p>Please, notice that your e-mail is an easy way to prevent duplicate 
		  identity registration and also to keep you up to date with system 
		  notifications.</p>
		  
		<div id="wide_detail">
		<form action="admin.htm" method="POST">
		<@bx.table "Identification">
	
		<tr>
		<@fl.submit "next"/>
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

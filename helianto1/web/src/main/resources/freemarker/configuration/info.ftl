<#import "/spring.ftl" as spring />
<#import "/macros/head.ftl" as hd />
<#import "/macros/box.ftl" as bx />
<#import "/macros/swf.ftl" as fl />

<@hd.head "Welcome">
	  <link href="style-blue.css" rel="stylesheet" />
</@hd.head>
<body>
<div style="height: 120px;"></div>
<div id="layout">

	<div id="sidebar">
	<h2>Configuration process <span style="font-weight: bolder;">></span></h2>
	<p>You were redirected to the system configuration
	    page because the system was not able to detect a manager. Please, read
	    the following instructions carefully. If you don't feel secure to proceed or have any doubt, please,
	    ask your system administrator for assistance.</p>
	<p>The steps you are about to follow
	    will grant you unrestricted privileges over the system you are about to install. You will
	    become the system manager, which has control over the top level admin Service. Please, read the
	    documentation if you are not yet aware of the system manager responsibilities.</p>
	</div>
	
	<div id="main">
	<p>Following the next pages, you will be required to:</p>
	<ol>
		<li><@fl.anchor >Provide a system identity to yourself;</@fl.anchor></li>
		<li>Select a namespace identifier that will be
		   assigned to a system operator and to a default entity;</li>
		<li>Login to the system for the first time as a confirmation
		   of a successfull configuration.</li>
	</ol>
	</div>
</div>
</body>
</html>

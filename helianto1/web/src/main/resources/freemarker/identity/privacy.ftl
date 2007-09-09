<#import "/spring.ftl" as spring />
<#import "/macros/layout.ftl" as lo />
<#import "/macros/box.ftl" as bx />
<#import "/macros/head.ftl" as hd />
<#import "/macros/swf.ftl" as fl />
<#import "/macros/cancelForm.ftl" as cf />
<@spring.bind "identityForm.credential.*" /> 

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<@hd.head "Privacy">
	  <link href="style-blue.css" rel="stylesheet" />
</@hd.head>
<body>
<div id="layout">

	<div id="sidebar">
	
		<h2>Privacy policy</h2>
		
		<p>Sorry, the system operator has not published the
			applicable privacy policy yet.</p>

	</div>
	
	<div id="main">
	
		<div id="narrow_detail" >
			<h3>Go back to</h3>
			<ul>
			<li><@fl.anchor "principal">e-mail ID</@fl.anchor></li>
			<li><@fl.anchor "plain">plain ID</@fl.anchor></li>
			<li><@fl.anchor "generated">system generated ID</@fl.anchor></li>
			</ul>
		</div>
		
	</div>

		
</div>
</body>
</html>

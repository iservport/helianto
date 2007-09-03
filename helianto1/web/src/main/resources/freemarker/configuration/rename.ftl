<#import "/spring.ftl" as spring />
<#import "/macros/head.ftl" as hd />
<#import "/macros/box.ftl" as bx />
<#import "/macros/swf.ftl" as fl />

<html>
<@hd.head "Rename" />
<body>
<div style="height: 120px;"></div>
<div id="layout">

	<div id="sidebar">
	<h2>Configuration process <span style="font-weight: bolder;">></span></h2>
	<p>The required <span class="me">identity</span> was successfully created and 
	assigned to your e-mail.</p>
	<p>The manager privileges are associated to one or more <span class="me">entities</span>. 
	Next step is to associate the identity to at least one <span class="me">entity</span> and 
	its corresponding <span class="me">operator</span>.</p>
	</div>
	
	<div id="main">

		<form action="admin.htm" method="POST">
		<table>
		
			<@bx.group >
				<@bx.row ><b>Rename to:</b></@bx.row>
				<@bx.row>
				<@spring.formInput "userForm.user.entity.alias", 'size="20" maxlength="20"'/>
				</@bx.row>
			</@bx.group>

			<tr>
				<@fl.submit/>
			</tr>

			<@fl.flowKey/>

		</table>
		</form>

		<div id="navigator">
		<ol>
			<li><@fl.anchor "back">Identity registration</@fl.anchor></li>
			<li>Operator and Entity name</li>
			<li>Login and confirm</li>
		</ol>
		</div>
	
	</div>

</div>
</body>
</html>

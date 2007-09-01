<#import "/spring.ftl" as spring />
<#import "/macros/head.ftl" as hd />
<#import "/macros/box.ftl" as bx />

<html>
<@hd.head "Rename" />
<body>
<div style="height: 80px;"></div>
<div id="layout">

	<div id="sidebar" style="border: 1px dotted #cccccc;">
	<h2>Configuration process <span style="font-weight: bolder;">></span></h2>
	<p>The required <span class="me">identity</span> was successfully created and 
	assigned to your e-mail.</p>
	<p>The manager privileges are associated to one or more <span class="me">entities</span>. 
	Next step is to associate the identity to ate least one <span class="me">entity</span> and 
	its corresponding <span class="me">operator</span>.</p>
	</div>
	
	<div id="main">

	<table>
		<tr>
		<td style="vertical-align: top;">
			<!-- 
			 ! Forms
			 !-->
			<form action="admin.htm" method="POST">
			<table>
			
				<tr class="t_title">
				<td colspan="3">Go to first step</td>
				</tr>
				
				<tr>
				<td>+</td>
				<td>Identity registration.</td>
				<td>Done.</td>
				</tr>
	
				<@bx.group >
					<@bx.row ><b>Rename to:</b></@bx.row>
					<@bx.row>
					<@spring.formInput "userForm.user.entity.alias", 'size="20" maxlength="20"'/>
					</@bx.row>
				</@bx.group>
	
				<tr>
				<td>&#160;</td>
				<td>Operator and Entity name.</td>
				<td><input type="submit" name="_eventId_next" value="Go" class="btn" /></td>
				</tr>
	
				<tr>
				<td>-</td>
				<td>Login and confirm.</td>
				<td>&#160;</td>
				</tr>
	
				<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}"/>
				
	
			</table>
			</form>
			<form action="admin.htm" method="POST">
			<p style="font-weight: bold; text-align: right;">
				<input type="submit" name="_eventId_cancel" value="Cancel"  class="btn" />
				<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}"/>
			</p>
			</form>
		</td>
		</tr>
	</table>
	</div>

</div>
</body>
</html>

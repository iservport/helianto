<#import "/spring.ftl" as spring />

<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
</head>
<body>
<table>
	<tr>
	<td style="width: 35%; vertical-align: top; border: 1px dotted #cccccc;">
	<p>Configuration process - Start</p>
	<p>You were redirected to this
	    page because the system was not able to detect a previous configuration. Please, read
	    the following instructions carefully. If you don´t feel secure to proceed or have any doubt, please,
	    ask your system administrator for assistance.</p>
	<p>The steps you are about to follow
	    will grant you unrestricted privileges over the system you are about to install. You will
	    become the system manager, which has control over the top level admin Service. Please, read the
	    documentation if you are not yet aware of the system manager responsibilities.</p>
	<p>You will be required to:</p>
	<ol>
		<li>Provide a system identity to yourself;</li>
		<li>Select a namespace identifier that will be
		   assigned to a system operator and to a default entity;</li>
		<li>Login to the system for the first time as a confirmation
		   of a successfull configuration.</li>
	</ol>
	</td>
	<td style="width: 65%; vertical-align: top;">
		<!-- 
		 ! Forms
		 !-->
		<form action="admin.htm" method="POST">
		<table>
		
			<tr class="t_title">
			<td colspan="3">Go to first step</td>
			</tr>
			
			<tr>
			<td>!</td>
			<td>Identity registration.</td>
			<td><input type="submit" name="_eventId_next" value="Go" class="btn" /></td>
			</tr>

			<tr>
			<td>-</td>
			<td>Operator and Entity name.</td>
			<td>&#160;</td>
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
</body>
</html>

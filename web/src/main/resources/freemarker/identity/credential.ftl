<#import "/spring.ftl" as spring />
<#import "/macros/layout.ftl" as lo />
<#import "/macros/box.ftl" as bx />
<#import "/macros/head.ftl" as hd />
<#import "/macros/cancelForm.ftl" as cf />

<@spring.bind "identityForm.credential.*" /> 

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<@hd.head "Password selection">
	  <link href="style-blue.css" rel="stylesheet" />
	<script language="javascript">
function dontsend (choice) {
sendBtn = document.getElementById("_eventId_send");
if (choice==-1) {
sendBtn.disabled = true;
} else {
sendBtn.disabled = false;
}
return false;
};
	</script>
</@hd.head>
<body>
<div id="layout">

	<div id="sidebar">
	
		<h2>Password selection"</h2>
		
		<p>The password required to protect your identity may 
	    be updated either on-line or emailed to you.</p>
		
		<@cf.cancelForm "admin.htm"/>
		
	</div>
	
	<div id="main">
	
		<div id="navigator" style="position: relative; float: right;">
			<h3>Summary</h3>
			<#include "summary.ftl"/>
		</div>
		
		<form action="admin.htm" method="POST">
		<@bx.table "${identityForm.credential.identity.principal}">
		
			<@bx.row>
			<p>Please notice the password email option is recommended.</p>
			</@bx.row>
		
			<#assign sendOptions={
				  "C" : "Send current password"
				, "N" : "Send new password"
				, "V" : "verify on-line (see below)"
			}
			/>
		
			<@bx.row>
			<@spring.formRadioButtons "identityForm.sendOption", sendOptions, "<br />", "onChange='dontsend(this.value);'"/>
			</@bx.row>

			<@bx.row>
			<input id="_eventId_send" type="submit" name="_eventId_send" value="<@spring.messageText "identity.button.sendPassword", "Send"/>"  class="btn" />
			</@bx.row>

			<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}"/>

		</@bx.table>
		</form>

		<form action="admin.htm" method="POST">
		<#include "passwordForm.ftl">
		</form>

	</div>

</div>
</body>
</html>

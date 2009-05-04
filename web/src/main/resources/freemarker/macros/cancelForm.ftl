<#--
 # A form including only the cancel transition.
 # To be used within a SWF view.
 #
 # @author Mauricio Fernandes de Castro
-->
<#macro cancelForm action="home.htm" >
	<form action="${action}" method="POST">
	<p style="font-weight: bold; text-align: right;">
		<input type="submit" name="_eventId_cancel" 
		    value="<@spring.messageText "button.cancel", "Cancel"/>"  
		    class="btn" />
		<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}"/>
	</p>
	</form>
</#macro>

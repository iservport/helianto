<@bx.table "Verify password on-line">

	<@bx.group>
		<@bx.row>Password:</@bx.row>
		<@bx.row>
		<@spring.formPasswordInput "identityForm.credential.password", 'size="32"'/>
		</@bx.row>
		<@bx.row>Password confirmation:</@bx.row>
		<@bx.row>
		<@spring.formPasswordInput "identityForm.credential.verifyPassword", 'size="32"'/>
		</@bx.row>
	</@bx.group>

	<@bx.row>
	<input type="submit" name="_eventId_next" value="<@spring.messageText "identity.button.next", "Next"/>"  class="btn" />
	</@bx.row>

	<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}"/>

</@bx.table>

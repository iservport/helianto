<#--
 * formInput
 *
 * Display a form input field of type 'text' and bind it to an attribute
 * of a command or bean.
 *
 * @param path the name of the field to bind to
 * @param attributes any additional attributes for the element (such as class
 *    or CSS styles or size
 -->
<#macro formDate path >
    <@spring.bind path/>
    <input type="text" id="${spring.status.expression}" name="${spring.status.expression}" value="${spring.stringStatusValue?string('dd/MM/yy hh:mm')}" size="14"
    <@spring.closeTag/>
</#macro>

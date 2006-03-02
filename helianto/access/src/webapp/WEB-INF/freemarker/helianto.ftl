<#--
 * Copyright 2005 I Serv Consultoria Empresarial Ltda.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
-->

<#--
 * A collection of macros to help with FreeMarker format.
 *
 * Includes macros intended to override macros with the same name
 * in the spring.ftl library, and macros to ease parameter 
 * defintiions required by the Spring webflow package.
 *
 * @author Maurício Fernandes de Castro
 * @version $Id$
-->

<#import "spring.ftl" as spring />

<#--
 * formSingleSelect
 *
 * OVERRIDES macro with the same name in spring ftl
 * SEE TOPIC 1341 in Spring Forum
 *
 * Show a selectbox (dropdown) input element allowing a single value to be chosen
 * from a list of options.
 *
 * @param path the name of the field to bind to
 * @param options a map (value=label) of all the available options
 * @param attributes any additional attributes for the element (such as class
 *        or CSS styles or size
-->
<#macro formSingleSelect path options attributes="">
    <@spring.bind path/>
    <select name="${spring.status.expression}" ${attributes}>
        <#list options?keys as optionKey>
            <option 
                <#if spring.status.value?default("")?string == optionKey?string>selected="true"</#if>
                value="${optionKey}"
            >${options[optionKey]}</option>
        </#list>
     </select>
</#macro>

<#--
 * formRadioButtons
 *
 * OVERRIDES macro with the same name in spring ftl
 * SEE TOPIC 1341 in Spring Forum
 *
 * Show radio buttons.
 *
 * @param path the name of the field to bind to
 * @param options a map (value=label) of all the available options
 * @param separator the html tag or other character list that should be used to
 *        separate each option.  Typically '&nbsp;' or '<br>'
 * @param attributes any additional attributes for the element (such as class
 *        or CSS styles or size
-->
<#macro formRadioButtons path options separator attributes="">
	<@spring.bind path/>
	<#list options?keys as optionKey>
	    <input type="radio" name="${spring.status.expression}" value="${optionKey}"
		    <#if spring.status.value?default("")?string == optionKey?string>checked="checked"</#if> ${attributes}
	    <@spring.closeTag/>
	    ${options[optionKey]}${separator}
	</#list>
</#macro>

<#--
 * formCheckboxes
 *
 * OVERRIDES macro with the same name in spring ftl
 * SEE TOPIC 1341 in Spring Forum
 *
 * Show checkboxes.
 *
 * @param path the name of the field to bind to
 * @param options a map (value=label) of all the available options
 * @param separator the html tag or other character list that should be used to
 *        separate each option.  Typically '&nbsp;' or '<br>'
 * @param attributes any additional attributes for the element (such as class
 *        or CSS styles or size
-->
<#macro formCheckboxes path options separator attributes="">
	<@spring.bind path/>
	<#list options?keys as optionKey>
	    <#assign isSelected = contains(spring.status.value?default([""]), optionKey)>
	    <input type="checkbox" name="${spring.status.expression}" value="${optionKey}"
		<#if isSelected>checked="checked"</#if> ${attributes}
	    <@spring.closeTag/>
	    ${options[optionKey]}${separator}
	</#list>
</#macro>

<#--
 * listContains
 *
 * Macro to return true if the list contains the scalar, false if not.
 * Surprisingly not a FreeMarker builtin. This function is used internally but
 * can be accessed by user code if required.
 *
 * @param list the list to search for the item
 * @param item the item to search for in the list
 * @return true if item is found in the list, false otherwise.
-->
<#function contains list item>
	<#list list as nextInList>
	<#if nextInList?string == item?string><#return true></#if>
	</#list>
	<#return false>
</#function>

<#--
 # formInput
 #
 # A macro to ease the Spring formInput macro usage. A message
 # bundle is required to render the form input label, where 
 # keys must have the same values used as paths to find objects 
 # placed in the application context.
 # 
 # @param path the path string used to search objects in the 
 #        context and keys in the message bundle.
 * @param attributes any additional attributes for the element (such as class
 *        or CSS styles or size
-->
<#macro formInput path attributes='size="10"'>
	<tr>
    	<td><@spring.message path/>: </td>
        <td><@spring.formInput path?string, attributes/>
        <#nested/></td>
	</tr>
</#macro>

<#--
 # formTextarea
 #
 # A macro to ease the Spring formInput macro usage. A message
 # bundle is required to render the form input label, where 
 # keys must have the same values used as paths to find objects 
 # placed in the application context.
 # 
 # @param path the path string used to search objects in the 
 #        context and keys in the message bundle.
 * @param attributes any additional attributes for the element (such as class
 *        or CSS styles or size
-->
<#macro formTextarea path attrib='rows="5" style="width: 100%;"'>
	<tr>
    	<td><@spring.message path/>: </td>
        <td><@spring.formTextarea path, attrib/></td>
	</tr>
</#macro>

<#--
 # submitParams
 #
 # A macro to render standard flow parameters in a webflow
 # view-state. Variables ${flowExecutionId} and 
 # ${currentState} must be set in the application context
 # prior to the macro invocation.
 #
 # @param eventId the name of the event
 #-->
<#macro submitParams eventId="submit">
	<input type="hidden" name="_flowExecutionId" value="${flowExecutionId}">
    <input type="hidden" name="_currentStateId" value="${currentStateId}">
    <input type="hidden" name="_eventId" value="${eventId}">
</#macro>

<#--
 # submitForm
 #
 # A macro to render a form that includes standard flow 
 # parameters in a webflow view-state. Same conditions as
 # the submitParams macro apply. The form will receive a
 # name like ${eventId}Form and can be chained to any
 # button that references this name.
 #
 # @param eventId the name of the event
 #-->
<#macro submitForm eventId="submit" action=".">
	<form name="${eventId}Form" method="POST" action="${action}">
		<@submitParams eventId />
    	<#nested/>
    </form>
</#macro>

<#--
 # submitButton
 #
 # A macro to render a button submit button. Button label and image
 # can be customized, and the onclick event triggers a javascript
 # inline function to submit a form named ${eventId}Form.
 #
 # @param eventId the name of the event
 # @param value the button label
 # @param img the image to be rendered in the button
 #-->
<#macro submitButton eventId="submit" value="Concluir" img="img/arrowEW.png">
    <button 
        type="submit" 
        name="_${eventId}" 
        value="${value}" 
        onclick="javascript:document.${eventId}Form.submit()"
        style="margin: 0pt;">
        <table style="border-spacing: 0pt;">
            <tr>
                <td><img src="${img}"/></td>
                <td>${value}</td>
            </tr>
        </table>
    </button>
</#macro>

<#--
 # showInput
 #
 # A macro to show the result aftera formInput macro usage. 
 # A message bundle is required to render the label, where 
 # keys must have the same values used as paths to find objects 
 # placed in the application context.
 # 
 # @param path the path string used to search objects in the 
 #        context and keys in the message bundle.
 # -->
<#macro showInput path>
	<tr>
    	<td><@spring.message path/>: </td>
        <td>${springMacroRequestContext.getBindStatus(path).getValue()}</td>
	</tr>
</#macro>


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
 * The credential save view state.
 *
 * @author Maurício Fernandes de Castro
 * @version $Id$
-->

<#import "../helianto.ftl" as h />
<#import "../spring.ftl" as s />

	<#--
	 # Content line
	 #-->
	<tr style="height: 300px;">

    <#-- 
	 # left side 
	 #-->
	<td style="width: 220px;">
    	Confirme...<br/>
	</td>
    <#-- 
	 # right side 
	 #-->
	<td colspan="2">
		<table><#--
			<@h.showInput "credential.id"/>-->
			<@h.showInput "credential.principal"/>
			<@h.showInput "credential.personalData.firstName"/>
			<@h.showInput "credential.personalData.lastName"/>
	    </table>
	</td>
	
	</tr>
	<#--
	 # Bottom line
	 #-->
	<tr>
    
    <td>
        <hr/>
	    <@h.submitForm "cancel" />
	    <@h.submitButton "cancel", "Cancelar"/>
	</td>
    <td>
        <hr/>
	    <@h.submitForm "finish", "credential.htm" />
	    <@h.submitButton "finish", "Finalizar"/>
    </td>
    <td style="text-align: right;">
        <hr/>
	    <@h.submitForm "back" />
	    <@h.submitButton "back", "Anterior"/>
    </td>

	</tr>


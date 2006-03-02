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
    	Não enviado...<br/>
	</td>
    <#-- 
	 # right side 
	 #-->
	<td colspan="2">
		Houve uma falha no envio do email. Por favor, tente novamente.
	</td>
	
	</tr>
	<#--
	 # Bottom line
	 #-->
	<tr>
    
    <td>
	    <@h.submitForm "restart" />
	    <@h.submitButton "restart", "Início"/>
	</td>
    <td>
	    <@h.submitForm "retry" />
	    <@h.submitButton "retry", "Tentar novamente"/>
	</td>
    <td>
	    <@h.submitForm "cancel" />
	    <@h.submitButton "cancel", "Cancelar"/>
	</td>

	</tr>


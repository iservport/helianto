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
    	Enviado...<br/>
	</td>
    <#-- 
	 # right side 
	 #-->
	<td colspan="2">
		Após receber a mensagem com a confirmação de seus dados,
		por favor, utlize a senha para acessar os serviços desejados.
	</td>
	
	</tr>
	<#--
	 # Bottom line
	 #-->
	<tr>
    
    <td>
	    <@h.submitForm "end" />
	    <@h.submitButton "end", "Fim"/>
	</td>

	</tr>


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
 * The credential form view state.
 *
 * @author Maurício Fernandes de Castro
 * @version $Id$
-->

<#import "../helianto.ftl" as h />
<#import "../spring.ftl" as s />


	<#--
	 # Content line
	 #-->
	<tr>

    <#-- 
	 # left side 
	 #-->
	<td rowspan="2" style="width: 220px;">
      <table>
        <tr>
          <td style="width: 30px;"><img src="img/arrow-small-dn.png"/></td>
          <td>Identificação</td>
        </tr>
      </table>
      <table style="width: 80%;">
        <tr>
          <td>Sua identificação é no sistema é 
          armazenada para possibilitar o controle de acesso 
          e garantir a privacidade dos demais usuários.
          <td/>
        </tr>
        <tr>
          <td>O processo é rápido e requer apenas informações 
          simples.</td>
        </tr>
      </table>
	</td>
    <#-- 
	 # right side 
	 #-->
	<td>
	    <@h.submitForm "save", "credential.htm" >
		<table>
			<@h.formInput "credential.principal", "size=40"/>
			<@h.formInput "credential.personalData.firstName", "size=30" />
			<@h.formInput "credential.personalData.lastName", "size=30" />
            <tr>
    	        <td><@s.showErrors "<br/>"/></td>
            </tr>
	    </table>
	    </@h.submitForm>
	</td>
    <td style="width: 160px;">
      <table>
        <tr>
          <td><img src="img/ask.png"/></td>
          <td><a href="noemail.htm">Não desejo ou não posso fornecer meu e-mail</a></td>
        </tr>
        <tr>
          <td>&#160;</td>
          <td><a href="corpemail.htm">Meu e-mail é corporativo</a></td>
        </tr>
      </table>
    </td>

	</tr>
	<#--
	 # Buttons
	 #-->
	<tr>

    <td>
	    <@h.submitForm "back" />
    	<@h.submitButton "back", "Anterior"/>
    </td>
    <td>
	   	<@h.submitButton "save", "Próximo"/>
   	</td>

	</tr>
	<#--
	 # Bottom line
	 #-->
	<tr>
    
    <td>
    	<br/>
	    <@h.submitForm "cancel" />
	    <@h.submitButton "cancel", "Cancelar"/>
	</td>

	</tr>


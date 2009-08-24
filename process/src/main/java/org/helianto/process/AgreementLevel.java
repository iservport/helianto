/* Copyright 2005 I Serv Consultoria Empresarial Ltda.
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
 */


package org.helianto.process;

/**
 * Descreve n�veis de cr�dito atribu�dos a
 * um cliente durante o or�amento.
 * 
 * @author Mauricio Fernandes de Castro
 */
public enum AgreementLevel {
	
	/**
	 * Requer avalia��o de cr�dito a cada ordem.
	 */
	REQUIRE_CREDIT_ASSESSMENT('R'),
	/**
	 * Dispensa avalia��o de cr�dito.
	 */
	ALLWAYS_GRANT_CREDIT('G');
	
	private AgreementLevel(char value) {
		this.value = value;
	}
	
	private char value;
	
	public char getValue() {
		return this.value;
	}

}

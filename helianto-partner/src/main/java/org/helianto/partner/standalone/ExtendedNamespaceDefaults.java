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


package org.helianto.partner.standalone;

import org.helianto.core.standalone.NamespaceDefaults;
import org.helianto.partner.domain.Division;

/**
 * Extends <code>NamespaceDefaults</code> to hold additional defaults in a context bean to hide 
 * the isolation complexity from developers building a stand-alone application.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class ExtendedNamespaceDefaults extends NamespaceDefaults {

	private Division defaultDivision;
	
	/**
	 * Default division.
	 */
	public Division getDefaultDivision() {
		return defaultDivision;
	}
	public void setDefaultDivision(Division defaultDivision) {
		this.defaultDivision = defaultDivision;
	}
}

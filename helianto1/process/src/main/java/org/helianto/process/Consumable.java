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

import javax.persistence.Table;

/**
 * Materials or components to be consumed during production or service.
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="proc_cons")
public class Consumable extends DerivedProcessDocument {

	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor
	 */
	public Consumable() {
		super();
	}
	
	@Override
	public DocumentAssociation documentAssociationFactory(int sequence) {
		return null;
	}

}

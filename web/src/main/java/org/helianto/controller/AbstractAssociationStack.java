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

package org.helianto.controller;

import org.helianto.core.AbstractAssociation;

/**
 * Extends simple stack to hold associations.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class AbstractAssociationStack<P, C> extends AbstractTargetStack<AbstractAssociation<P, C>> {

	private static final long serialVersionUID = 1L;
	private P rootInstance;
	
	/**
	 * Default constructor.
	 */
	public AbstractAssociationStack() {
		super();
	}

	/**
	 * Hold the parent of the first instance.
	 */
	public P getRootInstance() {
		return rootInstance;
	}
	public void setRootInstance(P rootInstance) {
		this.rootInstance = rootInstance;
	}

}

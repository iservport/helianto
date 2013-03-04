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


package org.helianto.core;

import org.helianto.core.domain.Identity;

/**
 * Fired if the same principal has already been persisted before.
 *  
 * @author Mauricio Fernandes de Castro
 */
public class DuplicateIdentityException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private Identity identity;
	
	/**
	 * Empty constructor.
	 */
	public DuplicateIdentityException() {
		super();
	}

	/**
	 * Identity constructor.
	 * 
	 * @param identity the rejected identity.
	 */
	public DuplicateIdentityException(Identity identity) {
		super();
		this.identity = identity;
	}
	
	/**
	 * Identity message constructor.
	 * 
	 * @param identity the rejected identity.
	 * @param message
	 */
	public DuplicateIdentityException(Identity identity, String message) {
		super(message);
		this.identity = identity;
	}
	
	/**
	 * The rejected identity.
	 */
	public Identity getIdentity() {
		return identity;
	}

}

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

package org.helianto.core.service;

import org.helianto.core.Sequenceable;

/**
 * A service to create sequences.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface SequenceMgr {
	
	/**
	 * Inspect the <code>internalNumber</code> attribute of any class
	 * implementing the <code>Sequenceable</code> interface and
	 * assign an appropriate value to it, based on the <code>internalNumberKey</code>
	 * attribute, in collaboration with the <code>InternalEnumerator</code> class.
	 * 
	 * @param sequenceable
	 */
	public void validateInternalNumber(Sequenceable sequenceable);
	
}

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

import java.util.List;

import org.helianto.core.Entity;
import org.helianto.core.Node;
import org.helianto.core.Operator;
import org.helianto.core.Sequenceable;

/**
 * A service to create sequences and trees.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface SequenceMgr {
	
	/**
	 * Create a new public number from a sequence named after a given public 
	 * number key. If the sequence does not exist, start a new one.
	 * 
	 * @param operator
	 * @param publicNumberKey
	 */
	public long newPublicNumber(Operator operator, String publicNumberKey);
	
	/**
	 * Create a new internal number from a sequence named after a given internal 
	 * number key. If the sequence does not exist, start a new one.
	 * 
	 * @param entity
	 * @param internalNumberKey
	 */
	public long newInternalNumber(Entity entity, String internalNumberKey);
	
	/**
	 * Inspect the <code>internalNumber</code> attribute of any class
	 * implementing the <code>Sequenceable</code> interface and
	 * assign an appropriate value to it, based on the <code>internalNumberKey</code>
	 * attribute, in collaboration with the <code>InternalEnumerator</code> class.
	 * 
	 * @param sequenceable
	 */
	public void validateInternalNumber(Sequenceable sequenceable);
	
	/**
	 * Create a tree.
	 * 
	 * @param root
	 */
	public List<Node> prepareTree(Node root);
	
}

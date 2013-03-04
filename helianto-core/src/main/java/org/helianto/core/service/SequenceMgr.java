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

import org.helianto.core.Node;
import org.helianto.core.domain.Entity;
import org.helianto.core.domain.Operator;
import org.helianto.core.domain.PrivateSequence;
import org.helianto.core.form.PrivateSequenceForm;
import org.helianto.core.number.Numerable;
import org.helianto.core.number.Sequenceable;
import org.helianto.core.number.Verifiable;

/**
 * A service to create sequences and trees.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface SequenceMgr {
	
	/**
	 * Find a public number from a sequence named after a given public 
	 * number key.
	 * 
	 * @param operator
	 * @param publicNumberKey
	 */
	long findOrCreatePublicNumber(Operator operator, String publicNumberKey);
	
	/**
	 * Create a new public number from a sequence named after a given public 
	 * number key. If the sequence does not exist, start a new one.
	 * 
	 * @param operator
	 * @param publicNumberKey
	 */
	long findNewPublicNumber(Operator operator, String publicNumberKey);
	
	/**
	 * Find an internal number from a sequence named after a given internal 
	 * number key.
	 * 
	 * @param entity
	 * @param internalNumberKey
	 * @param startNumber
	 */
	long findOrCreateInternalNumber(Entity entity, String internalNumberKey, int startNumber);
	
	/**
	 * Find an internal number from a sequence named after a given internal 
	 * number key.
	 * 
	 * @param entity
	 * @param internalNumberKey
	 */
	long findOrCreateInternalNumber(Entity entity, String internalNumberKey);
	
	/**
	 * Find private sequences.
	 * 
	 * @param form
	 */
	List<PrivateSequence> findPrivateSequences(PrivateSequenceForm form);
	
	/**
	 * Store private sequences.
	 * 
	 * @param privateSequence
	 */
	PrivateSequence storePrivateSequence(PrivateSequence privateSequence);
	
	/**
	 * Create a new internal number from a sequence named after a given internal 
	 * number key. If the sequence does not exist, start a new one from startNumber.
	 * 
	 * @param entity
	 * @param internalNumberKey
	 * @param startNumber
	 */
	long newInternalNumber(Entity entity, String internalNumberKey, int startNumber);
	
	/**
	 * Create a new internal number from a sequence named after a given internal 
	 * number key. If the sequence does not exist, start a new one from 1.
	 * 
	 * @param entity
	 * @param internalNumberKey
	 */
	long newInternalNumber(Entity entity, String internalNumberKey);
	
	/**
	 * Inspect the <code>publicNumber</code> attribute of any class
	 * implementing the <code>Numerable</code> interface and
	 * assign an appropriate value to it, based on the <code>publicNumberKey</code>
	 * attribute, in collaboration with the <code>PublicEnumerator</code> class.
	 * 
	 * @param sequenceable
	 */
	void validatePublicNumber(Numerable numerable);
	
	/**
	 * Inspect the <code>internalNumber</code> attribute of any class
	 * implementing the <code>Sequenceable</code> interface and
	 * assign an appropriate value to it, based on the <code>internalNumberKey</code>
	 * attribute, in collaboration with the <code>InternalEnumerator</code> class.
	 * 
	 * @param sequenceable
	 */
	void validateInternalNumber(Sequenceable sequenceable);
	
	/**
	 * Use reflection to inspect the <code>verifiable</code> parameter and, if it implements
	 * either <code>Sequenceable</code> or <code>Numerable</code>, generates a digit for it.
	 * 
	 * @param verifiable
	 */
	void generateVerificationDigit(Verifiable verifiable);
	
	/**
	 * Create a tree.
	 * 
	 * @param root
	 */
	List<Node> prepareTree(Node root);
	
}

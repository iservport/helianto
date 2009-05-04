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

import junit.framework.TestCase;

import org.helianto.core.Entity;
import org.helianto.core.test.EntityTestSupport;

/**
 * @author Mauricio Fernandes de Castro
 */
public class ProcessTests extends TestCase {
	
    public void testEquals() {
    	Entity entity = new Entity();

    	Process process = new Process(), copy = new Process();
    	ProcessDocument parent = new ProcessDocument() {
			private static final long serialVersionUID = 1L;
			public ProcessDocumentAssociation documentAssociationFactory(int sequence) {
				return null;
			}
    	};

        assertFalse(process.setKey(entity, "CODE")  .equals(parent.setKey(entity, "CODE")));
        assertTrue (process.setKey(null, null)      .equals(copy.setKey(null, null)));
        assertTrue (process.setKey(null, "")        .equals(copy.setKey(null, "")));
        assertFalse(process.setKey(null, "")        .equals(copy.setKey(null, "CODE")));
        assertFalse(process.setKey(null, "CODE")    .equals(copy.setKey(null, "")));
        assertTrue (process.setKey(entity, null)    .equals(copy.setKey(entity, null)));
        assertTrue (process.setKey(entity, "")      .equals(copy.setKey(entity, "")));
        assertFalse(process.setKey(entity, "CODE")  .equals(copy.setKey(entity, "")));
        assertFalse(process.setKey(entity, "")      .equals(copy.setKey(entity, "CODE")));
        assertTrue (process.setKey(entity, "CODE")  .equals(copy.setKey(entity, "CODE")));

    }
    
	public void testProcessFactory() {
		Entity entity = EntityTestSupport.createEntity();
		String processCode = "PROCESSCODE";
		long internalNumber = Long.MAX_VALUE;
		Process process = Process.processFactory(entity, processCode, internalNumber);
		assertSame(entity, process.getEntity());
		assertEquals(processCode, process.getDocCode());
		assertEquals(internalNumber, process.getInternalNumber());
	}

}

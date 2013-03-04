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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.helianto.core.domain.Entity;
import org.helianto.core.test.EntityTestSupport;
import org.helianto.process.domain.DerivedProcessDocument;
import org.helianto.process.domain.Process;
import org.junit.Test;

/**
 * @author Mauricio Fernandes de Castro
 */
public class ProcessTests {
	
	@Test
	public void constructor() {
		Entity entity = EntityTestSupport.createEntity();
		Process process = new Process(entity, "PROCESSCODE");
		assertSame(entity, process.getEntity());
		assertEquals("PROCESSCODE", process.getDocCode());
	}

	@SuppressWarnings("serial")
	@Test
    public void processTestsEquals() {
		Process document = new Process(null, null);
		assertFalse(document.equals(null));
		
		Process other = new Process(null, null);
		assertTrue(document.equals(other));
		
		Entity entity = EntityTestSupport.createEntity();
		document.setEntity(entity);
		assertFalse(document.equals(other));
		document.setDocCode("CODE");
		assertFalse(document.equals(other));
		other.setEntity(entity);
		assertFalse(document.equals(other));
		other.setDocCode("CODE");
		assertTrue(document.equals(other));
		assertEquals(document.hashCode(), other.hashCode());
		document.setDocCode("");
		assertFalse(document.equals(other));
		document.setEntity(new Entity());
		assertFalse(document.equals(other));

		DerivedProcessDocument ancestor = new DerivedProcessDocument(entity, "CODE") { };
		assertFalse(document.equals(ancestor));
    }
    
}

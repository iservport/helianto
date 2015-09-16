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

package org.helianto.document.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;

import org.helianto.core.domain.Entity;
import org.helianto.core.test.EntityTestSupport;
import org.junit.Test;


/**
 * 
 * @author Mauricio Fernandes de Castro
 */
public class DocumentFolderTests {
	
	@Test
	public void constructor() {
		DocumentFolder docBuilder = new DocumentFolder();
		assertTrue(docBuilder instanceof Serializable);
	}
	
	@Test
	public void builderEquals() {
		DocumentFolder docBuilder = new DocumentFolder();
		assertFalse(docBuilder.equals(null));
		DocumentFolder other = new DocumentFolder();
		assertTrue(docBuilder.equals(other));
		
		Entity entity = EntityTestSupport.createEntity();
		docBuilder.setEntity(entity);
		docBuilder.setFolderCode("CODE");

		assertFalse(docBuilder.equals(other));
		other.setEntity(entity);
		assertFalse(docBuilder.equals(other));
		other.setFolderCode("CODE");
		assertTrue(docBuilder.equals(other));
		assertEquals(docBuilder.hashCode(), other.hashCode());
		docBuilder.setEntity(new Entity());
		assertFalse(docBuilder.equals(other));
		docBuilder.setFolderCode("AAA");
		assertFalse(docBuilder.equals(other));		
	}
	
}

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

package org.helianto.document;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.helianto.core.AbstractKeyStringValue;
import org.helianto.core.KeyType;
import org.junit.Test;


/**
 * 
 * @author Mauricio Fernandes de Castro
 */
public class DocumentKeyTests {
	
	@Test
	public void constructor() {
		DocumentKey documentKey = new DocumentKey();
		assertTrue(documentKey instanceof AbstractKeyStringValue);
	}
	
	@Test
	public void compareTo() {
		KeyType kt1 = new KeyType().setKeyCode("1");
		KeyType kt2 = new KeyType().setKeyCode("2");
		KeyType kt3 = new KeyType().setKeyCode("2");
		Document document = new Document();
		
		DocumentKey key1 = DocumentKey.documentKeyFactory(document, kt1);
		DocumentKey key2 = DocumentKey.documentKeyFactory(document, kt2);
		DocumentKey key3 = DocumentKey.documentKeyFactory(document, kt3);
		
		assertEquals(-1, key1.compareTo(key2));
		assertEquals(1, key2.compareTo(key1));
		assertEquals(0, key2.compareTo(key3));
	}
	
	@SuppressWarnings("serial")
	@Test
	public void equalsDocumentKey() {
		KeyType keyType = new KeyType().setKeyCode("1");
		final Document document = new Document();
		DocumentKey documentKey = DocumentKey.documentKeyFactory(document, keyType);
		AbstractKeyStringValue other = new AbstractKeyStringValue() {
			@Override protected Object getKeyOwner() {
				return document;
			}
		};
		other.setKeyValue("1");
		assertFalse(documentKey.equals(other));
	}

}

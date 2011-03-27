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
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.helianto.document.base.AbstractTag;
import org.junit.Test;


/**
 * 
 * @author Mauricio Fernandes de Castro
 */
public class DocumentTagTests {
	
	@Test
	public void constructor() {
		DocumentTag documentTag = new DocumentTag();
		assertTrue(documentTag instanceof AbstractTag);
	}
	
	@Test
	public void factory() {
		Document document = new Document();
		DocumentTag documentTag = DocumentTag.tagFactory(document);
		assertSame(document, documentTag.getDocument());
	}
	
	@Test
	public void compare() {
		DocumentTag dt1 = new DocumentTag();
		dt1.setTagCode("1");
		DocumentTag dt2 = new DocumentTag();
		dt2.setTagCode("2");
		DocumentTag dt3 = new DocumentTag();
		dt3.setTagCode("2");
		
		assertEquals(-1, dt1.compareTo(dt2));
		assertEquals(1, dt2.compareTo(dt1));
		assertEquals(0, dt2.compareTo(dt3));
	}
	
	@Test
	public void equalsDocumentTag() {
		DocumentTag documentTag = new DocumentTag();
		DocumentTag other = new DocumentTag();
		assertFalse(documentTag.equals(null));
		assertTrue(documentTag.equals(other));
		
		Document document = new Document();
		documentTag.setDocument(document);
		documentTag.setTagCode("1");
		assertFalse(documentTag.equals(other));
		other.setDocument(document);
		assertFalse(documentTag.equals(other));
		other.setTagCode("1");
		assertTrue(documentTag.equals(other));
		assertEquals(documentTag.hashCode(), other.hashCode());
		documentTag.setTagCode("2");
		assertFalse(documentTag.equals(other));
		documentTag.setDocument(new Document());
		assertFalse(documentTag.equals(other));
	}

}

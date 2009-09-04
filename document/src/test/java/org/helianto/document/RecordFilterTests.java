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
import static org.junit.Assert.assertTrue;

import org.helianto.document.AbstractEventFilter;
import org.helianto.document.AbstractRecordFilter;
import org.junit.Before;
import org.junit.Test;


/**
 * 
 * @author Mauricio Fernandes de Castro
 */
public class RecordFilterTests {

	@Test
	public void constructor() {
		assertTrue(recordFilter instanceof AbstractEventFilter);
	}
	
	@Test
	public void emptyFilter() {
		assertEquals("", recordFilter.createCriteriaAsString(false));
	}
	
	@Test
	public void resolutionFilter() {
		recordFilter.setResolution('A');
		assertEquals("alias.resolution = 'A' ", recordFilter.createCriteriaAsString(false));
	}
	
	private AbstractRecordFilter recordFilter;
	
	@Before
	public void setUp() {
		recordFilter = new AbstractRecordFilter() {
			private static final long serialVersionUID = 1L;
			public String getObjectAlias() { return "alias"; }
		};
	}

}
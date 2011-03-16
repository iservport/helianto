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

import java.util.Date;

import org.helianto.core.Entity;
import org.helianto.core.PrivacyLevel;
import org.helianto.core.test.EntityTestSupport;
import org.helianto.document.AbstractJournal;
import org.helianto.document.Resolution;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class JournalTests {
	
	@Test
	public void defaultConstructor() {
		Date issueDate = new Date();
		journal.setIssueDate(issueDate);
    	assertEquals("SUMMARY", journal.getSummary());
    	assertEquals(Resolution.PRELIMINARY.getValue(), journal.getResolution());
    	assertEquals(PrivacyLevel.PUBLIC.getValue(), journal.getPrivacyLevel());
    	assertEquals(0, journal.getPriority());
		assertTrue(journal.getNextCheckDate() instanceof Date);
	}

	@Test
	public void actualDuration() {
		journal.setActualStartDate(new Date(1000000));
		journal.setActualEndDate(new Date(2000000));

		journal.setActualStartDate(null);
		journal.setActualEndDate(null);
		// em andamento ou suspensa usa data programada de fim e verdadeira no in�cio
		journal.setActualStartDate(new Date(500000));
		// demais casos usa datas verdadeiras
		journal.setActualEndDate(new Date(2500000));
		assertEquals(2000000, journal.getActualDuration());
	}
	
	private AbstractJournal journal;
	
	@Before
	public void setUp() {
		journal = new EventStub();
		Entity entity = EntityTestSupport.createEntity();
		journal.setEntity(entity);
	}
	
	@SuppressWarnings("serial")
	public class EventStub extends AbstractJournal {
		@Override
		public String getSummary() { return "SUMMARY"; }
		public String getInternalNumberKey() { return null; }
	}

}
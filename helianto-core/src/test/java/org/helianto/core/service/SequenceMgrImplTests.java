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

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.isA;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.reset;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;

import org.helianto.core.Entity;
import org.helianto.core.EntityFilter;
import org.helianto.core.InternalEnumerator;
import org.helianto.core.Sequenceable;
import org.helianto.core.repository.BasicDao;
import org.helianto.core.repository.FilterDao;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SequenceMgrImplTests {
    
    private SequenceMgrImpl sequenceMgr;
    private Entity entity = new Entity(), managedEntity = new Entity();

    @Test
    public void validateInternalNumberZeroNotNull() {
    	Sequenceable sequenceable = new SequenceableStub();
    	InternalEnumerator enumerator = new InternalEnumerator();
    	enumerator.setLastNumber(1000);
    	
    	expect(entityDao.merge(entity)).andReturn(managedEntity);
    	replay(entityDao);
    	expect(internalEnumeratorDao.findUnique(managedEntity, "TEST")).andReturn(enumerator);
    	internalEnumeratorDao.persist(enumerator);
    	replay(internalEnumeratorDao);
    	
    	sequenceMgr.validateInternalNumber(sequenceable);
    	assertEquals(1001, enumerator.getLastNumber());
    	assertEquals(1000, sequenceable.getInternalNumber());
    	verify(internalEnumeratorDao);
    	verify(entityDao);
    }
    
    /**
     * Sequenceable internalNumber not zero means the internal number should not change.
     */
    @Test
    public void validateInternalNumberNotZero() {
    	Sequenceable sequenceable = new SequenceableStub();
    	sequenceable.setInternalNumber(2000);

    	replay(internalEnumeratorDao);
    	
    	sequenceMgr.validateInternalNumber(sequenceable);
    	assertEquals(2000, sequenceable.getInternalNumber());
    	verify(internalEnumeratorDao);
    }
    
    /**
     * Create a new Enumerator instance.
     */
    @Test
    public void validateInternalNumberNull() {
    	Sequenceable sequenceable = new SequenceableStub();
    	InternalEnumerator enumerator = null, managedEnumerator = new InternalEnumerator();
    	
    	expect(entityDao.merge(entity)).andReturn(managedEntity);
    	replay(entityDao);
    	expect(internalEnumeratorDao.findUnique(managedEntity, "TEST")).andReturn(enumerator);
    	expect(internalEnumeratorDao.merge(isA(InternalEnumerator.class))).andReturn(managedEnumerator);
    	replay(internalEnumeratorDao);
    	
    	sequenceMgr.validateInternalNumber(sequenceable);
    	assertEquals(1, sequenceable.getInternalNumber());
    	verify(internalEnumeratorDao);
    }
    
    private BasicDao<InternalEnumerator> internalEnumeratorDao;
    private FilterDao<Entity, EntityFilter> entityDao;
    
    @SuppressWarnings("unchecked")
	@Before
    public void setUp() {
        sequenceMgr = new SequenceMgrImpl();
        internalEnumeratorDao = createMock(BasicDao.class);
        entityDao = createMock(FilterDao.class);
        sequenceMgr.setInternalEnumeratorDao(internalEnumeratorDao);
        sequenceMgr.setEntityDao(entityDao);
    }
    
    @After
    public void tearDown() {
        reset(internalEnumeratorDao);
        reset(entityDao);
    }
    
    public class SequenceableStub implements Sequenceable {
    	
    	private long internalNumber = 0;

		public Entity getEntity() {
			return entity;
		}

		public void setInternalNumber(long internalNumber) {
			this.internalNumber = internalNumber;
		}
		public long getInternalNumber() {
			return this.internalNumber;
		}

		public String getInternalNumberKey() {
			return "TEST";
		}

    }
    
}

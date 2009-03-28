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
import junit.framework.TestCase;

import org.helianto.core.Entity;
import org.helianto.core.EntityFilter;
import org.helianto.core.InternalEnumerator;
import org.helianto.core.Sequenceable;
import org.helianto.core.dao.FilterDao;
import org.helianto.core.dao.InternalEnumeratorDao;

public class SequenceMgrImplTests extends TestCase {
    
    private SequenceMgrImpl sequenceMgr;
    private Entity entity = new Entity(), managedEntity = new Entity();
    
    public void testValidateInternalNumberZeroNotNull() {
    	Sequenceable sequenceable = new SequenceableStub();
    	InternalEnumerator enumerator = new InternalEnumerator();
    	enumerator.setLastNumber(1000);
    	
    	expect(entityDao.merge(entity)).andReturn(managedEntity);
    	replay(entityDao);
    	expect(internalEnumeratorDao.findInternalEnumeratorByNaturalId(managedEntity, "TEST")).andReturn(enumerator);
    	internalEnumeratorDao.persistInternalEnumerator(enumerator);
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
    public void testValidateInternalNumberNotZero() {
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
    public void testValidateInternalNumberNull() {
    	Sequenceable sequenceable = new SequenceableStub();
    	InternalEnumerator enumerator = null, managedEnumerator = new InternalEnumerator();
    	
    	expect(entityDao.merge(entity)).andReturn(managedEntity);
    	replay(entityDao);
    	expect(internalEnumeratorDao.findInternalEnumeratorByNaturalId(managedEntity, "TEST")).andReturn(enumerator);
    	expect(internalEnumeratorDao.mergeInternalEnumerator(isA(InternalEnumerator.class))).andReturn(managedEnumerator);
    	replay(internalEnumeratorDao);
    	
    	sequenceMgr.validateInternalNumber(sequenceable);
    	assertEquals(1, sequenceable.getInternalNumber());
    	verify(internalEnumeratorDao);
    }
    
    private InternalEnumeratorDao internalEnumeratorDao;
    private FilterDao<Entity, EntityFilter> entityDao;
    
    @SuppressWarnings("unchecked")
	@Override
    public void setUp() {
        sequenceMgr = new SequenceMgrImpl();
        internalEnumeratorDao = createMock(InternalEnumeratorDao.class);
        entityDao = createMock(FilterDao.class);
        sequenceMgr.setInternalEnumeratorDao(internalEnumeratorDao);
        sequenceMgr.setEntityDao(entityDao);
    }
    
    @Override
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

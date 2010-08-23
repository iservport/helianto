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
import org.helianto.core.InternalEnumerator;
import org.helianto.core.Operator;
import org.helianto.core.PublicEnumerator;
import org.helianto.core.number.DigitGenerationStrategy;
import org.helianto.core.number.Numerable;
import org.helianto.core.number.Sequenceable;
import org.helianto.core.number.Verifiable;
import org.helianto.core.repository.BasicDao;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SequenceMgrImplTests {
    
    private SequenceMgrImpl sequenceMgr;
    private Operator operator = new Operator();
    private Entity entity = new Entity();

    @Test
    public void validatePublicNumberZeroNotNull() {
    	Numerable numerable = new NumerableStub();
    	PublicEnumerator enumerator = new PublicEnumerator();
    	enumerator.setLastNumber(1000);
    	
    	expect(publicEnumeratorDao.findUnique(operator, "PUBLIC")).andReturn(enumerator);
    	publicEnumeratorDao.saveOrUpdate(enumerator);
    	replay(publicEnumeratorDao);
    	
    	sequenceMgr.validatePublicNumber(numerable);
    	assertEquals(1001, enumerator.getLastNumber());
    	assertEquals(1000, numerable.getPublicNumber());
    	verify(publicEnumeratorDao);
    }
    
    @Test
    public void validateInternalNumberZeroNotNull() {
    	Sequenceable sequenceable = new SequenceableStub();
    	InternalEnumerator enumerator = new InternalEnumerator();
    	enumerator.setLastNumber(1000);
    	
    	expect(internalEnumeratorDao.findUnique(entity, "INTERNAL")).andReturn(enumerator);
    	internalEnumeratorDao.saveOrUpdate(enumerator);
    	replay(internalEnumeratorDao);
    	
    	sequenceMgr.validateInternalNumber(sequenceable);
    	assertEquals(1001, enumerator.getLastNumber());
    	assertEquals(1000, sequenceable.getInternalNumber());
    	verify(internalEnumeratorDao);
    }
    
    /**
     * Numerable publicNumber not zero means the public number should not change.
     */
    @Test
    public void validatePublicNumberNotZero() {
    	Numerable numerable = new NumerableStub();
    	numerable.setPublicNumber(2000);

    	replay(publicEnumeratorDao);
    	
    	sequenceMgr.validatePublicNumber(numerable);
    	assertEquals(2000, numerable.getPublicNumber());
    	verify(publicEnumeratorDao);
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
    public void validatePublicNumberNull() {
    	Numerable numerable = new NumerableStub();
    	PublicEnumerator enumerator = null;
    	
    	expect(publicEnumeratorDao.findUnique(operator, "PUBLIC")).andReturn(enumerator);
    	publicEnumeratorDao.saveOrUpdate(isA(PublicEnumerator.class));
    	replay(publicEnumeratorDao);
    	
    	sequenceMgr.validatePublicNumber(numerable);
    	assertEquals(1, numerable.getPublicNumber());
    	verify(publicEnumeratorDao);
    }
    
    /**
     * Create a new Enumerator instance.
     */
    @Test
    public void validateInternalNumberNull() {
    	Sequenceable sequenceable = new SequenceableStub();
    	InternalEnumerator enumerator = null;
    	
    	expect(internalEnumeratorDao.findUnique(entity, "INTERNAL")).andReturn(enumerator);
    	internalEnumeratorDao.saveOrUpdate(isA(InternalEnumerator.class));
    	replay(internalEnumeratorDao);
    	
    	sequenceMgr.validateInternalNumber(sequenceable);
    	assertEquals(1, sequenceable.getInternalNumber());
    	verify(internalEnumeratorDao);
    }
    
    /**
     * Generate a verification digit.
     */
    @Test
    public void generateVerificationDigit() {
    	SequenceableVerifiableStub sequenceable = new SequenceableVerifiableStub();
    	
    	expect(digitGenerationStrategy.generateDigit(Long.MAX_VALUE)).andReturn(Integer.MAX_VALUE);
    	replay(digitGenerationStrategy);
    	
    	sequenceMgr.generateVerificationDigit(sequenceable);
    	assertEquals(Integer.MAX_VALUE, sequenceable.getVerificationDigit());
    	verify(digitGenerationStrategy);
    }
    
    
    
    private BasicDao<InternalEnumerator> internalEnumeratorDao;
    private BasicDao<PublicEnumerator> publicEnumeratorDao;
    private DigitGenerationStrategy digitGenerationStrategy;
    
    @SuppressWarnings("unchecked")
	@Before
    public void setUp() {
        sequenceMgr = new SequenceMgrImpl();
        internalEnumeratorDao = createMock(BasicDao.class);
        publicEnumeratorDao = createMock(BasicDao.class);
        digitGenerationStrategy = createMock(DigitGenerationStrategy.class);
        sequenceMgr.setInternalEnumeratorDao(internalEnumeratorDao);
        sequenceMgr.setPublicEnumeratorDao(publicEnumeratorDao);
        sequenceMgr.setDigitGenerationStrategy(digitGenerationStrategy);
    }
    
    @After
    public void tearDown() {
        reset(internalEnumeratorDao);
        reset(publicEnumeratorDao);
        reset(digitGenerationStrategy);
    }
    
    public class NumerableStub implements Numerable {
    	
    	private long publicNumber = 0;

		public Operator getOperator() {
			return operator;
		}

		public void setPublicNumber(long publicNumber) {
			this.publicNumber = publicNumber;
		}
		public long getPublicNumber() {
			return this.publicNumber;
		}

		public String getPublicNumberKey() {
			return "PUBLIC";
		}

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
			return "INTERNAL";
		}

    }
    
    public class SequenceableVerifiableStub implements Sequenceable, Verifiable {
    	
    	private long internalNumber = Long.MAX_VALUE;
    	private int digit = 0;

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
			return "INTERNAL";
		}

		public int getVerificationDigit() {
			return digit;
		}

		public void setVerificationDigit(int digit) {
			this.digit = digit;
		}

    }
    
}

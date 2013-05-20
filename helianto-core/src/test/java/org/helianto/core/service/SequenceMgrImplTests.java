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

import javax.persistence.Transient;

import org.easymock.EasyMock;
import org.helianto.core.domain.Entity;
import org.helianto.core.domain.Operator;
import org.helianto.core.domain.PrivateSequence;
import org.helianto.core.domain.PublicSequence;
import org.helianto.core.number.DigitGenerationStrategy;
import org.helianto.core.number.Numerable;
import org.helianto.core.number.Sequenceable;
import org.helianto.core.number.Verifiable;
import org.helianto.core.repository.PrivateSequenceRepository;
import org.helianto.core.repository.PublicSequenceRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class SequenceMgrImplTests {
    
    private SequenceMgrImpl sequenceMgr;
    private Operator operator = new Operator();
    private Entity entity = new Entity();
    
    @Test
    public void findOrCreatePublicNumber() {
    	PublicSequence enumerator = new PublicSequence();
    	enumerator.setLastNumber(1000);
    	
    	expect(publicSequenceRepository.findByOperatorAndTypeName(operator, "PUBLIC")).andReturn(enumerator);
    	replay(publicSequenceRepository);
    	
    	assertEquals(1000, sequenceMgr.findOrCreatePublicNumber(operator, "PUBLIC"));
    	verify(publicSequenceRepository);
    }

    @Test
    public void findOrCreatePublicNumberNull() {
    	expect(publicSequenceRepository.findByOperatorAndTypeName(operator, "PUBLIC")).andReturn(null);
    	expect(publicSequenceRepository.save(EasyMock.isA(PublicSequence.class))).andReturn(null);
    	replay(publicSequenceRepository);
    	
    	assertEquals(1, sequenceMgr.findOrCreatePublicNumber(operator, "PUBLIC"));
    	verify(publicSequenceRepository);
    }

    @Test
    public void validatePublicNumberZeroNotNull() {
    	Numerable numerable = new NumerableStub();
    	PublicSequence enumerator = new PublicSequence();
    	enumerator.setLastNumber(1000);
    	
    	expect(publicSequenceRepository.findByOperatorAndTypeName(operator, "PUBLIC")).andReturn(enumerator);
    	expect(publicSequenceRepository.save(EasyMock.isA(PublicSequence.class))).andReturn(enumerator);
    	replay(publicSequenceRepository);
    	
    	sequenceMgr.validatePublicNumber(numerable);
    	assertEquals(1001, enumerator.getLastNumber());
    	assertEquals(1000, numerable.getPublicNumber());
    	verify(publicSequenceRepository);
    }
    
    @Test
    public void findOrCreateInternalNumber() {
    	PrivateSequence enumerator = new PrivateSequence();
    	enumerator.setLastNumber(1000);
    	
    	expect(privateSequenceRepository.findByEntityAndTypeName(entity, "INTERNAL")).andReturn(enumerator);
    	replay(privateSequenceRepository);
    	
    	assertEquals(1000, sequenceMgr.findOrCreateInternalNumber(entity, "INTERNAL"));
    	verify(privateSequenceRepository);
    }

    @Test
    public void findOrCreateInternalNumberNull() {
    	expect(privateSequenceRepository.findByEntityAndTypeName(entity, "INTERNAL")).andReturn(null);
    	expect(privateSequenceRepository.save(EasyMock.isA(PrivateSequence.class))).andReturn(new PrivateSequence());
    	replay(privateSequenceRepository);
    	
    	assertEquals(1, sequenceMgr.findOrCreateInternalNumber(entity, "INTERNAL"));
    	verify(privateSequenceRepository);
    }

    @Test
    public void validateInternalNumberZeroNotNull() {
    	Sequenceable sequenceable = new SequenceableStub();
    	PrivateSequence enumerator = new PrivateSequence();
    	enumerator.setLastNumber(1000);
    	
    	expect(privateSequenceRepository.findByEntityAndTypeName(entity, "INTERNAL")).andReturn(enumerator);
    	expect(privateSequenceRepository.save(enumerator)).andReturn(enumerator);
    	replay(privateSequenceRepository);
    	
    	sequenceMgr.validateInternalNumber(sequenceable);
    	assertEquals(1001, enumerator.getLastNumber());
    	assertEquals(1000, sequenceable.getInternalNumber());
    	verify(privateSequenceRepository);
    }
    
    /**
     * Numerable publicNumber not zero means the public number should not change.
     */
    @Test
    public void validatePublicNumberNotZero() {
    	Numerable numerable = new NumerableStub();
    	numerable.setPublicNumber(2000);

    	replay(publicSequenceRepository);
    	
    	sequenceMgr.validatePublicNumber(numerable);
    	assertEquals(2000, numerable.getPublicNumber());
    	verify(publicSequenceRepository);
    }
    
    /**
     * Sequenceable internalNumber not zero means the internal number should not change.
     */
    @Test
    public void validateInternalNumberNotZero() {
    	Sequenceable sequenceable = new SequenceableStub();
    	sequenceable.setInternalNumber(2000);

    	replay(privateSequenceRepository);
    	
    	sequenceMgr.validateInternalNumber(sequenceable);
    	assertEquals(2000, sequenceable.getInternalNumber());
    	verify(privateSequenceRepository);
    }
    
    /**
     * Create a new Enumerator instance.
     */
    @Test
    public void validatePublicNumberNull() {
    	Numerable numerable = new NumerableStub();
    	PublicSequence enumerator = null;
    	
    	expect(publicSequenceRepository.findByOperatorAndTypeName(operator, "PUBLIC")).andReturn(enumerator);
    	expect(publicSequenceRepository.save(EasyMock.isA(PublicSequence.class))).andReturn(enumerator);
    	replay(publicSequenceRepository);
    	
    	sequenceMgr.validatePublicNumber(numerable);
    	assertEquals(1, numerable.getPublicNumber());
    	verify(publicSequenceRepository);
    }
    
    /**
     * Create a new Enumerator instance.
     */
    @Test
    public void validateInternalNumberNull() {
    	Sequenceable sequenceable = new SequenceableStub();
    	PrivateSequence privateSequence = null;
    	
    	expect(privateSequenceRepository.findByEntityAndTypeName(entity, "INTERNAL")).andReturn(privateSequence);
    	expect(privateSequenceRepository.save(isA(PrivateSequence.class))).andReturn(privateSequence);
    	replay(privateSequenceRepository);
    	
    	sequenceMgr.validateInternalNumber(sequenceable);
    	assertEquals(1, sequenceable.getInternalNumber());
    	verify(privateSequenceRepository);
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
    
    private PrivateSequenceRepository privateSequenceRepository;
    private PublicSequenceRepository publicSequenceRepository;
    private DigitGenerationStrategy digitGenerationStrategy;
    
	@Before
    public void setUp() {
        sequenceMgr = new SequenceMgrImpl();
        privateSequenceRepository = createMock(PrivateSequenceRepository.class);
        publicSequenceRepository = createMock(PublicSequenceRepository.class);
        digitGenerationStrategy = createMock(DigitGenerationStrategy.class);
        sequenceMgr.setPrivateSequenceRepository(privateSequenceRepository);
        sequenceMgr.setPublicSequenceRepository(publicSequenceRepository);
        sequenceMgr.setDigitGenerationStrategy(digitGenerationStrategy);
    }
    
    @After
    public void tearDown() {
        reset(privateSequenceRepository);
        reset(publicSequenceRepository);
        reset(digitGenerationStrategy);
    }
    
    @SuppressWarnings("serial")
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
    
    @SuppressWarnings("serial")
	public class SequenceableStub implements Sequenceable {
    	
    	private long internalNumber = 0;

		public Entity getEntity() {
			return entity;
		}
		
	    @Transient
	    public int getStartNumber() {
	    	return 1;
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
    
    @SuppressWarnings("serial")
	public class SequenceableVerifiableStub implements Sequenceable, Verifiable {
    	
    	private long internalNumber = Long.MAX_VALUE;
    	private int digit = 0;

		public Entity getEntity() {
			return entity;
		}

	    @Transient
	    public int getStartNumber() {
	    	return 1;
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

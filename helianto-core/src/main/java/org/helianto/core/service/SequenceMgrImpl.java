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

import java.util.List;

import javax.annotation.Resource;

import org.helianto.core.Node;
import org.helianto.core.SequenceMgr;
import org.helianto.core.domain.Entity;
import org.helianto.core.domain.Operator;
import org.helianto.core.domain.PrivateSequence;
import org.helianto.core.domain.PublicSequence;
import org.helianto.core.filter.Filter;
import org.helianto.core.filter.PrivateSequenceFilterAdapter;
import org.helianto.core.form.PrivateSequenceForm;
import org.helianto.core.number.DigitGenerationStrategy;
import org.helianto.core.number.Numerable;
import org.helianto.core.number.Sequenceable;
import org.helianto.core.number.Verifiable;
import org.helianto.core.repository.BasicDao;
import org.helianto.core.repository.FilterDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Default implementation of <code>SequenceMgr</code> interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Service("sequenceMgr")
public class SequenceMgrImpl implements SequenceMgr {
	
	@Transactional
	public long findOrCreatePublicNumber(Operator operator, String publicNumberKey) {
		PublicSequence publicSequence = publicEnumeratorDao.findUnique(operator, publicNumberKey);
		if (publicSequence!=null) {
			return publicSequence.getLastNumber();
		} else  {
            publicSequence = new PublicSequence(operator, publicNumberKey);
            publicEnumeratorDao.saveOrUpdate(publicSequence);
            logger.debug("Created PublicSequence: {}", publicSequence);
            return 1;
        }
	}
	
	@Transactional
    public long findNewPublicNumber(Operator operator, String publicNumberKey) {
    	PublicSequence publicSequence = publicEnumeratorDao.findUnique(operator, publicNumberKey);
        if (publicSequence!=null) {
            long lastNumber = publicSequence.getLastNumber();
            publicSequence.setLastNumber(lastNumber+1);
            publicEnumeratorDao.saveOrUpdate(publicSequence);
            logger.debug("Incremented existing PublicSequence: {}", publicSequence);
            return lastNumber;
        } else  {
            publicSequence = new PublicSequence();
            publicSequence.setOperator(operator);
            publicSequence.setTypeName(publicNumberKey);
            publicSequence.setLastNumber(2);    
            publicEnumeratorDao.saveOrUpdate(publicSequence);
            logger.debug("Created PublicSequence: {}", publicSequence);
            return 1;
        }
    }
    
	@Transactional
	public long findOrCreateInternalNumber(Entity entity, String internalNumberKey, int startNumber) {
		PrivateSequence privateSequence = internalEnumeratorDao.findUnique(entity, internalNumberKey);
		if (privateSequence!=null) {
			return privateSequence.getLastNumber();
		} else  {
            privateSequence = new PrivateSequence(entity, internalNumberKey);
            internalEnumeratorDao.saveOrUpdate(privateSequence);
            logger.debug("Created PrivateSequence: {}", privateSequence);
            return startNumber;
        }
    }
    
	@Transactional
	public long findOrCreateInternalNumber(Entity entity, String internalNumberKey) {
		return findOrCreateInternalNumber(entity, internalNumberKey, 1);
	}
	
	@Transactional(readOnly=true)
	public List<PrivateSequence> findPrivateSequences(PrivateSequenceForm form) {
		Filter filter = new PrivateSequenceFilterAdapter(form);
		List<PrivateSequence> privateSequenceList = (List<PrivateSequence>) internalEnumeratorDao.find(filter);
		return privateSequenceList;
	}
	
	@Transactional
	public PrivateSequence storePrivateSequence(PrivateSequence privateSequence) {
		internalEnumeratorDao.saveOrUpdate(privateSequence);
		return privateSequence;
	}
	
	@Transactional
    public long newInternalNumber(Entity entity, String internalNumberKey, int startNumber) {
        PrivateSequence privateSequence = internalEnumeratorDao.findUnique(entity, internalNumberKey);
        if (privateSequence!=null) {
            long lastNumber = privateSequence.getLastNumber();
            privateSequence.setLastNumber(lastNumber+1);
            internalEnumeratorDao.saveOrUpdate(privateSequence);
            logger.debug("Incremented existing PrivateSequence: {}", privateSequence);
            return lastNumber;
        } else  {
            privateSequence = new PrivateSequence();
            privateSequence.setEntity(entity);
            privateSequence.setTypeName(internalNumberKey);
            privateSequence.setLastNumber(startNumber+1);    
            internalEnumeratorDao.saveOrUpdate(privateSequence);
            logger.debug("Created PrivateSequence: {}", privateSequence);
            return startNumber;
        }
	}
	
    public long newInternalNumber(Entity entity, String internalNumberKey) {
    	return newInternalNumber(entity, internalNumberKey, 1);
    }
    
	public void validatePublicNumber(Numerable numerable) {
        if (numerable.getPublicNumber()==0) {
            long publicNumber = findNewPublicNumber(numerable.getOperator(), numerable.getPublicNumberKey());
            numerable.setPublicNumber(publicNumber);
            logger.debug("Created public key for new {} {}", numerable.getPublicNumberKey(), numerable.getPublicNumber());
        }
        else {
            logger.debug("PublicNumber not generated");
        }
	}
	
	public void validateInternalNumber(Sequenceable sequenceable) {
        if (sequenceable.getInternalNumber() >= sequenceable.getStartNumber()) {
            logger.debug("InternalNumber already up to date, not generated");
        }
        else {
            long internalNumber = newInternalNumber(sequenceable.getEntity(), sequenceable.getInternalNumberKey(), sequenceable.getStartNumber());
            sequenceable.setInternalNumber(internalNumber);
            logger.debug("Created internal key for new {} {}", sequenceable.getInternalNumberKey(), sequenceable.getInternalNumber());
        }
	}
	
	public void generateVerificationDigit(Verifiable verifiable) {
		long number = 0;
		if (verifiable instanceof Numerable) {
			number = ((Numerable) verifiable).getPublicNumber();
		} 
		else if (verifiable instanceof Sequenceable) {
			number = ((Sequenceable) verifiable).getInternalNumber();
		}
		if (number!=0) {
			verifiable.setVerificationDigit(digitGenerationStrategy.generateDigit(number));
			logger.debug("Verification digit set in {}.", verifiable);
		}
	}
	
	public List<Node> prepareTree(Node root) {
		treeBuilder.buildTree(root);
		return treeBuilder.getTree();
	}

    // collabs 
    
	private BasicDao<PublicSequence> publicEnumeratorDao;
	private FilterDao<PrivateSequence> internalEnumeratorDao;
	private DigitGenerationStrategy digitGenerationStrategy;
	private TreeBuilder treeBuilder;
	
    @Resource(name="publicEnumeratorDao")
	public void setPublicEnumeratorDao(BasicDao<PublicSequence> publicEnumeratorDao) {
		this.publicEnumeratorDao = publicEnumeratorDao;
	}

    @Resource(name="internalEnumeratorDao")
    public void setInternalEnumeratorDao(FilterDao<PrivateSequence> internalEnumeratorDao) {
        this.internalEnumeratorDao = internalEnumeratorDao;
    }
    
    @Resource(name="digitGenerationStrategy")
    public void setDigitGenerationStrategy(DigitGenerationStrategy digitGenerationStrategy) {
		this.digitGenerationStrategy = digitGenerationStrategy;
	}

	@Resource
	public void setTreeBuilder(TreeBuilder treeBuilder) {
		this.treeBuilder = treeBuilder;
	}

	private static final Logger logger = LoggerFactory.getLogger(SequenceMgrImpl.class);

}

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

import org.helianto.core.Entity;
import org.helianto.core.InternalEnumerator;
import org.helianto.core.Node;
import org.helianto.core.Operator;
import org.helianto.core.PublicEnumerator;
import org.helianto.core.Sequenceable;
import org.helianto.core.repository.BasicDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Default implementation of <code>SequenceMgr</code> interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Service("sequenceMgr")
public class SequenceMgrImpl implements SequenceMgr {
	
    public long newPublicNumber(Operator operator, String publicNumberKey) {
    	PublicEnumerator enumerator = publicEnumeratorDao.findUnique(operator, publicNumberKey);
        if (enumerator!=null) {
            long lastNumber = enumerator.getLastNumber();
            enumerator.setLastNumber(lastNumber+1);
            publicEnumeratorDao.saveOrUpdate(enumerator);
            logger.debug("Incremented existing PublicEnumerator: {}", enumerator);
            return lastNumber;
        } else  {
            enumerator = new PublicEnumerator();
            enumerator.setOperator(operator);
            enumerator.setTypeName(publicNumberKey);
            enumerator.setLastNumber(2);    
            publicEnumeratorDao.saveOrUpdate(enumerator);
            logger.debug("Created PublicEnumerator: {}", enumerator);
            return 1;
        }
    }
    
    public long newInternalNumber(Entity entity, String internalNumberKey) {
        InternalEnumerator enumerator = internalEnumeratorDao.findUnique(entity, internalNumberKey);
        if (enumerator!=null) {
            long lastNumber = enumerator.getLastNumber();
            enumerator.setLastNumber(lastNumber+1);
            internalEnumeratorDao.saveOrUpdate(enumerator);
            logger.debug("Incremented existing InternalEnumerator: {}", enumerator);
            return lastNumber;
        } else  {
            enumerator = new InternalEnumerator();
            enumerator.setEntity(entity);
            enumerator.setTypeName(internalNumberKey);
            enumerator.setLastNumber(2);    
            internalEnumeratorDao.saveOrUpdate(enumerator);
            logger.debug("Created InternalEnumerator: {}", enumerator);
            return 1;
        }
    }
    
	public void validateInternalNumber(Sequenceable sequenceable) {
        if (sequenceable.getInternalNumber()==0) {
            long internalNumber = newInternalNumber(sequenceable.getEntity(), sequenceable.getInternalNumberKey());
            sequenceable.setInternalNumber(internalNumber);
            logger.debug("Created key for new {} {}", sequenceable.getInternalNumberKey(), sequenceable.getInternalNumber());
        }
        else {
            logger.debug("InternalNumber not generated");
        }
	}
	
	public List<Node> prepareTree(Node root) {
		treeBuilder.buildTree(root);
		return treeBuilder.getTree();
	}

    // collabs 
    
	private BasicDao<PublicEnumerator> publicEnumeratorDao;
	private BasicDao<InternalEnumerator> internalEnumeratorDao;
	private TreeBuilder treeBuilder;
	
    @Resource(name="publicEnumeratorDao")
	public void setPublicEnumeratorDao( BasicDao<PublicEnumerator> publicEnumeratorDao) {
		this.publicEnumeratorDao = publicEnumeratorDao;
	}

    @Resource(name="internalEnumeratorDao")
    public void setInternalEnumeratorDao(BasicDao<InternalEnumerator> internalEnumeratorDao) {
        this.internalEnumeratorDao = internalEnumeratorDao;
    }

	@Resource
	public void setTreeBuilder(TreeBuilder treeBuilder) {
		this.treeBuilder = treeBuilder;
	}

	private static final Logger logger = LoggerFactory.getLogger(SequenceMgrImpl.class);

}

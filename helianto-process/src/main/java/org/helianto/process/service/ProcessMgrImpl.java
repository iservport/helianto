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

package org.helianto.process.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.helianto.core.Entity;
import org.helianto.core.Node;
import org.helianto.core.Sequenceable;
import org.helianto.core.User;
import org.helianto.core.repository.BasicDao;
import org.helianto.core.repository.FilterDao;
import org.helianto.core.service.SequenceMgr;
import org.helianto.partner.service.PartnerMgrImpl;
import org.helianto.process.AssociationType;
import org.helianto.process.Operation;
import org.helianto.process.Process;
import org.helianto.process.ProcessDocument;
import org.helianto.process.ProcessDocumentAssociation;
import org.helianto.process.ProcessDocumentFilter;
import org.helianto.process.Setup;
import org.helianto.resource.ResourceGroup;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Default <code>ProcessMgr</code> interface implementation.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Service("processMgr")
@Transactional
public class ProcessMgrImpl extends PartnerMgrImpl  implements ProcessMgr {

    public List<Node> prepareTree(ProcessDocument processDocument) {
    	ProcessDocument managedProcessDocument = processDocumentDao.merge(processDocument);
    	ProcessNode root = new ProcessNode(managedProcessDocument);
    	List<Node> processTree = sequenceMgr.prepareTree(root);
    	return processTree;
    }

    public List<ProcessDocument> findProcessDocuments(ProcessDocumentFilter filter) {
        List<ProcessDocument> processDocumentList = (List<ProcessDocument>) processDocumentDao.find(filter);
        if (logger.isDebugEnabled() && processDocumentList.size()>0) {
            logger.debug("Found {} item(s)", processDocumentList.size());
        }
        if (filter.getExclusions()!=null && filter.getExclusions().size()>0) {
        	processDocumentList.removeAll(filter.getExclusions());
            if (logger.isDebugEnabled()) {
                logger.debug("Removed {} item(s)", filter.getExclusions());
            }
        }
        return processDocumentList ;
	}

  	public ProcessDocument storeProcessDocument(ProcessDocument processDocument) {
  		ProcessDocument managedProcessDocument = 
  			(ProcessDocument) processDocumentDao.merge(processDocument);
  		if (managedProcessDocument instanceof Sequenceable) {
  			sequenceMgr.validateInternalNumber((Sequenceable) managedProcessDocument);
  		}
		return managedProcessDocument;
	}

	public ProcessDocumentAssociation storeDocumentAssociation(ProcessDocumentAssociation documentAssociation) {
		ProcessDocumentAssociation managedDocumentAssociation = processDocumentAssociationDao.merge(documentAssociation);
		return managedDocumentAssociation;
	}

	public List<ProcessDocumentAssociation> findOperations(User user, Process process) {
		ProcessDocumentFilter filter = ProcessDocumentFilter.processDocumentFilterFactory(user, process);
        logger.debug("Created filter {}", filter);
		String criteria = createProcessDocumentCriteriaAsString(filter, "documentAssociation");
		List<ProcessDocumentAssociation> operationList = (List<ProcessDocumentAssociation>) processDocumentAssociationDao.find(criteria);
        if (logger.isDebugEnabled() && operationList.size()>0) {
            logger.debug("Found {} item(s)", operationList.size());
        }
        return operationList ;
	}

	/**
	 * Create a criteria string from a filter.
	 * 
	 * @param filter
	 * @param prefix
	 */
	protected String createProcessDocumentCriteriaAsString(ProcessDocumentFilter filter, String prefix) {
		return new StringBuilder()
			.append(prefix)
			.append(".parent.id=")
			.append(filter.getDocument().getId())
			.append(" order by ")
			.append(prefix)
			.append(".sequence ")
			.toString();
	}
	
    public Process createProcess(Entity entity) {
        // TODO Auto-generated method stub
        return null;
    }
    
    public ProcessDocumentAssociation prepareAssociation(ProcessDocument parent, Object child) {
        logger.debug("Parent class is {}", parent.getClass());
        if (child==null) {
        	return new ProcessDocumentAssociation(parent);
        }
        logger.debug("Child class is {}", child.getClass());
    	AssociationType associationType = AssociationType.resolveAssociationType(parent.getClass(), child.getClass());
    	if (associationType==null) {
    		logger.warn("Unknown association");
    		associationType = AssociationType.GENERAL;
    	}
    	else if (associationType.equals(AssociationType.GENERAL)) {
    		logger.warn("Possible invalid association");
    	}
    	ProcessDocumentAssociation documentAssociation = parent.documentAssociationFactory((ProcessDocument) child, 0);
    	return documentAssociation;
    }
    
    public List<ProcessDocumentAssociation> findCharacteristics(User user, Operation operation) {
		ProcessDocumentFilter filter = ProcessDocumentFilter.processDocumentFilterFactory(user, operation);
        logger.debug("Created filter {}", filter);
		String criteria = createProcessDocumentCriteriaAsString(filter, "documentAssociation");
		List<ProcessDocumentAssociation> characteristicList = (List<ProcessDocumentAssociation>) processDocumentAssociationDao.find(criteria);
        logger.debug("Found {} item(s)", characteristicList.size());
        return characteristicList ;
    }

    @Deprecated
    public ProcessDocumentAssociation prepareCharacteristic(Operation operation) {
    	Set<ProcessDocumentAssociation> characteristicSet = operation.getChildAssociations();
        if (logger.isDebugEnabled() && characteristicSet.size()>0) {
            logger.debug("Found {} item(s) before insertion.", characteristicSet.size());
        }
    	return operation.operationCharacteristicFactory("", 0, 0);
    }
    
	public Setup prepareNewSetup(Operation operation, ResourceGroup resourceGroup) {
		Operation managedOperation = (Operation) processDocumentDao.merge(operation);
		return managedOperation.operationSetupFactory(resourceGroup);
	}

    public Setup storeSetup(Setup setup) {
    	return setupDao.merge(setup);
    }

	public List<Setup> listSetups(Operation operation) {
		Operation managedOperation = (Operation) processDocumentDao.merge(operation);
		List<Setup> listSetups = new ArrayList<Setup>(managedOperation.getSetups());
	    if (logger.isDebugEnabled() && listSetups!=null) {
	        logger.debug("Found {} setup(s)", listSetups.size());
	    }
	    processDocumentDao.evict(managedOperation);
	    Collections.sort(listSetups);
		return listSetups;
	}
	
    // collaborators 

    private FilterDao<ProcessDocument, ProcessDocumentFilter> processDocumentDao;
    private BasicDao<Setup> setupDao;
    private BasicDao<ProcessDocumentAssociation> processDocumentAssociationDao;
    private SequenceMgr sequenceMgr;
    
    @javax.annotation.Resource(name="processDocumentDao")
    public void setProcessDocumentDao(FilterDao<ProcessDocument, ProcessDocumentFilter> processDocumentDao) {
        this.processDocumentDao = processDocumentDao;
    }

    @javax.annotation.Resource(name="setupDao")
    public void setSetupDao(BasicDao<Setup> setupDao) {
        this.setupDao = setupDao;
    }

    @javax.annotation.Resource(name="processDocumentAssociationDao")
    public void setProcessDocumentAssociationDao(BasicDao<ProcessDocumentAssociation> processDocumentAssociationDao) {
        this.processDocumentAssociationDao = processDocumentAssociationDao;
    }

    @javax.annotation.Resource
    public void setSequenceMgr(SequenceMgr sequenceMgr) {
        this.sequenceMgr = sequenceMgr;
    }

    public static final Logger logger = LoggerFactory.getLogger(ProcessMgrImpl.class);

}

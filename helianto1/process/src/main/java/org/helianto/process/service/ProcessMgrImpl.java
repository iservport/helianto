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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.helianto.core.Entity;
import org.helianto.core.Node;
import org.helianto.core.Sequenceable;
import org.helianto.core.User;
import org.helianto.core.filter.SelectionStrategy;
import org.helianto.core.service.SequenceMgr;
import org.helianto.partner.service.PartnerMgrImpl;
import org.helianto.process.AssociationType;
import org.helianto.process.DocumentAssociation;
import org.helianto.process.Operation;
import org.helianto.process.Process;
import org.helianto.process.ProcessDocument;
import org.helianto.process.ProcessDocumentFilter;
import org.helianto.process.ResourceGroup;
import org.helianto.process.Setup;
import org.helianto.process.dao.DocumentAssociationDao;
import org.helianto.process.dao.ProcessDocumentDao;
import org.helianto.process.dao.SetupDao;

/**
 * <code>ProcessMgr</code> interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class ProcessMgrImpl extends PartnerMgrImpl  implements ProcessMgr {

    public List<Node> prepareTree(ProcessDocument processDocument) {
    	ProcessDocument managedProcessDocument = processDocumentDao.mergeProcessDocument(processDocument);
    	ProcessNode root = new ProcessNode(managedProcessDocument);
    	List<Node> processTree = sequenceMgr.prepareTree(root);
    	return processTree;
    }

    public List<ProcessDocument> findProcessDocuments(ProcessDocumentFilter filter) {
		String criteria = processDocumentSelectionStrategy.createCriteriaAsString(filter, "processDocument");
        List<ProcessDocument> processDocumentList = processDocumentDao.findProcessDocuments(criteria);
        if (logger.isDebugEnabled() && processDocumentList.size()>0) {
            logger.debug("Found "+processDocumentList.size()+" item(s)");
        }
        if (filter.getExclusions()!=null && filter.getExclusions().size()>0) {
        	processDocumentList.removeAll(filter.getExclusions());
            if (logger.isDebugEnabled()) {
                logger.debug("Removed "+filter.getExclusions()+" item(s)");
            }
        }
        return processDocumentList ;
	}

  	public ProcessDocument storeProcessDocument(ProcessDocument processDocument) {
  		ProcessDocument managedProcessDocument = 
  			(ProcessDocument) processDocumentDao.mergeProcessDocument(processDocument);
  		if (managedProcessDocument instanceof Sequenceable) {
  			sequenceMgr.validateInternalNumber((Sequenceable) managedProcessDocument);
  		}
		return managedProcessDocument;
	}

	public DocumentAssociation storeDocumentAssociation(DocumentAssociation documentAssociation) {
		DocumentAssociation managedDocumentAssociation = documentAssociationDao.mergeDocumentAssociation(documentAssociation);
		return managedDocumentAssociation;
	}

	public List<DocumentAssociation> findOperations(User user, Process process) {
		ProcessDocumentFilter filter = ProcessDocumentFilter.processDocumentFilterFactory(user, process);
        if (logger.isDebugEnabled()) {
            logger.debug("Created filter "+filter);
        }
		String criteria = createProcessDocumentCriteriaAsString(filter, "documentAssociation");
		List<DocumentAssociation> operationList = documentAssociationDao.findDocumentAssociations(criteria);
        if (logger.isDebugEnabled() && operationList.size()>0) {
            logger.debug("Found "+operationList.size()+" item(s)");
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
    
    public DocumentAssociation prepareAssociation(ProcessDocument parent, Object child) {
        if (logger.isDebugEnabled()) {
            logger.debug("Parent class is "+parent.getClass());
            logger.debug("Child class is "+child.getClass());
        }
    	AssociationType associationType = AssociationType.resolveAssociationType(parent.getClass(), child.getClass());
    	if (associationType==null) {
    		logger.warn("Unknown association");
    		associationType = AssociationType.GENERAL;
    	}
    	else if (associationType.equals(AssociationType.GENERAL)) {
    		logger.warn("Possible invalid association");
    	}
    	DocumentAssociation documentAssociation = parent.documentAssociationFactory((ProcessDocument) child, 0);
    	return documentAssociation;
    }
    
    public DocumentAssociation prepareAssociation(ProcessDocument parent, int sequence) {
        if (logger.isDebugEnabled()) {
            logger.debug("Parent class is "+parent.getClass());
        }
        ProcessDocument managedParent = processDocumentDao.mergeProcessDocument(parent);
        DocumentAssociation documentAssociation = managedParent.documentAssociationFactory(sequence);
        processDocumentDao.evict(managedParent);
    	return documentAssociation;
    }
    
    public List<DocumentAssociation> findCharacteristics(User user, Operation operation) {
		ProcessDocumentFilter filter = ProcessDocumentFilter.processDocumentFilterFactory(user, operation);
        if (logger.isDebugEnabled()) {
            logger.debug("Created filter "+filter);
        }
		String criteria = createProcessDocumentCriteriaAsString(filter, "documentAssociation");
		List<DocumentAssociation> characteristicList = documentAssociationDao.findDocumentAssociations(criteria);
        if (logger.isDebugEnabled() && characteristicList.size()>0) {
            logger.debug("Found "+characteristicList.size()+" item(s)");
        }
        return characteristicList ;
    }

    @Deprecated
    public DocumentAssociation prepareCharacteristic(Operation operation) {
    	Set<DocumentAssociation> characteristicSet = operation.getChildAssociations();
        if (logger.isDebugEnabled() && characteristicSet.size()>0) {
            logger.debug("Found "+characteristicSet.size()+" item(s) before insertion.");
        }
    	return operation.operationCharacteristicFactory("", 0, 0);
    }
    
	public Setup prepareNewSetup(Operation operation, ResourceGroup resourceGroup) {
		Operation managedOperation = (Operation) processDocumentDao.mergeProcessDocument(operation);
		return managedOperation.operationSetupFactory(resourceGroup);
	}

    public Setup storeSetup(Setup setup) {
    	return setupDao.mergeSetup(setup);
    }

	public List<Setup> listSetups(Operation operation) {
		Operation managedOperation = (Operation) processDocumentDao.mergeProcessDocument(operation);
		List<Setup> listSetups = new ArrayList<Setup>(managedOperation.getSetups());
	    if (logger.isDebugEnabled() && listSetups!=null) {
	        logger.debug("Found "+listSetups.size()+" setup(s)");
	    }
	    processDocumentDao.evict(managedOperation);
	    Collections.sort(listSetups);
		return listSetups;
	}
	
    // collaborators 

    private ProcessDocumentDao processDocumentDao;
    private SetupDao setupDao;
    private SelectionStrategy<ProcessDocumentFilter> processDocumentSelectionStrategy;
    private DocumentAssociationDao documentAssociationDao;
    private SequenceMgr sequenceMgr;
    
    @javax.annotation.Resource
    public void setProcessDocumentDao(ProcessDocumentDao processDocumentDao) {
        this.processDocumentDao = processDocumentDao;
    }

    @javax.annotation.Resource
    public void setSetupDao(SetupDao setupDao) {
        this.setupDao = setupDao;
    }

    @javax.annotation.Resource(name="processDocumentSelectionStrategy")
    public void setProcessDocumentSelectionStrategy(SelectionStrategy<ProcessDocumentFilter> processDocumentSelectionStrategy) {
        this.processDocumentSelectionStrategy = processDocumentSelectionStrategy;
    }

    @javax.annotation.Resource
    public void setDocumentAssociationDao(DocumentAssociationDao documentAssociationDao) {
        this.documentAssociationDao = documentAssociationDao;
    }

    @javax.annotation.Resource
    public void setSequenceMgr(SequenceMgr sequenceMgr) {
        this.sequenceMgr = sequenceMgr;
    }

    public static final Log logger = LogFactory.getLog(ProcessMgrImpl.class);

}

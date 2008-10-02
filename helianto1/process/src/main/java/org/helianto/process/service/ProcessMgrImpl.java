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

import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.helianto.core.Entity;
import org.helianto.core.Node;
import org.helianto.core.filter.SelectionStrategy;
import org.helianto.core.service.SequenceMgr;
import org.helianto.partner.service.PartnerMgrImpl;
import org.helianto.process.Characteristic;
import org.helianto.process.DocumentAssociation;
import org.helianto.process.ExternalDocument;
import org.helianto.process.MaterialType;
import org.helianto.process.Operation;
import org.helianto.process.Part;
import org.helianto.process.Process;
import org.helianto.process.ProcessDocument;
import org.helianto.process.ProcessDocumentFilter;
import org.helianto.process.ProcessFilter;
import org.helianto.process.Resource;
import org.helianto.process.Setup;
import org.helianto.process.dao.DocumentAssociationDao;
import org.helianto.process.dao.ProcessDao;

/**
 * Default implementation of <code>ProcessMgr</code> interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class ProcessMgrImpl extends PartnerMgrImpl  implements ProcessMgr {

    private ProcessDao processDao;
    private SelectionStrategy<ProcessFilter> processSelectionStrategy;
    private DocumentAssociationDao documentAssociationDao;
    private SequenceMgr sequenceMgr;
    
    public List<Node> prepareTree(ProcessDocument processDocument) {
    	ProcessNode root = new ProcessNode(processDocument);
    	List<Node> processTree = sequenceMgr.prepareTree(root);
    	return processTree;
    }

	public List<Process> findProcesses(ProcessFilter filter) {
		String criteria = processSelectionStrategy.createCriteriaAsString(filter, "process");
        List<Process> processList = processDao.findProcesses(criteria);
        if (logger.isDebugEnabled() && processList.size()>0) {
            logger.debug("Found "+processList.size()+" item(s)");
        }
        if (filter.getExclusions()!=null && filter.getExclusions().size()>0) {
            processList.removeAll(filter.getExclusions());
            if (logger.isDebugEnabled()) {
                logger.debug("Removed "+filter.getExclusions()+" item(s)");
            }
        }
        return processList ;
	}

	public Process storeProcess(Process process) {
		sequenceMgr.validateInternalNumber(process);
		return (Process) processDao.mergeProcessDocument(process);
	}

	public DocumentAssociation storeDocumentAssociation(DocumentAssociation documentAssociation) {
		return (DocumentAssociation) documentAssociationDao.mergeDocumentAssociation(documentAssociation);
	}

	public List<DocumentAssociation> findOperations(Process process) {
		ProcessDocumentFilter filter = ProcessDocumentFilter.processDocumentFilterFactory(process);
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

    public DocumentAssociation prepareOperation(Process process) {
    	return process.processOperationFactory("", 0, 0);
    }
    
    public List<DocumentAssociation> findCharacteristics(Operation operation) {
		ProcessDocumentFilter filter = ProcessDocumentFilter.processDocumentFilterFactory(operation);
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

    public DocumentAssociation prepareCharacteristic(Operation operation) {
    	Set<DocumentAssociation> characteristicSet = operation.getChildAssociations();
        if (logger.isDebugEnabled() && characteristicSet.size()>0) {
            logger.debug("Found "+characteristicSet.size()+" item(s) before insertion.");
        }
    	return operation.operationCharacteristicFactory("", 0, 0);
    }
    
    public DocumentAssociation prepareSpecification(Characteristic characteristic) {
    	Set<DocumentAssociation> specificationSet = characteristic.getChildAssociations();
        if (logger.isDebugEnabled() && specificationSet.size()>0) {
            logger.debug("Found "+specificationSet.size()+" item(s) before insertion.");
        }
    	return characteristic.characteristicSpecificationFactory("", 0, 0);
    }
    
    public Setup createSetupFactory(Operation operation, Resource resource) {
        // TODO Auto-generated method stub
        return null;
    }

    public void persistExternalDocument(ExternalDocument externalDocument) {
        processDao.persistDocument(externalDocument);
    }

    public void persistPart(Part part) {
        processDao.persistDocument(part);
    }

    public void persistProcess(Process process) {
        if (process.getInternalNumber()==0) {
            //FIXME internal number should return long ...
//            process.setInternalNumber(new Long(currentNumber(process.getEntity(), "PROC")));
        }
        processDao.persistDocument(process);
    }

    public void persistOperation(Operation operation) {
        processDao.persistDocument(operation);
    }

    public void persistSetup(Setup setup) {
        processDao.persistSetup(setup);
    }

    public ExternalDocument findExternalDocumentByNaturalId(Entity entity, String docCode) {
        return processDao.findExternalDocumentByNaturalId(entity, docCode);
    }
    
    public List<ExternalDocument> findExternalDocumentByEntity(Entity entity) {
        return processDao.findExternalDocumentByEntity(entity);
    }

    public List<ExternalDocument> findExternalDocumentRootByEntity(Entity entity) {
        return processDao.findExternalDocumentRootByEntity(entity);
    }

    public List<ExternalDocument> findExternalDocumentByParent(ExternalDocument parent) {
        return processDao.findExternalDocumentByParent(parent);
    }

    public void persistMaterial(MaterialType material) {
        // TODO Auto-generated method stub
        
    }
    
    // collaborators  DocumentAssociationDao documentAssociationDao

    @javax.annotation.Resource
    public void setProcessDao(ProcessDao processDao) {
        this.processDao = processDao;
    }

    @javax.annotation.Resource(name="processSelectionStrategy")
    public void setProcessSelectionStrategy(SelectionStrategy<ProcessFilter> processSelectionStrategy) {
        this.processSelectionStrategy = processSelectionStrategy;
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

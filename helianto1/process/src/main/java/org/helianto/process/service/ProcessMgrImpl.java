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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.helianto.core.Entity;
import org.helianto.partner.service.PartnerMgrImpl;
import org.helianto.process.ExternalDocument;
import org.helianto.process.MaterialType;
import org.helianto.process.Operation;
import org.helianto.process.Part;
import org.helianto.process.Process;
import org.helianto.process.ProcessFilter;
import org.helianto.process.Resource;
import org.helianto.process.Setup;
import org.helianto.process.dao.ProcessDao;
import org.helianto.process.dao.ProcessSelectionStrategy;

/**
 * Default implementation of <code>ProcessMgr</code> interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class ProcessMgrImpl extends PartnerMgrImpl  implements ProcessMgr {

    private ProcessDao processDao;
    private ProcessSelectionStrategy processSelectionStrategy;

	@Override
	public List<Process> findProcesses(ProcessFilter filter) {
		String criteria = processSelectionStrategy.createCriteriaAsString(filter, "process");
        List<Process> processList = processDao.findProcesses(criteria);
        if (logger.isDebugEnabled() && processList.size()>0) {
            logger.debug("Found "+processList.size()+" item(s)");
        }
        if (filter.getExclusions().size()>0) {
            processList.removeAll(filter.getExclusions());
            if (logger.isDebugEnabled()) {
                logger.debug("Removed "+filter.getExclusions()+" item(s)");
            }
        }
        return processList ;
	}

    @Deprecated
	public Part createPart(Entity entity, boolean hasDrawing) {
        // TODO Auto-generated method stub
        return null;
    }

    public void associateParts(Part parent, Part child, double coefficient, int sequence) {
        // TODO Auto-generated method stub
        
    }

    public Process createProcess(Entity entity) {
        // TODO Auto-generated method stub
        return null;
    }

    public Operation createOperation(Process process) {
        // TODO Auto-generated method stub
        return null;
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

    // collaborators 

    @javax.annotation.Resource
    public void setProcessDao(ProcessDao processDao) {
        this.processDao = processDao;
    }

    @javax.annotation.Resource(name="processSelectionStrategy")
    public void setProcessSelectionStrategy(ProcessSelectionStrategy processSelectionStrategy) {
        this.processSelectionStrategy = processSelectionStrategy;
    }

    public static final Log logger = LogFactory.getLog(ProcessMgrImpl.class);

}

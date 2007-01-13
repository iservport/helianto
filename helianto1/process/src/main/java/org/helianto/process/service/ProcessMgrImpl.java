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
import org.helianto.core.service.PartnerMgrImpl;
import org.helianto.process.ExternalDocument;
import org.helianto.process.MaterialType;
import org.helianto.process.Operation;
import org.helianto.process.Part;
import org.helianto.process.Process;
import org.helianto.process.Resource;
import org.helianto.process.Setup;
import org.helianto.process.creation.ExternalDocumentCreator;
import org.helianto.process.dao.ProcessDao;
import org.helianto.process.type.DocumentType;

/**
 * Default implementation of <code>ProcessMgr</code> interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class ProcessMgrImpl extends PartnerMgrImpl  implements ProcessMgr {

    public ExternalDocument createExternalDocumentCategory(Entity entity, String documentCode) {
        ExternalDocument externalDocument  = 
            ExternalDocumentCreator.externalDocumentFactory(entity, documentCode, DocumentType.CATEGORY);
        externalDocument.setDocName(documentCode);
        externalDocument.setDocUrl(documentCode+"/");
        return externalDocument;
    }

    public ExternalDocument createExternalFolder(ExternalDocument parent, String documentCode) {
        ExternalDocument externalDocument  = 
            ExternalDocumentCreator.externalDocumentFactory(parent.getEntity(), documentCode, DocumentType.FOLDER);
        // FIXME new association domain model
//        externalDocument.setParent(parent);
        externalDocument.setDocName(documentCode);
        externalDocument.setDocUrl(documentCode+"/");
        return externalDocument;
    }

    public ExternalDocument createExternalFile(ExternalDocument parent, String documentCode) {
        ExternalDocument externalDocument  = 
            ExternalDocumentCreator.externalDocumentFactory(parent.getEntity(), documentCode, DocumentType.FILE);
        // FIXME new association domain model
//        externalDocument.setParent(parent);
        externalDocument.setDocName(documentCode);
        externalDocument.setDocUrl(documentCode+"/");
        return externalDocument;
    }

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

//    public 
    
    public static final Log logger = LogFactory.getLog(ProcessMgrImpl.class);

    private ProcessDao processDao;

    public void setProcessDao(ProcessDao processDao) {
        this.processDao = processDao;
    }

    public void persistMaterial(MaterialType material) {
        // TODO Auto-generated method stub
        
    }

}

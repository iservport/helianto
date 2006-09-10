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

package org.helianto.process.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.helianto.core.Entity;
import org.helianto.process.Document;
import org.helianto.process.ExternalDocument;
import org.helianto.process.Operation;
import org.helianto.process.Part;
import org.helianto.process.Process;
import org.helianto.process.Setup;
import org.helianto.process.dao.ProcessDao;

public class ProcessDaoImpl extends MaterialDaoImpl implements ProcessDao {

    public void persistDocument(Document document) {
        merge(document);
    }

    public void persistSetup(Setup setup) {
        merge(setup);
    }

    public ExternalDocument findExternalDocumentByNaturalId(Entity entity, String docCode) {
        return (ExternalDocument) findUnique(EXTERNALDOCUMENT_QRY, entity, docCode);
    }
    
    public List<ExternalDocument> findExternalDocumentByEntity(Entity entity) {
        return (ArrayList<ExternalDocument>) find(EXTERNALDOCUMENT_ENTITY_QRY, entity);
    }

    public List<ExternalDocument> findExternalDocumentRootByEntity(Entity entity) {
        return (ArrayList<ExternalDocument>) find(EXTERNALDOCUMENT_ROOT_QRY, entity);
    }

    public List<ExternalDocument> findExternalDocumentByParent(ExternalDocument parent) {
        return (ArrayList<ExternalDocument>) find(EXTERNALDOCUMENT_PARENT_QRY, parent);
    }

    public List<Part> findPartByEntity(Entity entity) {
        return (ArrayList<Part>) find(PART_QRY, entity);
    }

    public List<Process> findProcessByEntity(Entity entity) {
        return (ArrayList<Process>) find(PROCESS_QRY, entity);
    }

    public List<Operation> findOperationByProcess(Entity entity) {
        return (ArrayList<Operation>) find(OPERATION_QRY, entity);
    }

    public List<Setup> findSetupByEntity(Entity entity) {
        return (ArrayList<Setup>) find(SETUP_QRY, entity);
    }

    static String EXTERNALDOCUMENT_QRY = "from ExternalDocument externalDocument "+
        "where externalDocument.entity = ? and externalDocument.docCode = ? ";
    static final String EXTERNALDOCUMENT_ENTITY_QRY = "from ExternalDocument externalDocument " +
        "where externalDocument.entity = ? ";
    static final String EXTERNALDOCUMENT_ROOT_QRY = "from ExternalDocument externalDocument " +
        "where externalDocument.entity = ? and externalDocument.parent = null ";
    static final String EXTERNALDOCUMENT_PARENT_QRY = "from ExternalDocument externalDocument " +
        "where externalDocument.parent = ? ";
    static final String PART_QRY = null;
    static final String PROCESS_QRY = null;
    static final String OPERATION_QRY = null;
    static final String SETUP_QRY = null;
    
    static final String TREE_QRY = 
        "from Tree tree where tree.parent.id = ? " +
        "and tree.child = ?";

    public void removeDocument(Document document) {
        remove(document);
        
    }

}

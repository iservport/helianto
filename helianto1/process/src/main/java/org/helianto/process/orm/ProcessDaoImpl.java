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

package org.helianto.process.orm;

import java.util.ArrayList;
import java.util.List;

import org.helianto.core.Entity;
import org.helianto.core.hibernate.GenericDaoImpl;
import org.helianto.process.ProcessDocument;
import org.helianto.process.ExternalDocument;
import org.helianto.process.Operation;
import org.helianto.process.Part;
import org.helianto.process.Process;
import org.helianto.process.Setup;
import org.helianto.process.dao.ProcessDao;
import org.springframework.stereotype.Repository;

/**
 * Default implementation of <code>Process</code> data access interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Repository("processDao")
public class ProcessDaoImpl extends GenericDaoImpl implements ProcessDao {

    public void persistDocument(ProcessDocument document) {
        merge(document);
    }

    public void persistSetup(Setup setup) {
        merge(setup);
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<Process> findProcesses(String criteria) {
        if (logger.isDebugEnabled()) {
            logger.debug("Finding process list with criteria ='"+criteria+"'");
        }
        if (criteria.equals("")) {
            return (ArrayList<Process>) find(Process.getProcessQueryStringBuilder());
        }
        return (ArrayList<Process>) find(new StringBuilder(Process.getProcessQueryStringBuilder()).append("where ").append(criteria));
    }

    public ExternalDocument findExternalDocumentByNaturalId(Entity entity, String docCode) {
        return (ExternalDocument) findUnique(EXTERNALDOCUMENT_QRY+EXTERNALDOCUMENT_FILTER, entity, docCode);
    }
    
    static String EXTERNALDOCUMENT_QRY = "from ExternalDocument externalDocument ";
    
    static String EXTERNALDOCUMENT_FILTER = "where externalDocument.entity = ? and externalDocument.docCode = ? ";
    
    public List<ExternalDocument> findExternalDocumentByEntity(Entity entity) {
        return (ArrayList<ExternalDocument>) find(EXTERNALDOCUMENT_QRY+EXTERNALDOCUMENT_FILTER_ENTITY, entity);
    }

    static final String EXTERNALDOCUMENT_FILTER_ENTITY = "where externalDocument.entity = ? ";
    
    public List<ExternalDocument> findExternalDocumentRootByEntity(Entity entity) {
        return (ArrayList<ExternalDocument>) find(EXTERNALDOCUMENT_QRY+EXTERNALDOCUMENT_FILTER_ENTITY+EXTERNALDOCUMENT_FILTER_ROOT, entity);
    }

    static final String EXTERNALDOCUMENT_FILTER_ROOT = "and externalDocument.parentAssociations.size = 0 ";
    
    public List<ExternalDocument> findExternalDocumentByParent(ExternalDocument parent) {
        return (ArrayList<ExternalDocument>) find(EXTERNALDOCUMENT_PARENT_QRY, parent);
    }

    static final String EXTERNALDOCUMENT_PARENT_QRY = "select child from Tree associations " +
        "where associations.parent = ? ";

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

    static final String PART_QRY = null;
    static final String PROCESS_QRY = null;
    static final String OPERATION_QRY = null;
    static final String SETUP_QRY = null;
    
    static final String TREE_QRY = 
        "from Tree tree where tree.parent.id = ? " +
        "and tree.child = ?";

    public void removeDocument(ProcessDocument document) {
        remove(document);
        
    }

}

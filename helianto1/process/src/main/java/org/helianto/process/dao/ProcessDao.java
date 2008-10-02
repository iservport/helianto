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

package org.helianto.process.dao;

import java.util.List;

import org.helianto.core.Entity;
import org.helianto.process.ProcessDocument;
import org.helianto.process.ExternalDocument;
import org.helianto.process.Operation;
import org.helianto.process.Part;
import org.helianto.process.Process;
import org.helianto.process.Setup;

/**
 * <code>Process</code> data access interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface ProcessDao {
    
    /**
     * Persist <code>Document</code> and subclasses.
     */
    public void persistDocument(ProcessDocument document);

    public void persistSetup(Setup setup);

    /**
     * <code>Process</code> finder.
     */
    public List<Process> findProcesses(String criteria);
    
    /**
     * Persist <code>ProcessDocument</code> and subclasses.
     */
    public void persistProcessDocument(ProcessDocument processDocument);

    /**
     * Merge <code>Document</code> and subclasses.
     */
    public ProcessDocument mergeProcessDocument(ProcessDocument processDocument);

    /**
     * Remove <code>ProcessDocument</code> and subclasses.
     */
    public void removeProcessDocument(ProcessDocument processDocument);

    
    //

    /**
     * <code>ExternalDocument</code> finder.
     */
    public ExternalDocument findExternalDocumentByNaturalId(Entity entity, String docCode);
    
    /**
     * <code>ExternalDocument</code> entity finder.
     */
    public List<ExternalDocument> findExternalDocumentByEntity(Entity entity);

    /**
     * <code>ExternalDocument</code> root (null parent) finder.
     */
    public List<ExternalDocument> findExternalDocumentRootByEntity(Entity entity);

    /**
     * <code>ExternalDocument</code> finder by parent.
     */
    public List<ExternalDocument> findExternalDocumentByParent(ExternalDocument parent);
    
    //
    
    public List<Part> findPartByEntity(Entity entity);

    public List<Process> findProcessByEntity(Entity entity);
    
    public List<Operation> findOperationByProcess(Entity entity);

    public List<Operation> findOperations(String criteria);

    public List<Setup> findSetupByEntity(Entity entity);
    
    /**
     * Remove <code>Document</code> and subclasses.
     */
    public void removeDocument(ProcessDocument document);

}

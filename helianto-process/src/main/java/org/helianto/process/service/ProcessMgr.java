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

import org.helianto.core.Entity;
import org.helianto.core.Node;
import org.helianto.core.User;
import org.helianto.partner.service.PartnerMgr;
import org.helianto.process.ProcessDocumentAssociation;
import org.helianto.process.Operation;
import org.helianto.process.Process;
import org.helianto.process.ProcessDocument;
import org.helianto.process.ProcessDocumentFilter;
import org.helianto.process.Setup;
import org.helianto.resource.ResourceGroup;

/**
 * <code>ProcessMgr</code> interface.
 * 
 * <p>
 * Usual process requirements may be signifcantly different depending on 
 * application area. For instance, a business model may require one process per part
 * and have operations that are most likely unique to that process, while 
 * a different model should allow zero or more parts to be attached to a previously
 * existing process. In addition, process details may be described in many ways,
 * including control plans and characteristics.
 * </p>
 * 
 * <p>
 * This process interface is designed to provide services including methods either to hide
 * or to expose such complexity at the user discretion. Presentation layer calls may be targeted
 * at one of the correspondent overloaded methods according to the business model required by
 * the business model. It relies on the hierarchy of the
 * <code>ProcessDocument</code> class, where parts, processes, operations, characteristics
 * and control plans are its descendants and may be related to each other through the
 * <code>DocumentAssociation</code> hierarchy.
 * </p>
 * <p></p>
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface ProcessMgr extends PartnerMgr {
	
	/**
	 * Create a process node tree.
	 * 
	 * @param processDocument
	 */
	public List<Node> prepareTree(ProcessDocument processDocument);

    /**
     * Find process documents.
     */
    public List<ProcessDocument> findProcessDocuments(ProcessDocumentFilter filter);
    
    /**
     * Process factory method.
     */
    public Process createProcess(Entity entity);
    
    /**
     * Store process document. If sequenceable, fix the sequence.
     */
    public ProcessDocument storeProcessDocument(ProcessDocument processDocument);
    
    /**
     * Find associations having child operations for a process.
     */
    public List<ProcessDocumentAssociation> findOperations(User user, Process process);
    
    /**
     * List setups.
     */
    public List<Setup> listSetups(Operation operation);
    
    /**
     * Prepare a new association to presentation layer.
     */
    public ProcessDocumentAssociation prepareAssociation(ProcessDocument parent, Object child);
    
    /**
     * Find characteristics.
     */
    public List<ProcessDocumentAssociation> findCharacteristics(User user, Operation operation);
    
    /**
     * Associated characteristic creation.
     */
    public ProcessDocumentAssociation prepareCharacteristic(Operation operation);
    
    /**
     * Store document associations.
     */
    public ProcessDocumentAssociation storeDocumentAssociation(ProcessDocumentAssociation documentAssociation);
    
	/**
	 * Create setup. 
	 */
	public Setup prepareNewSetup(Operation operation, ResourceGroup resourceGroup);
    
    /**
     * Store setups.
     */
	public Setup storeSetup(Setup setup);

}

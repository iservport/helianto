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

import java.util.Collection;

import org.helianto.core.Entity;
import org.helianto.core.service.PartnerMgr;
import org.helianto.process.MaterialType;
import org.helianto.process.Operation;
import org.helianto.process.Part;
import org.helianto.process.Process;
import org.helianto.process.Resource;
import org.helianto.process.Setup;
import org.helianto.process.Unit;

/**
 * Default implementation of the 
 * <code>ProcessMgr</code> interface.
 * 
 * @author Mauricio Fernandes de Castro
 * @version $Id$
 */
public interface ProcessMgr extends PartnerMgr {

    /**
     * Factory method to create a <code>Resource</code> group.
     * 
     * <p>
     * <code>Resource</code> groups must have a null parent.
     * </p>
     */
    public Resource resourceGroupFactory(Entity entity);
    
    /**
     * Factory method to create a <code>Resource</code>.
     * 
     * <p>
     * <code>Resource</code> groups must have a valid parent.
     * </p>
     */
    public Resource resourceFactory(Resource parent);
    
    /**
     * Unit factory method.
     */
    public Unit unitFactory(Entity entity, String unitCode, String unitName);
    
    /**
     * Unit factory method.
     */
    public Unit unitFactory(Unit parent, String unitCode, String unitName);
    
    /**
     * Material factory method.
     */
    public MaterialType materialFactory(Unit unit, String materialName);
    
    /**
     * Material factory method.
     */
    public MaterialType materialFactory(MaterialType parent, Unit unit, String materialName);
    
    /**
     * Product factory method.
     */
//    public Product productFactory(Unit unit, Material material, String productCode, Process process);
    
    /**
     * Part factory method.
     */
    public Part partFactory(Entity entity, String partCode, boolean hasDrawing);
    
    /**
     * Part factory method.
     */
    public Part partFactory(Part parent, String partCode, boolean hasDrawing, double coefficient);
    
    /**
     * Part association method.
     */
    public void associateParts(Part parent, Part child, double coefficient, int sequence);
    
    /**
     * Process factory method.
     */
    public Process processFactory(Entity entity, String processCode);
    
    /**
     * Operation factory method.
     */
    public Operation operationFactory(Process process, int sequence, String operationCode, long execTime);
    
    /**
     * Setup factory method.
     */
    public Setup setupFactory(Operation operation, Resource resource, int priority, long setupTime, long transportTime); 
    
    /**
     * Persist a <code>Resource</code>.
     */
    public void persistResource(Resource resource);
    
    /**
     * Persist an <code>Unit</code>.
     */
    public void persistUnit(Unit unit);
    
    /**
     * Persist a <code>Material</code>.
     */
    public void persistMaterial(MaterialType material);
    
    /**
     * Persist a <code>Part</code>.
     */
    public void persistPart(Part part);
    
    /**
     * Persist a <code>Process</code>.
     */
    public void persistProcess(Process process);
    
    /**
     * Persist an <code>Operation</code>.
     */
    public void persistOperation(Operation operation);
    
    /**
     * Persist an <code>Setup</code>.
     */
    public void persistSetup(Setup setup);
    
    /**
     * Load a <code>Resource</code>.
     */
    public Resource loadResource(Integer key);
    
    /**
     * Load an <code>Unit</code>.
     */
    public Unit loadUnit(Integer key);
    
    /**
     * Load a <code>Material</code>.
     */
    public MaterialType loadMaterial(Long key);
    
    /**
     * Load a <code>Part</code>.
     */
    public Part loadPart(Long key);
    
    /**
     * Load a <code>Process</code>.
     */
    public Process loadProcess(Long key);
    
    /**
     * Load an <code>Operation</code>.
     */
    public Operation loadOperation(Long key);
    
    /**
     * Load a <code>Setup</code>.
     */
    public Setup loadSetup(Long key);
    
    /**
     * Find <code>Resource</code>s by <code>Entity</code>.
     */
    public Collection findResources(Entity entity);

    /**
     * Find <code>Resource</code>s by <code>Entity</code> alias.
     */
    public Collection findResources(String entityAlias);
    
    /**
     * Find <code>Resource</code> groups by <code>Entity</code>.
     */
    public Collection findResourceGroups(Entity entity);

    /**
     * Find <code>Resource</code>s by group.
     */
    public Collection findResourcesByGroup(Resource resource);

}

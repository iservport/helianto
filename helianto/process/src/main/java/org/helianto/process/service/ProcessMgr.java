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

import java.io.Serializable;
import java.util.List;

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
     * Unit factory method.
     */
    public Unit createUnit(Entity entity);
    
    /**
     * Material factory method.
     */
    public MaterialType createMaterialType(Unit unit);
    
    /**
     * Part factory method.
     */
    public Part createPart(Entity entity, boolean hasDrawing);
    
    /**
     * Part association method.
     */
    public void associateParts(Part parent, Part child, double coefficient, int sequence);
    
    /**
     * Process factory method.
     */
    public Process createProcess(Entity entity);
    
    /**
     * Operation factory method.
     */
    public Operation createOperation(Process process);
    
    /**
     * Setup factory method.
     */
    public Setup createSetupFactory(Operation operation, Resource resource); 
    
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
     * Load an <code>Unit</code>.
     */
    public Unit loadUnit(Serializable key);
    
    /**
     * Load a <code>Material</code>.
     */
    public MaterialType loadMaterial(Serializable key);
    
    /**
     * Load a <code>Part</code>.
     */
    public Part loadPart(Serializable key);
    
    /**
     * Load a <code>Process</code>.
     */
    public Process loadProcess(Serializable key);
    
    /**
     * Load an <code>Operation</code>.
     */
    public Operation loadOperation(Serializable key);
    
    /**
     * Load a <code>Setup</code>.
     */
    public Setup loadSetup(Serializable key);
    
    /**
     * Find <code>Unit</code> list for this <code>Entity</code>
     */
    public List<Unit> findUnitByEntity(Entity entity);
    
}

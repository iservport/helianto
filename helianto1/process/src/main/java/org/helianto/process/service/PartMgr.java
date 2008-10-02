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

import org.helianto.core.Entity;
import org.helianto.partner.service.PartnerMgr;
import org.helianto.process.MaterialType;
import org.helianto.process.Part;

/**
 * <code>ProcessMgr</code> interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface PartMgr extends PartnerMgr {

    /**
     * Part factory method.
     */
    public Part createPart(Entity entity, boolean hasDrawing);
    
    /**
     * Part association method.
     */
    public void associateParts(Part parent, Part child, double coefficient, int sequence);
    
    /**
     * Persist a <code>Material</code>.
     */
    public void persistMaterial(MaterialType material);
    
    /**
     * Persist a <code>Part</code>.
     */
    public void persistPart(Part part);
    
}

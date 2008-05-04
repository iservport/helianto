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
import org.helianto.core.dao.CommonOrmDao;
import org.helianto.process.Cause;

/**
 * <code>Cause</code> data access interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface CauseDao extends CommonOrmDao {
     
    /**
     * Persist <code>Cause</code>.
     */
    public void persistCause(Cause cause);
    
    /**
     * Merge <code>Cause</code>.
     */
    public Cause mergeCause(Cause cause);
    
    /**
     * Remove <code>Cause</code>.
     */
    public void removeCause(Cause cause);
    
    /**
     * Find <code>Cause</code> by <code>Entity</code> and internalNumber.
     */
    public Cause findCauseByNaturalId(Entity entity, long internalNumber);
    
    /**
     * Find <code>Cause</code> by criteria.
     */
    public List<Cause> findCauses(String criteria);    
    
}

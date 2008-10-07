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

import org.helianto.core.dao.CommonOrmDao;
import org.helianto.process.Operation;
import org.helianto.process.Resource;
import org.helianto.process.Setup;

/**
 * <code>Setup</code> data access interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface SetupDao extends CommonOrmDao {
     
    /**
     * Persist <code>Setup</code>.
     */
    public void persistSetup(Setup setup);
    
    /**
     * Merge <code>Setup</code>.
     */
    public Setup mergeSetup(Setup setup);
    
    /**
     * Remove <code>Setup</code>.
     */
    public void removeSetup(Setup setup);
    
    /**
     * Find <code>Setup</code> by <code>Operation</code> and <code>Resource</code>.
     */
    public Setup findSetupByNaturalId(Operation operation, Resource resource);
    
    /**
     * Find <code>Setup</code> by criteria.
     */
    public List<Setup> findSetups(String criteria);    
    
}

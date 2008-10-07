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

import org.helianto.core.hibernate.GenericDaoImpl;
import org.helianto.process.Operation;
import org.helianto.process.Resource;
import org.helianto.process.Setup;
import org.helianto.process.dao.SetupDao;
import org.springframework.stereotype.Repository;
/**
 * Default implementation of <code>Document</code> data access interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Repository("setupDao")
public class SetupDaoImpl extends GenericDaoImpl implements SetupDao {
     
    public void persistSetup(Setup setup) {
        if (logger.isDebugEnabled()) {
            logger.debug("Persisting "+setup);
        }
        persist(setup);
    }
    
    public Setup mergeSetup(Setup setup) {
        if (logger.isDebugEnabled()) {
            logger.debug("Merging "+setup);
        }
        return (Setup) merge(setup);
    }
    
    public void removeSetup(Setup setup) {
        if (logger.isDebugEnabled()) {
            logger.debug("Removing "+setup);
        }
        remove(setup);
    }
    
    public Setup findSetupByNaturalId(Operation operation, Resource resource) {
        if (logger.isDebugEnabled()) {
            logger.debug("Finding unique setup with operation='"+operation+"' and resource='"+resource+"' ");
        }
        return (Setup) findUnique(Setup.getSetupNaturalIdQueryString(), operation, resource);
    }
    
    @SuppressWarnings("unchecked")
	public List<Setup> findSetups(String criteria) {
        if (logger.isDebugEnabled()) {
            logger.debug("Finding setup list with criteria ='"+criteria+"'");
        }
        if (criteria.equals("")) {
            return (ArrayList<Setup>) find(Setup.getSetupQueryStringBuilder());
        }
        return (ArrayList<Setup>) find(new StringBuilder(Setup.getSetupQueryStringBuilder()).append("where ").append(criteria));
    }
    
}

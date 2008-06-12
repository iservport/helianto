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

package org.helianto.core.orm;

import java.util.ArrayList;
import java.util.List;

import org.helianto.core.Identity;
import org.helianto.core.dao.IdentityDao;
import org.helianto.core.hibernate.GenericDaoImpl;
import org.springframework.stereotype.Repository;

/**
 * Default implementation of <code>Identity</code> data access interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Repository("identityDao")
public class IdentityDaoImpl extends GenericDaoImpl implements IdentityDao {

    public void persistIdentity(Identity identity) {
        if (logger.isDebugEnabled()) {
            logger.debug("Persisting " + identity);
        }
        persist(identity);
    }

    public Identity mergeIdentity(Identity identity) {
        if (logger.isDebugEnabled()) {
            logger.debug("Merging " + identity);
        }
        return (Identity) merge(identity);
    }

    public void removeIdentity(Identity identity) {
        if (logger.isDebugEnabled()) {
            logger.debug("Removing " + identity);
        }
        remove(identity);
    }

    public Identity findIdentityByNaturalId(String principal) {
        if (logger.isDebugEnabled()) {
            logger.debug("Finding unique identity with principal='" + principal
                    + "' ");
        }
        return (Identity) findUnique(
                Identity.getIdentityNaturalIdQueryString(), principal);
    }

    @SuppressWarnings("unchecked")
	public List<Identity> findIdentities(String criteria) {
        if (logger.isDebugEnabled()) {
            logger.debug("Finding identity list with criteria='" + criteria
                    + "' ");
        }
        return (ArrayList<Identity>) find(Identity
                .getIdentityQueryAllString().append("where ").append(criteria));
    }

}

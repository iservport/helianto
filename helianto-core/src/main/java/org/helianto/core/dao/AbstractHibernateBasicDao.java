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

package org.helianto.core.dao;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.helianto.core.repository.AbstractBasicDao;
import org.helianto.core.repository.HibernatePersistenceStrategy;
import org.helianto.core.repository.PersistenceStrategy;
import org.springframework.beans.factory.InitializingBean;

/**
 * Base implementation using Hibernate Session for <code>BasicDao</code>.
 * 
 * <p>
 * This class will be removed in the next release. The current approach is 
 * implemented through JavaConfig and the appropriate dependencies are 
 * wired togheter.
 * </p>
 * 
 * @deprecated
 * @author Mauricio Fernandes de Castro.
 */
public abstract class AbstractHibernateBasicDao<T> extends AbstractBasicDao<T> implements InitializingBean {
	
	@Override
    protected PersistenceStrategy getPersistenceStrategy() {
		return this.persistenceStrategy;
	}
	
    public void afterPropertiesSet() throws Exception {
		this.persistenceStrategy = new HibernatePersistenceStrategy(sessionFactory);
	}

	// collabs
    
	private org.hibernate.SessionFactory sessionFactory;
    private PersistenceStrategy persistenceStrategy;
    
    /**
     * Spring will inject a managed Hibernate Session into this field.
     */
    @Resource(name="sessionFactory")
	public void setSessionFactory(org.hibernate.SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

    private static final Log logger = LogFactory.getLog(AbstractHibernateBasicDao.class);

}

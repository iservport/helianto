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

package org.helianto.core.junit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.helianto.core.dao.GenericDao;
import org.helianto.core.service.GenericService;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

/**
 * Base class to domain integration tests based on 
 * <code>GenericDao</code>.
 * 
 * @author Mauricio Fernandes de Castro
 * @version $Id$
 */
public abstract class AbstractTestBase extends AbstractDependencyInjectionSpringContextTests implements GenericService {
    /**
     * Logger for this class
     */
    protected static final Log logger = LogFactory.getLog(AbstractTestBase.class);
    
    private GenericDao genericDao;
    
    /**
     * @return Returns the genericDao.
     */
    public GenericDao getGenericDao() {
        return genericDao;
    }
    /**
     * @param genericDao The genericDao to set.
     */
    public void setGenericDao(GenericDao genericDao) {
        this.genericDao = genericDao;
    }

}

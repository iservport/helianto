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

package org.helianto.core.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.helianto.core.dao.GenericDao;
import org.helianto.core.mail.JavaMailAdapter;

/**
 * Base class to services using the <code>GenericDao</code>.
 * 
 * @author Mauricio Fernandes de Castro
 * @version $Id $
 */
public abstract class AbstractGenericService implements GenericService {
    
    public static final Log logger = LogFactory.getLog(AbstractGenericService.class);

    protected GenericDao genericDao;

    protected JavaMailAdapter javaMailAdapter;

    public JavaMailAdapter getJavaMailAdapter() {
        return javaMailAdapter;
    }

    public void setJavaMailAdapter(JavaMailAdapter javaMailAdapter) {
        this.javaMailAdapter = javaMailAdapter;
    }

    public GenericDao getGenericDao() {
        return genericDao;
    }

    public void setGenericDao(GenericDao genericDao) {
        this.genericDao = genericDao;
    }
    
}

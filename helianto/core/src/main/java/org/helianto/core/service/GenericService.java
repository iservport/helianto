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

import org.helianto.core.dao.GenericDao;
import org.helianto.core.mail.JavaMailAdapter;

/**
 * A generic service interface.
 * 
 * @author Mauício Fernandes de Castro
 * @version $Id $
 */
public interface GenericService {

    /**
     * @return Returns the genericDao.
     */
    public GenericDao getGenericDao();

    /**
     * @param genericDao The genericDao to set.
     */
    public void setGenericDao(GenericDao genericDao);
    
    /**
     * @return Returns a specialized mail sender.
     */
    public JavaMailAdapter getJavaMailAdapter();

    /**
     * @param genericDao The mail sender to set.
     */
   public void setJavaMailAdapter(JavaMailAdapter javaMailAdapter);

}
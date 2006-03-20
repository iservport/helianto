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

package org.helianto.core.hibernate;

import org.helianto.core.Home;
import org.helianto.core.HomeCreatorImpl;
import org.helianto.core.dao.HomeDao;
import org.helianto.core.junit.AbstractIntegrationTest;

public class HomeDaoImplTests extends AbstractIntegrationTest {
    
    private HomeDao homeDao;
    private HomeCreatorImpl factory;
    
    public void setHomeDao(HomeDao homeDao) {
        this.homeDao = homeDao;
    }
    
    @Override
    protected void onSetUpBeforeTransaction() throws Exception {
        factory = new HomeCreatorImpl();
    }

    public void testHomeLifeCycle() {
        
        Home home = factory.homeFactory("TEST");
        homeDao.persistHome(home);
        
        hibernateTemplate.flush();
        
        Home h = homeDao.findHomeByHomeName("TEST");
        assertEquals(home, h);
        
        Home duplicatedHome = factory.homeFactory("TEST");
        try {
            homeDao.persistHome(duplicatedHome);
            fail();
        } catch (Exception e) {
            //ok
        }

    }
    
}

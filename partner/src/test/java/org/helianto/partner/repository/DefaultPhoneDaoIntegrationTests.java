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


package org.helianto.partner.repository;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import org.helianto.core.dao.BasicDao;
import org.helianto.partner.Phone;
import org.helianto.partner.test.AbstractPartnerDaoIntegrationTest;
import org.helianto.partner.test.PhoneTestSupport;

/**
 * @author Mauricio Fernandes de Castro
 */
public class DefaultPhoneDaoIntegrationTests extends AbstractPartnerDaoIntegrationTest {
	
	@Override
	public void findUnique() {
		Phone phone = PhoneTestSupport.createPhone();
		phoneDao.persist(phone);
		assertEquals(phone, phoneDao.findUnique(phone.getAddress(), phone.getSequence()));
	}

    //- collabs

    private BasicDao<Phone> phoneDao;
    
    @Resource(name="phoneDao")
    public void setPhoneDao(BasicDao<Phone> phoneDao) {
        this.phoneDao = phoneDao;
    }
    
}

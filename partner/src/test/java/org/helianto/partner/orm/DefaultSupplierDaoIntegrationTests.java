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


package org.helianto.partner.orm;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import org.helianto.core.dao.BasicDao;
import org.helianto.partner.Partner;
import org.helianto.partner.Supplier;
import org.helianto.partner.test.AbstractPartnerDaoIntegrationTest;
import org.helianto.partner.test.SupplierTestSupport;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Mauricio Fernandes de Castro
 */
public class DefaultSupplierDaoIntegrationTests extends AbstractPartnerDaoIntegrationTest {
	
	@Override
	public void testFindUnique() {
		Supplier supplier = SupplierTestSupport.createSupplier();
		partnerDao.persist(supplier);
		assertEquals(supplier, partnerDao.findUnique(supplier.getPartnerRegistry(), 'S'));
	}

    //- collabs

    private BasicDao<Partner> partnerDao;
    
    @Resource(name="partnerDao")
    public void setPartnerDao(BasicDao<Partner> partnerDao) {
        this.partnerDao = partnerDao;
    }
    
}

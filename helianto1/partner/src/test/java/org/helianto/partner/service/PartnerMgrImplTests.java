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

package org.helianto.partner.service;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.easymock.EasyMock;
import org.helianto.core.dao.FilterDao;
import org.helianto.partner.PartnerRegistry;
import org.helianto.partner.PartnerRegistryFilter;

/**
 * @author Mauricio Fernandes de Castro
 */
public class PartnerMgrImplTests extends TestCase {
    
    private PartnerMgrImpl partnerMgr;
    
    public void testFindPartnerRegistries() {
    	PartnerRegistryFilter partnerRegistryFilter = new PartnerRegistryFilter();
    	List<PartnerRegistry> partnerRegistryList = new ArrayList<PartnerRegistry>();
    	
    	expect(partnerRegistryDao.find(partnerRegistryFilter)).andReturn(partnerRegistryList);
    	replay(partnerRegistryDao);
    	
    	assertSame(partnerRegistryList, partnerMgr.findPartnerRegistries(partnerRegistryFilter));
    	verify(partnerRegistryDao);
    }
    
    public void testStorePartnerRegistry() {
    	PartnerRegistry partnerRegistry = new PartnerRegistry();
    	PartnerRegistry managedPartnerRegistry = new PartnerRegistry();
    	
    	expect(partnerRegistryDao.merge(partnerRegistry)).andReturn(managedPartnerRegistry);
    	replay(partnerRegistryDao);

    	assertSame(managedPartnerRegistry, partnerMgr.storePartnerRegistry(partnerRegistry));
    	verify(partnerRegistryDao);
    }
    
    public void testRemovePartnerRegistry() {
    	PartnerRegistry partnerRegistry = new PartnerRegistry();
    	
    	partnerRegistryDao.remove(partnerRegistry);
    	replay(partnerRegistryDao);

    	partnerMgr.removePartnerRegistry(partnerRegistry);
    	verify(partnerRegistryDao);
    }
    
    private FilterDao<PartnerRegistry, PartnerRegistryFilter> partnerRegistryDao;

	@SuppressWarnings("unchecked")
	@Override
    public void setUp() {
        partnerMgr = new PartnerMgrImpl();
        partnerRegistryDao = EasyMock.createMock(FilterDao.class);
        partnerMgr.setPartnerRegistryDao(partnerRegistryDao);
    }
    
    @Override
    public void tearDown() {
    	EasyMock.reset(partnerRegistryDao);
    }

}

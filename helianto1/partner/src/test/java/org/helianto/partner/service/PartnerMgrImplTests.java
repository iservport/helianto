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

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.easymock.EasyMock;
import org.helianto.core.filter.SelectionStrategy;
import org.helianto.partner.PartnerRegistry;
import org.helianto.partner.PartnerRegistryFilter;
import org.helianto.partner.dao.PartnerRegistryDao;

/**
 * @author Mauricio Fernandes de Castro
 */
public class PartnerMgrImplTests extends TestCase {
    
    private PartnerMgrImpl partnerMgr;
    
    public void testFindPartnerRegistries() {
    	PartnerRegistryFilter partnerRegistryFilter = new PartnerRegistryFilter();
    	List<PartnerRegistry> partnerRegistryList = new ArrayList<PartnerRegistry>();
    	
    	String criteria = "CRITERIA";
    	expect(partnerRegistrySelectionStrategy.createCriteriaAsString(partnerRegistryFilter, "partnerRegistry")).andReturn(criteria);
    	replay(partnerRegistrySelectionStrategy);
    	
    	expect(partnerRegistryDao.findPartnerRegistries(criteria)).andReturn(partnerRegistryList);
    	replay(partnerRegistryDao);
    	
    	assertSame(partnerRegistryList, partnerMgr.findPartnerRegistries(partnerRegistryFilter));
    }
    
    public void testStorePartnerRegistry() {
    	PartnerRegistry partnerRegistry = new PartnerRegistry();
    	PartnerRegistry managedPartnerRegistry = new PartnerRegistry();
    	
    	expect(partnerRegistryDao.mergePartnerRegistry(partnerRegistry)).andReturn(managedPartnerRegistry);
    	replay(partnerRegistryDao);

    	assertSame(managedPartnerRegistry, partnerMgr.storePartnerRegistry(partnerRegistry));
    }
    
    public void testRemovePartnerRegistry() {
    	PartnerRegistry partnerRegistry = new PartnerRegistry();
    	
    	partnerRegistryDao.removePartnerRegistry(partnerRegistry);
    	replay(partnerRegistryDao);

    	partnerMgr.removePartnerRegistry(partnerRegistry);
    }
    
    private PartnerRegistryDao partnerRegistryDao;
	private SelectionStrategy<PartnerRegistryFilter> partnerRegistrySelectionStrategy;

	@SuppressWarnings("unchecked")
	@Override
    public void setUp() {
        partnerMgr = new PartnerMgrImpl();
        partnerRegistryDao = EasyMock.createMock(PartnerRegistryDao.class);
        partnerMgr.setPartnerRegistryDao(partnerRegistryDao);
        partnerRegistrySelectionStrategy = EasyMock.createMock(SelectionStrategy.class);
        partnerMgr.setPartnerRegistrySelectionStrategy(partnerRegistrySelectionStrategy);
    }
    
    @Override
    public void tearDown() {
    	EasyMock.reset(partnerRegistryDao);
    	EasyMock.reset(partnerRegistrySelectionStrategy);
    }

}

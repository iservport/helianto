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
import static org.junit.Assert.assertSame;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.helianto.core.repository.FilterDao;
import org.helianto.partner.PrivateEntity;
import org.helianto.partner.PrivateEntityFilter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Mauricio Fernandes de Castro
 */
public class PartnerMgrImplTests {
    
    private PartnerMgrImpl partnerMgr;
    
	@Test
    public void findPartnerRegistries() {
    	PrivateEntityFilter partnerRegistryFilter = new PrivateEntityFilter();
    	List<PrivateEntity> partnerRegistryList = new ArrayList<PrivateEntity>();
    	
    	expect(privateEntityDao.find(partnerRegistryFilter)).andReturn(partnerRegistryList);
    	replay(privateEntityDao);
    	
    	assertSame(partnerRegistryList, partnerMgr.findPartnerRegistries(partnerRegistryFilter));
    	verify(privateEntityDao);
    }
    
	@Test
    public void storePartnerRegistry() {
    	PrivateEntity partnerRegistry = new PrivateEntity();
    	
    	privateEntityDao.saveOrUpdate(partnerRegistry);
    	replay(privateEntityDao);

    	assertSame(partnerRegistry, partnerMgr.storePartnerRegistry(partnerRegistry));
    	verify(privateEntityDao);
    }
    
	@Test
    public void removePartnerRegistry() {
    	PrivateEntity partnerRegistry = new PrivateEntity();
    	
    	privateEntityDao.remove(partnerRegistry);
    	replay(privateEntityDao);

    	partnerMgr.removePartnerRegistry(partnerRegistry);
    	verify(privateEntityDao);
    }
    
    private FilterDao<PrivateEntity, PrivateEntityFilter> privateEntityDao;

	@SuppressWarnings("unchecked")
	@Before
    public void setUp() {
        partnerMgr = new PartnerMgrImpl();
        privateEntityDao = EasyMock.createMock(FilterDao.class);
        partnerMgr.setPrivateEntityDao(privateEntityDao);
    }
    
    @After
    public void tearDown() {
    	EasyMock.reset(privateEntityDao);
    }

}

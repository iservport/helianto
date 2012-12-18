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


package org.helianto.bootstrap.config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.easymock.EasyMock;
import org.helianto.bootstrap.ContextInstallationMgr;
import org.helianto.bootstrap.ServiceInstallationMgr;
import org.helianto.bootstrap.config.DefaultOperatorInstaller;
import org.helianto.bootstrap.config.ContextDefaults;
import org.helianto.core.domain.KeyType;
import org.helianto.core.domain.Operator;
import org.helianto.core.domain.Service;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * @author Mauricio Fernandes de Castro
 */
public class DefaultOperatorInstallerTests {
	
	@Test
	public void afterPropertiesSet() throws Exception {
		Operator defaultOperator = new Operator();
		
		EasyMock.expect(contextInstallationMgr.installOperator("DEFAULT", false)).andReturn(defaultOperator);
		
		defaultOperatorInstaller.setRequiredKeyTypeList(new String[] { "key1", "key2: key2Name" });
		KeyType keyType1 = new KeyType();
		EasyMock.expect(contextInstallationMgr.installKey(defaultOperator, "key1")).andReturn(keyType1);
		KeyType keyType2 = new KeyType();
		EasyMock.expect(contextInstallationMgr.installKey(defaultOperator, "key2")).andReturn(keyType2);
		EasyMock.replay(contextInstallationMgr);
		
		Service service1 = new Service();
		EasyMock.expect(serviceInstallationMgr.installService(defaultOperator, "service1")).andReturn(service1);
		Service service2 = new Service();
		EasyMock.expect(serviceInstallationMgr.installService(defaultOperator, "service2")).andReturn(service2);
		
		EasyMock.replay(serviceInstallationMgr);
		
		defaultOperatorInstaller.setRequiredServiceList(new String[] { "service1", "service2" });
		
		defaultOperatorInstaller.afterPropertiesSet();
		assertSame(defaultOperator, contextDefaults.getDefaultOperator());
		assertSame(keyType1, contextDefaults.getKeyTypeMap().get("key1"));
		assertSame(keyType2, contextDefaults.getKeyTypeMap().get("key2"));
		assertEquals("key2Name", contextDefaults.getKeyTypeMap().get("key2").getKeyName());
		assertSame(service1, contextDefaults.getServiceMap().get("service1"));
		assertSame(service2, contextDefaults.getServiceMap().get("service2"));
		
	}

	private DefaultOperatorInstaller defaultOperatorInstaller;
	
	private ContextDefaults contextDefaults;
	private ContextInstallationMgr contextInstallationMgr;
	private ServiceInstallationMgr serviceInstallationMgr;
	
	@Before
	public void setUp() {
		contextDefaults = new ContextDefaults();
		contextInstallationMgr = EasyMock.createMock(ContextInstallationMgr.class);
		serviceInstallationMgr = EasyMock.createMock(ServiceInstallationMgr.class);
		defaultOperatorInstaller = new DefaultOperatorInstaller(contextDefaults, contextInstallationMgr, serviceInstallationMgr);
	}
	
	@After
	public void tearDown() {
		EasyMock.reset(contextInstallationMgr);
		EasyMock.reset(serviceInstallationMgr);
	}
	
}

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


package org.helianto.core.standalone;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.easymock.EasyMock;
import org.helianto.core.PostInstallationMgr;
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
		
		EasyMock.expect(postInstallationMgr.installOperator("DEFAULT", false)).andReturn(defaultOperator);
		
		defaultOperatorInstaller.setRequiredKeyTypeList(new String[] { "key1", "key2: key2Name" });
		KeyType keyType1 = new KeyType();
		EasyMock.expect(postInstallationMgr.installKey(defaultOperator, "key1")).andReturn(keyType1);
		KeyType keyType2 = new KeyType();
		EasyMock.expect(postInstallationMgr.installKey(defaultOperator, "key2")).andReturn(keyType2);
		
		defaultOperatorInstaller.setRequiredServiceList(new String[] { "service1", "service2" });
		Service service1 = new Service();
		EasyMock.expect(postInstallationMgr.installService(defaultOperator, "service1")).andReturn(service1);
		Service service2 = new Service();
		EasyMock.expect(postInstallationMgr.installService(defaultOperator, "service2")).andReturn(service2);
		
		EasyMock.replay(postInstallationMgr);
		
		defaultOperatorInstaller.afterPropertiesSet();
		assertSame(defaultOperator, namespace.getDefaultOperator());
		assertSame(keyType1, namespace.getKeyTypeMap().get("key1"));
		assertSame(keyType2, namespace.getKeyTypeMap().get("key2"));
		assertEquals("key2Name", namespace.getKeyTypeMap().get("key2").getKeyName());
		assertSame(service1, namespace.getServiceMap().get("service1"));
		assertSame(service2, namespace.getServiceMap().get("service2"));
		
	}

	private DefaultOperatorInstaller defaultOperatorInstaller;
	private NamespaceDefaults namespace;
	private PostInstallationMgr postInstallationMgr;
	
	@Before
	public void setUp() {
		defaultOperatorInstaller = new DefaultOperatorInstaller();
		namespace = new NamespaceDefaults();
		defaultOperatorInstaller.setNamespace(namespace);
		postInstallationMgr = EasyMock.createMock(PostInstallationMgr.class);
		defaultOperatorInstaller.setPostInstallationMgr(postInstallationMgr);
	}
	
	@After
	public void tearDown() {
		EasyMock.reset(postInstallationMgr);
	}
	
}

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

import static org.junit.Assert.assertSame;

import org.easymock.classextension.EasyMock;
import org.helianto.core.Entity;
import org.helianto.core.Operator;
import org.helianto.core.service.PostInstallationMgr;
import org.helianto.core.standalone.DefaultEntityInstaller;
import org.helianto.core.standalone.NamespaceDefaults;
import org.helianto.core.test.OperatorTestSupport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * @author Mauricio Fernandes de Castro
 */
public class DefaultEntityInstallerTests {
	
	@Test
	public void afterPropertiesSet() throws Exception {
		Entity defaultEntity = new Entity();
		EasyMock.expect(postInstallationMgr.installEntity(defaultOperator, "DEFAULT", false)).andReturn(defaultEntity);
		EasyMock.replay(postInstallationMgr);
		
		defaultEntityInstaller.afterPropertiesSet();
		assertSame(defaultEntity, namespace.getDefaultEntity());
		
	}

	private DefaultEntityInstaller defaultEntityInstaller;
	private NamespaceDefaults namespace;
	private PostInstallationMgr postInstallationMgr;
	private Operator defaultOperator;
	
	@Before
	public void setUp() {
		defaultEntityInstaller = new DefaultEntityInstaller();
		namespace = new NamespaceDefaults();
		defaultEntityInstaller.setNamespace(namespace);
		postInstallationMgr = EasyMock.createMock(PostInstallationMgr.class);
		defaultEntityInstaller.setPostInstallationMgr(postInstallationMgr);
		
		defaultOperator = OperatorTestSupport.createOperator();
		namespace.setDefaultOperator(defaultOperator);
	}
	
	@After
	public void tearDown() {
		EasyMock.reset(postInstallationMgr);
	}
}

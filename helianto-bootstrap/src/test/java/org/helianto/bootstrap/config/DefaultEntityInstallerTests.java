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

import org.easymock.classextension.EasyMock;
import org.helianto.bootstrap.ContextInstallationMgr;
import org.helianto.core.domain.Operator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * @author Mauricio Fernandes de Castro
 */
public class DefaultEntityInstallerTests {
	
	@Test
	public void afterPropertiesSet() throws Exception {
//		Entity defaultEntity = new Entity();
//		postInstallationMgr.installEntity(EasyMock.isA(Entity.class), EasyMock.isA(Boolean.class));
//		EasyMock.expectLastCall().andReturn(defaultEntity);
//		EasyMock.replay(postInstallationMgr);
//		
//		defaultEntityInstaller.afterPropertiesSet();
//		assertSame(defaultEntity, namespace.getDefaultEntity());
		
	}

	private DefaultEntityInstaller defaultEntityInstaller;
	private ContextDefaults namespace;
	private ContextInstallationMgr contextInstallationMgr;
	private Operator defaultOperator;
	
	@Before
	public void setUp() {
//		defaultEntityInstaller = new DefaultEntityInstaller();
//		namespace = new NamespaceDefaults();
//		defaultEntityInstaller.setNamespace(namespace);
//		postInstallationMgr = EasyMock.createMock(PostInstallationMgr.class);
//		defaultEntityInstaller.setPostInstallationMgr(postInstallationMgr);
//		
//		defaultOperator = OperatorTestSupport.createOperator();
//		namespace.setDefaultOperator(defaultOperator);
	}
	
	@After
	public void tearDown() {
//		EasyMock.reset(contextInstallationMgr);
	}
}

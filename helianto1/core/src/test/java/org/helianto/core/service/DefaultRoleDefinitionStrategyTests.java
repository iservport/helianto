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

package org.helianto.core.service;

import org.helianto.core.Entity;
import org.helianto.core.Service;
import org.helianto.core.UserGroup;
import org.helianto.core.UserRole;

import junit.framework.TestCase;

/**
 * 
 * @author Mauricio Fernandes de Castro
 */
public class DefaultRoleDefinitionStrategyTests extends TestCase {
	
	private DefaultRoleDefinitionStrategy roleDefinitionStrategy;

	public void testGrant() {
		Entity entity = new Entity();
		Service service = new Service();
		String[] extensions = new String[] {"extension0", "extension1" };

		UserGroup userGroup = roleDefinitionStrategy.grant(entity, service, extensions);
		
		assertSame(entity, userGroup.getEntity());
		assertEquals(userGroup.getUserKey(), service.getServiceName());
		assertEquals(userGroup.getIdentity().getPrincipal(), service.getServiceName());
        for (UserRole userRole: userGroup.getRoles()) {
        	assertSame(userGroup, userRole.getUserGroup());
        	assertSame(service, userRole.getService());
        	boolean hasExtension = false;
        	for (String extension: extensions) {
            	if (userRole.getServiceExtension().equals(extension)) {
            		hasExtension = true; break;
            	}
            }
        	assertTrue(hasExtension);
        }

	}
	
	@Override
	public void setUp() {
		roleDefinitionStrategy = new DefaultRoleDefinitionStrategy();
	}

}

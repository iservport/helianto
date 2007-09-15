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

/**
 * Strategy to move <code>UserRole</code> assingnment
 * away from the service facade.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface RoleDefinitionStrategy {
	
	/**
	 * Create a <code>UserGroup</code> to the required
	 * <code>Entity</code> and assign <code>UserRole</code>s
	 * to it using the supplied <code>Service</code> and extensions.
	 * 
	 * @param entity
	 * @param service
	 * @param extensions
	 */
	public UserGroup grant(Entity entity, Service service, String[] extensions);

}

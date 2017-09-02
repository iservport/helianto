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

package org.helianto.classic;

import org.helianto.core.domain.Entity;
import org.helianto.core.domain.Identity;

/**
 * Post installation tasks.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface PostInstallationMgr {

	/**
	 * Install an Entity, if one does not exist.
	 * 
	 * <p>
	 * This method is appropriate to requests coming from the presentation layer. The embedded
	 * manager identity is required and used as a signal to perform the full installation routine.
	 * </p>
	 * 
	 * @param entity
	 * @param reinstall
	 */
	public Entity installEntity(Entity entity, boolean reinstall);

	/**
	 * Install an Entity, if one does not exist.
	 * 
	 * @param entity
	 * @param manager
	 */
	public Entity installEntity(Entity entity, Identity manager);

}

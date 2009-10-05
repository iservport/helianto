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


package org.helianto.core.test;

import org.helianto.core.Entity;
import org.helianto.core.TopLevelNumberedEntity;


/**
 * Generic test support to classes implementing the 
 * <code>TopLevelNumberedEntity</code> interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class TopLevelNumberedEntityTestSupport {
	
	private static int testKey = 0;
	
	/**
	 * Create sample.
	 */
	public static <T extends TopLevelNumberedEntity> T create(Class<T> clazz) {
		Entity entity = EntityTestSupport.createEntity();
		return TopLevelNumberedEntityTestSupport.create(clazz, entity);
	}

	/**
	 * Create sample.
	 * 
	 * @param entity
	 */
	public static <T extends TopLevelNumberedEntity> T create(Class<T> clazz, Entity entity) {
		try {
			T sample = clazz.newInstance();
			sample.setKey(entity, testKey++);
			return sample;
		}
		catch (Exception e) {
			throw new RuntimeException("Test sample not created.");
		}
	}

}

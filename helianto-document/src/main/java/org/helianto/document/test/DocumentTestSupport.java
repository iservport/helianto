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

package org.helianto.document.test;

import org.helianto.core.Entity;
import org.helianto.core.test.EntityTestSupport;
import org.helianto.document.domain.Document;

/**
 * Document test support.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class DocumentTestSupport {

	private static int testKey = 0;
	
	/**
	 * Create sample.
	 */
	public static <T extends Document> T create(Class<T> clazz) {
		Entity entity = EntityTestSupport.createEntity();
		return DocumentTestSupport.create(clazz, entity);
	}

	/**
	 * Create sample.
	 * 
	 * @param entity
	 */
	public static <T extends Document> T create(Class<T> clazz, Entity entity) {
		try {
			T sample = clazz.newInstance();
			sample.setEntity(entity);
			sample.setDocCode(""+testKey++);
			return sample;
		}
		catch (Exception e) {
			throw new RuntimeException("Test sample not created.");
		}
	}

}

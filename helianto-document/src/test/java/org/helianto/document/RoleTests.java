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

package org.helianto.document;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.helianto.core.Entity;
import org.helianto.document.base.AbstractDocument;
import org.junit.Test;


/**
 * 
 * @author Mauricio Fernandes de Castro
 */
public class RoleTests {
	
	@SuppressWarnings("serial")
	@Test
	public void constructor() {
		Role function = new Role() { };
		assertTrue(function instanceof AbstractDocument);
		assertTrue(function.getParentAssociations().add(new FunctionAssociation() {}));
		assertTrue(function.getChildAssociations().add(new FunctionAssociation() {}));
	}
	
	@SuppressWarnings("serial")
	@Test
	public void entity() {
		Entity entity = new Entity();
		Role function = new Role(entity, "") { };
		assertSame(entity, function.getEntity());
	}

}

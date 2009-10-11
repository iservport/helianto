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
import org.junit.Test;


/**
 * 
 * @author Mauricio Fernandes de Castro
 */
public class FunctionTests {
	
	@SuppressWarnings("serial")
	@Test
	public void constructor() {
		AbstractFunction function = new AbstractFunction() { };
		assertTrue(function instanceof AbstractDocument);
		assertTrue(function.getParentAssociations().add(new AbstractFunctionAssociation() {}));
		assertTrue(function.getChildAssociations().add(new AbstractFunctionAssociation() {}));
	}
	
	@SuppressWarnings("serial")
	@Test
	public void entity() {
		Entity entity = new Entity();
		AbstractFunction function = new AbstractFunction(entity) { };
		assertSame(entity, function.getEntity());
	}

}

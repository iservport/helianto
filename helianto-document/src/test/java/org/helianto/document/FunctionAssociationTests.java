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

import static org.junit.Assert.*;

import org.helianto.core.AbstractAssociation;
import org.junit.Test;


/**
 * 
 * @author Mauricio Fernandes de Castro
 */
public class FunctionAssociationTests {
	
	@Test
	public void constructor() {
		AbstractFunctionAssociation assoc = new AbstractFunctionAssociation();
		assertTrue(assoc instanceof AbstractAssociation);
	}
	
	@SuppressWarnings("serial")
	@Test
	public void association() {
		AbstractFunction parent = new AbstractFunction() {};
		AbstractFunction child  = new AbstractFunction() {};
		AbstractFunctionAssociation assoc = new AbstractFunctionAssociation();
		assoc.setParent(parent);
		assoc.setChild(child);
		assertSame(parent, assoc.getParent());
		assertSame(child, assoc.getChild());
	}

}

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

package org.helianto.inventory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;

import org.helianto.core.Entity;
import org.helianto.core.number.Sequenceable;
import org.helianto.core.test.EntityTestSupport;
import org.junit.Test;


/**
 * 
 * @author Mauricio Fernandes de Castro
 */
public class PickingTests {
	
	@Test
	public void constructor()  {
		Picking picking = new Picking();
		assertTrue(picking instanceof Serializable);
		assertTrue(picking instanceof Sequenceable);
		assertEquals("", picking.getPackaging());
	}
	
	@Test
	public void pickingEquals() {
		Picking picking = new Picking();
		Picking other = new Picking();
		assertTrue(picking.equals(other));
		assertFalse(picking.equals(null));
		
		Entity entity = EntityTestSupport.createEntity();
		picking.setEntity(entity);
		picking.setInternalNumber(Long.MAX_VALUE);
		
		other.setEntity(entity);
		assertFalse(picking.equals(other));
		other.setInternalNumber(Long.MAX_VALUE);
		assertTrue(picking.equals(other));
		assertEquals(picking.hashCode(), picking.hashCode());
		picking.setInternalNumber(Long.MIN_VALUE);
		assertFalse(picking.equals(other));
		picking.setEntity(new Entity());
		assertFalse(picking.equals(other));
		
	}

}

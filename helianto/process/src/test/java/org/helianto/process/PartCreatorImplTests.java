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

package org.helianto.process;

import org.helianto.core.Entity;
import org.helianto.core.Home;
import org.helianto.core.creation.EntityCreator;
import org.helianto.core.creation.EntityCreatorImpl;

import junit.framework.TestCase;

public class PartCreatorImplTests extends TestCase {
    
    private PartCreator partCreator;
    private EntityCreator entityCreator;
    private Entity entity;
    
    public void setUp() {
        entityCreator = new EntityCreatorImpl();
        partCreator = new PartCreatorImpl();
        entity = entityCreator.entityFactory(new Home(), "ENTITY");
    }
    
    public void testPartWithDrawingFactory() {
        Part  part = partCreator.partFactory(entity, "CODE", "NAME");
        assertSame(entity, part.getEntity());
    	// TODO
    }

}

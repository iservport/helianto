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

package org.helianto.message.transform;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.util.HashMap;
import java.util.Map;

import org.helianto.core.Identity;
import org.junit.Test;

/**
 * @author Mauricio Fernandes de Castro
 */
public class ModelTests {
    
    @Test
    public void modelConstructor() {
        Identity identity = new Identity();
        Model model = new Model(identity);
        assertSame(identity, model.getRequiredModel().get(identity.getClass().getSimpleName()));
    }

    @Test
    public void modelTemplateName() {
        Identity identity = new Identity();
        Model model = new Model(identity);
        model.setTemplateName("TEST");
        assertEquals("TEST", model.getTemplateName());
    }

    @Test
    public void modelMap() {
        Identity identity = new Identity();
        Model model = new Model(identity);
        Map<String, Object> map = new HashMap<String, Object>();
        model.setModel(map);
        assertSame(map, model.getRequiredModel());
    }

}

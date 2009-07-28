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

package org.helianto.message.transform.mail;

import static org.junit.Assert.assertSame;

import org.helianto.core.Identity;
import org.helianto.core.Operator;
import org.helianto.message.transform.Model;
import org.junit.Test;

/**
 * @author Mauricio Fernandes de Castro
 */
public class ModelMailFormTests {
    
    @Test
    public void modelMailForm() {
        Operator operator = new Operator();
        ModelMailForm modelMailForm = new ModelMailFormImpl(operator);
        Model model = new Model(new Identity());
        modelMailForm.setModel(model);
        assertSame(model, modelMailForm.getModel());
    }

}

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

import org.helianto.core.Operator;
import org.helianto.message.mail.compose.DefaultMailForm;
import org.helianto.message.transform.Model;

/**
 * 
 * @author Mauricio Fernandes de Castro
 */
public class ModelMailFormImpl extends DefaultMailForm implements ModelMailForm {
    
    /**
     * Minimal constructor.
     * @param operator
     */
    public ModelMailFormImpl(Operator operator) {
        super(operator);
    }

    private Model model;

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

}

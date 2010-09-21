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
package org.helianto.partner.validation;

import org.helianto.core.validation.AbstractSessionPropertyEditor;
import org.helianto.partner.Laboratory;
import org.springframework.stereotype.Component;

/**
 * Default <code>Laboratory</code> property editor.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Component("laboratoryPropertyEditor")
public class LaboratoryPropertyEditor extends AbstractSessionPropertyEditor {
    
    @Override
    public String getAsText() {
        Laboratory laboratory = (Laboratory) getValue();
        return String.valueOf(laboratory.getPrivateEntity().getPartnerAlias());
    }
    
    @Override
    public void setAsText(String id) throws IllegalArgumentException {
        setAsText(id, Laboratory.class);
    }

}


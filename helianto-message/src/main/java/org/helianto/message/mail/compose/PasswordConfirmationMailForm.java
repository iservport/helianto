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

package org.helianto.message.mail.compose;

import org.helianto.core.domain.Credential;
import org.helianto.core.domain.Operator;

/**
 * Password confirmation mail form.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class PasswordConfirmationMailForm extends DefaultMailForm {
    
    private Credential credential;

    public PasswordConfirmationMailForm(Operator operator) {
        super(operator);
    }
    
    public PasswordConfirmationMailForm() {
        super();
    }
    
    public Credential getCredential() {
        return credential;
    }

    public void setCredential(Credential credential) {
        this.credential = credential;
    }

}

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

package org.helianto.core.validate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.helianto.core.User;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Basic <code>User</code> validator.
 * 
 * @author Mauricio Fernandes de Castro
 * @version $ Id: $
 */
public class UserValidator extends CredentialValidator implements Validator {
    
    private final Log logger = LogFactory.getLog(getClass());

    public boolean supports(Class clazz) {
        return User.class.equals(clazz);
    }

    public void validate(Object obj, Errors errors) {
        if (obj==null) {
            errors.reject("user.error.nullpointer", "Null user received");
        } else {
            User user = (User) obj;
        }
    }
    

}

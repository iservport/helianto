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

import junit.framework.TestCase;

import org.helianto.core.Credential;
import org.helianto.core.Entity;
import org.helianto.core.User;
import org.helianto.core.UserCreatorImpl;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;

public class UserValidatorTests extends TestCase {
    
    private Validator userValidator;
    private BindException errors;
    private User user;
    
    public void setUp() {
        Credential credential = new UserCreatorImpl().credentialFactory();
        user = new UserCreatorImpl().userFactory(new Entity(), credential);
        userValidator = new UserValidator();
        errors = new BindException(user, "user");
    }
    
    public void testValidateSupports() {
        assertTrue(userValidator.supports(User.class));
    }
    
    public void testValidateNullUser() {
        userValidator.validate(null, errors);
        for (Object object : errors.getAllErrors()) {
            assertEquals("user.error.nullpointer", 
                    ((ObjectError) object).getCode());
        }
    }

    public void testValidateEmptyPrincipal() {
        userValidator.validate(user, errors);
        for (Object object : errors.getAllErrors()) {
            assertEquals("credential.error.whitespace", 
                    ((ObjectError) object).getCode());
        }
    }

//    public void testValidatePrincipalInvalidChar() {
//        user.getCredential().setPrincipal("~");
//        userValidator.validate(user, errors);
//        for (Object object : errors.getAllErrors()) {
//            assertEquals("credential.error.whitespace", 
//                    ((ObjectError) object).getCode());
//        }
//    }

}

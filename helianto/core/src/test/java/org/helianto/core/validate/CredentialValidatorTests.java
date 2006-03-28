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
import org.helianto.core.UserCreatorImpl;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;

public class CredentialValidatorTests extends TestCase {
    
    private Validator credentialValidator;
    private BindException errors;
    private Credential credential;
    
    public void setUp() {
        credential = new UserCreatorImpl().credentialFactory();
        credentialValidator = new CredentialValidator();
        errors = new BindException(credential, "credential");
    }
    
    public void testValidateSupports() {
        assertTrue(credentialValidator.supports(Credential.class));
    }
    
    public void testValidateNullCredential() {
        credentialValidator.validate(null, errors);
        for (Object object : errors.getAllErrors()) {
            assertEquals("credential.error.nullpointer", 
                    ((ObjectError) object).getCode());
        }
    }

    public void testValidateEmptyPrincipal() {
        credentialValidator.validate(credential, errors);
        for (Object object : errors.getAllErrors()) {
            assertEquals("credential.error.empty", 
                    ((ObjectError) object).getCode());
        }
    }

    public void testValidateNullPrincipal() {
        credential.setPrincipal(null);
        credentialValidator.validate(credential, errors);
        for (Object object : errors.getAllErrors()) {
            assertEquals("credential.error.empty", 
                    ((ObjectError) object).getCode());
        }
    }

    public void testValidatePrincipalInvalidChar() {
        credential.setPrincipal("+");
        credentialValidator.validate(credential, errors);
        for (Object object : errors.getAllErrors()) {
            assertEquals("credential.error.invalidchar", 
                    ((ObjectError) object).getCode());
        }
    }

}

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

package org.helianto.core.validation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.helianto.core.Credential;
import org.helianto.core.Identity;
import org.junit.Before;
import org.junit.Test;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;

public class CredentialValidatorTests {
    
    @Test
    public void validateSupports() {
        assertTrue(credentialValidator.supports(Credential.class));
    }
    
    @Test
    public void validateNullCredential() {
        credentialValidator.validate(null, errors);
        checkRequiredErrors(errors,"credential.error.nullpointer");
    }

    @Test
    public void passwordNull() {
        credential.setPasswordDirty(true);
        credential.setPassword(null);
        credentialValidator.validate(credential, errors);
        checkRequiredErrors(errors,"credential.error.empty");
    }
    
    @Test
    public void passwordEmpty() {
        credential.setPasswordDirty(true);
        credential.setPassword("");
        credentialValidator.validate(credential, errors);
        checkRequiredErrors(errors,"credential.error.empty");
    }
    
    @Test
    public void passwordNotTooLong() {
        credential.setPasswordDirty(true);
        credential.setPassword(sixteen+"1234");
        credential.setVerifyPassword(sixteen+"1234");
        credentialValidator.validate(credential, errors);
        assertEquals(0, errors.getAllErrors().size());
    }
    
    @Test
    public void passwordTooLong() {
        credential.setPasswordDirty(true);
        credential.setPassword(sixteen+"12345");
        credentialValidator.validate(credential, errors);
        checkRequiredErrors(errors,"credential.error.passwordtoolong");
    }
    
    @Test
    public void passwordNotTooShort() {
        credential.setPasswordDirty(true);
        credential.setPassword("123456");
        credential.setVerifyPassword("123456");
        credentialValidator.validate(credential, errors);
        assertEquals(0, errors.getAllErrors().size());
    }
    
    @Test
    public void passwordTooShort() {
        credential.setPasswordDirty(true);
        credential.setPassword("12345");
        credentialValidator.validate(credential, errors);
        checkRequiredErrors(errors,"credential.error.passwordtooshort");
    }
    
    @Test
    public void verifyPasswordNull() {
        credential.setPasswordDirty(true);
        credential.setPassword("123456");
        credential.setVerifyPassword(null);
        credentialValidator.validate(credential, errors);
        checkRequiredErrors(errors,"credential.error.emptyverify");
    }
    
    @Test
    public void verifyPasswordEmpty() {
        credential.setPasswordDirty(true);
        credential.setPassword("123456");
        credential.setVerifyPassword("");
        credentialValidator.validate(credential, errors);
        checkRequiredErrors(errors,"credential.error.emptyverify");
    }
    
    @Test
    public void validPassword() {
        credential.setPasswordDirty(true);
        credential.setPassword("abcdef");
        credential.setVerifyPassword("AbCdEf");
        credentialValidator.validate(credential, errors);
        assertEquals(0, errors.getAllErrors().size());
    }
    
    @Test
    public void invalidPassword() {
        credential.setPasswordDirty(true);
        credential.setPassword("123456");
        credential.setVerifyPassword("12345");
        credentialValidator.validate(credential, errors);
        checkRequiredErrors(errors,"credential.error.passworderror");
    }
    
    @Test
    public void passwordWhiteSpace() {
        credential.setPasswordDirty(true);
        credential.setPassword("123 \n\t");
        credential.setVerifyPassword("123 \n\t");
        credentialValidator.validate(credential, errors);
        checkRequiredErrors(errors,"credential.error.whitespace");
    }
    
    // helper methods
    
    private void checkRequiredErrors(BindException errors, String code) {
        logger.debug("Check required errors ");
        boolean hasErrors = false;
        for (Object object : errors.getAllErrors()) {
            hasErrors = true;
            ObjectError objectError = (ObjectError) object;
            assertEquals(code, objectError.getCode());
            logger.debug("\n{}={}", objectError.getCode(), objectError.getDefaultMessage());
        }
        assertTrue(hasErrors);
    }
    
    static String sixteen = "abcdefghijklmnop";

    // collabs 
    
    private Validator credentialValidator;
    private BindException errors;
    private Credential credential;
    
    @Before
    public void setUp() {
        Identity identity = new Identity();
        identity.setPrincipal("ABC");
        credential = new Credential(identity, "empty");
        credentialValidator = new CredentialValidator();
        errors = new BindException(credential, "credential");
    }
    
    private static final Logger logger = LoggerFactory.getLogger(CredentialValidatorTests.class);
    
}

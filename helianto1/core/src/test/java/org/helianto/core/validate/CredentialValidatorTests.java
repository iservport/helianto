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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.helianto.core.Credential;
import org.helianto.core.Identity;
import org.helianto.core.creation.AuthenticationCreator;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;

public class CredentialValidatorTests extends TestCase {
    
    // fields 
    
    private final Log logger = LogFactory.getLog(getClass());
    
    private Validator credentialValidator;
    private BindException errors;
    private Credential credential;
    
    // setup
    
    public void setUp() {
        Identity identity = new Identity();
        identity.setPrincipal("ABC");
        credential = AuthenticationCreator.credentialFactory(identity, "empty");
        credentialValidator = new CredentialValidator();
        errors = new BindException(credential, "credential");
    }
    
    // tests
    
    public void testValidateSupports() {
        assertTrue(credentialValidator.supports(Credential.class));
    }
    
    public void testValidateNullCredential() {
        credentialValidator.validate(null, errors);
        checkRequiredErrors(errors,"credential.error.nullpointer");
    }

    public void testPasswordNull() {
        credential.setPasswordDirty(true);
        credential.setPassword(null);
        credentialValidator.validate(credential, errors);
        checkRequiredErrors(errors,"credential.error.empty");
    }
    
    public void testPasswordEmpty() {
        credential.setPasswordDirty(true);
        credential.setPassword("");
        credentialValidator.validate(credential, errors);
        checkRequiredErrors(errors,"credential.error.empty");
    }
    
    public void testPasswordNotTooLong() {
        credential.setPasswordDirty(true);
        credential.setPassword(sixteen+"1234");
        credential.setVerifyPassword(sixteen+"1234");
        credentialValidator.validate(credential, errors);
        assertEquals(0, errors.getAllErrors().size());
    }
    
    public void testPasswordTooLong() {
        credential.setPasswordDirty(true);
        credential.setPassword(sixteen+"12345");
        credentialValidator.validate(credential, errors);
        checkRequiredErrors(errors,"credential.error.passwordtoolong");
    }
    
    public void testPasswordNotTooShort() {
        credential.setPasswordDirty(true);
        credential.setPassword("123456");
        credential.setVerifyPassword("123456");
        credentialValidator.validate(credential, errors);
        assertEquals(0, errors.getAllErrors().size());
    }
    
    public void testPasswordTooShort() {
        credential.setPasswordDirty(true);
        credential.setPassword("12345");
        credentialValidator.validate(credential, errors);
        checkRequiredErrors(errors,"credential.error.passwordtooshort");
    }
    
    public void testVerifyPasswordNull() {
        credential.setPasswordDirty(true);
        credential.setPassword("123456");
        credential.setVerifyPassword(null);
        credentialValidator.validate(credential, errors);
        checkRequiredErrors(errors,"credential.error.emptyverify");
    }
    
    public void testVerifyPasswordEmpty() {
        credential.setPasswordDirty(true);
        credential.setPassword("123456");
        credential.setVerifyPassword("");
        credentialValidator.validate(credential, errors);
        checkRequiredErrors(errors,"credential.error.emptyverify");
    }
    
    public void testValidPassword() {
        credential.setPasswordDirty(true);
        credential.setPassword("abcdef");
        credential.setVerifyPassword("AbCdEf");
        credentialValidator.validate(credential, errors);
        assertEquals(0, errors.getAllErrors().size());
    }
    
    public void testInvalidPassword() {
        credential.setPasswordDirty(true);
        credential.setPassword("123456");
        credential.setVerifyPassword("12345");
        credentialValidator.validate(credential, errors);
        checkRequiredErrors(errors,"credential.error.passworderror");
    }
    
    public void testPasswordWhiteSpace() {
        credential.setPasswordDirty(true);
        credential.setPassword("123 \n\t");
        credential.setVerifyPassword("123 \n\t");
        credentialValidator.validate(credential, errors);
        checkRequiredErrors(errors,"credential.error.whitespace");
    }
    
    // helper methods
    
    private void checkRequiredErrors(BindException errors, String code) {
        if (logger.isDebugEnabled()) {
            logger.debug("Check required errors ");
        }
        boolean hasErrors = false;
        for (Object object : errors.getAllErrors()) {
            hasErrors = true;
            ObjectError objectError = (ObjectError) object;
            assertEquals(code, objectError.getCode());
            if (logger.isDebugEnabled()) {
                logger.debug("\n"+objectError.getCode()+"="+objectError.getDefaultMessage());
            }
        }
        assertTrue(hasErrors);
    }
    
    static String sixteen = "abcdefghijklmnop";

}

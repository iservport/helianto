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
import org.helianto.core.Identity;
import org.junit.Before;
import org.junit.Test;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;

public class IdentityValidatorTests {
    
    // fields 
    
    private final Logger logger = LoggerFactory.getLogger(getClass());
    
    private Validator identityValidator;
    private BindException errors;
    private Identity identity;
    
    // setup
    
    @Before
    public void setUp() {
        identity = new Identity();
        identity.setPrincipal("ABC");
        identityValidator = new IdentityValidator();
        errors = new BindException(identity, "identity");
    }
    
    // tests
    
    @Test
    public void validateSupports() {
        assertTrue(identityValidator.supports(Identity.class));
    }
    
    @Test
    public void validateNullCredential() {
        identityValidator.validate(null, errors);
        checkRequiredErrors(errors,"identity.error.nullpointer");
    }

    @Test
    public void validateEmptyPrincipal() {
        identity.setPrincipal("");
        identityValidator.validate(identity, errors);
        checkRequiredErrors(errors,"identity.error.empty");
    }

    @Test
    public void validateNullPrincipal() {
        identity.setPrincipal(null);
        identityValidator.validate(identity, errors);
        checkRequiredErrors(errors,"identity.error.empty");
    }

    @Test
    public void validatePrincipalWhiteSpace() {
        identity.setPrincipal(" \n\t");
        identityValidator.validate(identity, errors);
        checkRequiredErrors(errors,"identity.error.whitespace");
    }

//    @Test
//    public void validatePrincipalInvalidChar() {
//        String invalidChar = "!#$%&*()-=+����?{}[]�`'|,;/<>:~^�*?\"\'\\";
//        for (char c : invalidChar.toCharArray()) {
//            errors = new BindException(identity, "identity");
//            identity.setPrincipal(String.valueOf(c));
//            identityValidator.validate(identity, errors);
//            checkRequiredErrors(errors,"identity.error.invalidchar");
//        }
//    }
    
    @Test
    public void validatePrincipalValidChar() {
        String validChar = "@._1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (char c : validChar.toCharArray()) {
            errors = new BindException(identity, "identity");
            identity.setPrincipal(String.valueOf(c));
            identityValidator.validate(identity, errors);
            assertEquals(0, errors.getAllErrors().size());
        }
    }
    
    @Test
    public void principalNotTooLong() {
        identity.setPrincipal(sixteen+sixteen+sixteen+sixteen);
        identityValidator.validate(identity, errors);
        assertEquals(0, errors.getAllErrors().size());
    }
    
    @Test
    public void principalTooLong() {
        identity.setPrincipal(sixteen+sixteen+sixteen+sixteen+"1");
        identityValidator.validate(identity, errors);
        checkRequiredErrors(errors,"identity.error.principaltoolong");
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

}

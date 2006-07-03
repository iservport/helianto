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
import org.helianto.core.Identity;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;

public class IdentityValidatorTests extends TestCase {
    
    // fields 
    
    private final Log logger = LogFactory.getLog(getClass());
    
    private Validator identityValidator;
    private BindException errors;
    private Identity identity;
    
    // setup
    
    public void setUp() {
        identity = new Identity();
        identity.setPrincipal("ABC");
        identityValidator = new IdentityValidator();
        errors = new BindException(identity, "identity");
    }
    
    // tests
    
    public void testValidateSupports() {
        assertTrue(identityValidator.supports(Identity.class));
    }
    
    public void testValidateNullCredential() {
        identityValidator.validate(null, errors);
        checkRequiredErrors(errors,"identity.error.nullpointer");
    }

    public void testValidateEmptyPrincipal() {
        identity.setPrincipal("");
        identityValidator.validate(identity, errors);
        checkRequiredErrors(errors,"identity.error.empty");
    }

    public void testValidateNullPrincipal() {
        identity.setPrincipal(null);
        identityValidator.validate(identity, errors);
        checkRequiredErrors(errors,"identity.error.empty");
    }

    public void testValidatePrincipalWhiteSpace() {
        identity.setPrincipal(" \n\t");
        identityValidator.validate(identity, errors);
        checkRequiredErrors(errors,"identity.error.whitespace");
    }

    public void testValidatePrincipalInvalidChar() {
        String invalidChar = "!#$%&*()-=+§£¢¬?{}[]°`'|,;/<>:~^°*?\"\'\\";
        for (char c : invalidChar.toCharArray()) {
            errors = new BindException(identity, "identity");
            identity.setPrincipal(String.valueOf(c));
            identityValidator.validate(identity, errors);
            checkRequiredErrors(errors,"identity.error.invalidchar");
        }
    }
    
    public void testValidatePrincipalValidChar() {
        String validChar = "@._1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (char c : validChar.toCharArray()) {
            errors = new BindException(identity, "identity");
            identity.setPrincipal(String.valueOf(c));
            identityValidator.validate(identity, errors);
            assertEquals(0, errors.getAllErrors().size());
        }
    }
    
    public void testPrincipalNotTooLong() {
        identity.setPrincipal(sixteen+sixteen+sixteen+sixteen);
        identityValidator.validate(identity, errors);
        assertEquals(0, errors.getAllErrors().size());
    }
    
    public void testPrincipalTooLong() {
        identity.setPrincipal(sixteen+sixteen+sixteen+sixteen+"1");
        identityValidator.validate(identity, errors);
        checkRequiredErrors(errors,"identity.error.principaltoolong");
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

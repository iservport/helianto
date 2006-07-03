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
import org.helianto.core.Credential;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Basic <code>Credential</code> validator.
 * 
 * <p><code>Credential principal</code> should not be empty (or null),
 * should be maximum 64 chars long, no white space, and have only digits, letters
 * or <code>'@' '.'</code> or <code>'_'</code>. </p>
 * 
 * <p><code>Credential password</code> is only checked if <code>isPasswordDirty</code> is set.
 * It should not be empty (or null), be 6 to 20 chars long, no white space. 
 * <code>Credential password</code> and <code>Credential verifyPassword</code> must match. </p>
 * 
 * @author Mauricio Fernandes de Castro
 * @version $Id$
 */
public class CredentialValidator implements Validator {
    
    private final Log logger = LogFactory.getLog(getClass());

    public boolean supports(Class clazz) {
        return Credential.class.equals(clazz);
    }

    public void validate(Object obj, Errors errors) {
        if (obj==null) {
            errors.reject("credential.error.nullpointer", "Null Credential received");
        } else {
            Credential credential = (Credential) obj;
            validatePassword(credential, errors);
        }
    }
    
    public void validatePassword(Credential credential, Errors errors) {
        if (credential.isPasswordDirty()) {
            if (logger.isDebugEnabled()) {
                logger.debug("Validating Credential password");
            }
            String password = credential.getPassword();
            String verifyPassword = credential.getVerifyPassword();
            if (password == null || password.length() == 0) {
                if (logger.isDebugEnabled()) {
                    logger.debug("Credential password should not be empty");
                }
                errors.rejectValue("password", 
                        "credential.error.empty", 
                        "Credential password should not be empty");
            } else {
                if (password.length()>20) {
                    if (logger.isDebugEnabled()) {
                        logger.debug("Credential password lenght should not exceed 20 chars");
                    }
                    errors.rejectValue("password", 
                            "credential.error.passwordtoolong", 
                            "Credential password lenght should not exceed 20 chars");
                } else if (password.length()<6) {
                    if (logger.isDebugEnabled()) {
                        logger.debug("Credential password lenght should be at least 6 chars");
                    }
                    errors.rejectValue("password", 
                            "credential.error.passwordtooshort", 
                            "Credential password lenght should be at least 6 chars");
                } else if (verifyPassword == null || verifyPassword.length() == 0) {
                    if (logger.isDebugEnabled()) {
                        logger.debug("Credential password verification should not be empty");
                    }
                    errors.rejectValue("verifyPassword", 
                            "credential.error.emptyverify", 
                            "Credential password verification should not be empty");
                } else {
                    if (password.compareToIgnoreCase(verifyPassword)!=0) {
                        if (logger.isDebugEnabled()) {
                            logger.debug("Credential password and verification do not match");
                        }
                        errors.rejectValue("password", 
                                "credential.error.passworderror", 
                                "Credential password and verification do not match");
                    } else {
                        for (char c : password.toCharArray()) {
                            if (Character.isWhitespace(c)) {
                                if (logger.isDebugEnabled()) {
                                    logger.debug("Credential principal or password should not have whitespace");
                                }
                                errors.rejectValue("password", 
                                        "credential.error.whitespace", 
                                        "Credential password should not have whitespace");
                            }
                        }
                    }
                }
            }
        }
    }

}

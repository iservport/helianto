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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.helianto.core.Identity;
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
public class IdentityValidator implements Validator {
    
    private final Log logger = LogFactory.getLog(getClass());

    @SuppressWarnings("unchecked")
	public boolean supports(Class clazz) {
        return Identity.class.equals(clazz);
    }

    public void validate(Object obj, Errors errors) {
        if (obj==null) {
            errors.reject("identity.error.nullpointer", "Null Credential received");
        } else {
            Identity identity = (Identity) obj;
            validatePrincipal(identity, errors);
        }
    }
    
    public void validatePrincipal(Identity identity, Errors errors) {
        if (logger.isDebugEnabled()) {
            logger.debug("Validating Credential principal");
        }
        String principal = identity.getPrincipal();
        if (principal == null || principal.length() == 0) {
            if (logger.isDebugEnabled()) {
                logger.debug("Identity principal should not be empty");
            }
            errors.rejectValue("principal", 
                    "identity.error.empty", 
                    "Identity principal should not be empty");
        } else {
            for (char c : principal.toCharArray()) {
                if (Character.isWhitespace(c)) {
                    if (logger.isDebugEnabled()) {
                        logger.debug("Identity principal should not have whitespace");
                    }
                    errors.rejectValue("principal", 
                            "identity.error.whitespace", 
                            "Identity principal should not have whitespace");
                } else if (!Character.isLetterOrDigit(c) && (c!='@' & c!='.' & c!='_')) {
                    if (logger.isDebugEnabled()) {
                        logger.debug("Identity principal should not include invalid char " + c);
                    }
                    errors.rejectValue("principal", 
                            "identity.error.invalidchar", new Object[] { c },
                            "Identity principal should not include invalid char $1 " + c);
                }
            }
            if (principal.length()>64) {
                if (logger.isDebugEnabled()) {
                    logger.debug("Identity principal lenght should not exceed 64 chars");
                }
                errors.rejectValue("principal", 
                        "identity.error.principaltoolong", 
                        "Identity principal lenght should not exceed 64 chars");
            }
        }
    }
    
}

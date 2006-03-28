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
            validatePrincipal(credential, errors);
        }
    }
    
    public void validatePrincipal(Credential credential, Errors errors) {
        if (logger.isDebugEnabled()) {
            logger.debug("Validating Credential principal");
        }
        String principal = credential.getPrincipal();
        if (principal == null || principal.length() == 0) {
            errors.rejectValue("principal", 
                    "credential.error.empty", 
                    "Credential principal should not be empty or have whitespace");
        } else {
            for (char c : principal.toCharArray()) {
                if (Character.isWhitespace(c)) {
                    errors.rejectValue("principal", 
                            "credential.error.whitespace", 
                            "Credential principal should include invalid char " + c);
                }
                if (!Character.isLetterOrDigit(c)) {
                    errors.rejectValue("principal", 
                            "credential.error.invalidchar", 
                            "Credential principal should include invalid char " + c);
                }
                
            }
        }
    }

}

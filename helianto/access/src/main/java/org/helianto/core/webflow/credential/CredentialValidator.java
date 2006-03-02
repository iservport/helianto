//Created on 12/07/2005
package org.helianto.core.webflow.credential;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import org.helianto.core.Credential;
import org.helianto.core.CredentialType;

public class CredentialValidator implements Validator {

    private final Log logger = LogFactory.getLog(CredentialValidator.class);
    
    public boolean supports(Class clazz) {
        return clazz.equals(Credential.class);
    }

    public void validate(Object obj, Errors errors) {
        Credential form = (Credential) obj;
        if (logger.isDebugEnabled()) {
            logger.debug("Credential validation started");
        }
        validateEmail(form, errors);
        validateNames(form, errors);
        if (logger.isDebugEnabled()) {
            logger.debug("Credential validation ended");
        }
    }

    public void validateEmail(Credential form, Errors errors) {
        if (form.getCredentialType()!=CredentialType.NOT_ADDRESSABLE.getValue() 
                && form.getPrincipal().indexOf('@')==0) {
            errors.rejectValue("principal", null, "Addressable principal should have '@'");
            if (logger.isDebugEnabled()) {
                logger.debug("valid addressable principal should have '@'");
            }
        }
    }

    public void validateNames(Credential form, Errors errors) {
        if (form.getPersonalData().getFirstName().length()>24) {
            errors.rejectValue("personalData.firstName", null, "First name should not have more than 24 char");
            if (logger.isDebugEnabled()) {
                logger.debug("First name too long");
            }
        }
        if (form.getPersonalData().getLastName().length()>32) {
            errors.rejectValue("personalData.lastName", null, "Last name should not have more than 32 char");
            if (logger.isDebugEnabled()) {
                logger.debug("Last name too long");
            }
        }
    }

}

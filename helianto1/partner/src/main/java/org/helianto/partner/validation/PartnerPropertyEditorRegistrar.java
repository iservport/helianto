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
package org.helianto.partner.validation;

import java.beans.PropertyEditor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.helianto.core.validate.AbstractLoaderPropertyEditorRegistrar;
import org.helianto.partner.Account;
import org.helianto.partner.Address;
import org.helianto.partner.Contact;
import org.helianto.partner.Partner;
import org.helianto.partner.PartnerAssociation;
import org.helianto.partner.PartnerAssociationFilter;
import org.helianto.partner.PartnerKey;
import org.helianto.partner.Phone;
import org.springframework.beans.PropertyEditorRegistry;

/**
 * <code>Partner</code> <code>PropertyLoader</code> backed <code>Address</code> property editor.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class PartnerPropertyEditorRegistrar extends AbstractLoaderPropertyEditorRegistrar {

	public void registerCustomEditors(PropertyEditorRegistry registry) {
		
		// address or contact
        PropertyEditor AddressPropertyEditor = new AddressPropertyEditor(getPropertyLoader());
        if (logger.isDebugEnabled()) {
            logger.debug("Registering custom editor "+AddressPropertyEditor);
        }
        registry.registerCustomEditor(Address.class, AddressPropertyEditor);
        registry.registerCustomEditor(Contact.class, AddressPropertyEditor);
        
        // partnerAssociation
        PropertyEditor PartnerAssociationPropertyEditor = new PartnerAssociationPropertyEditor(getPropertyLoader());
        if (logger.isDebugEnabled()) {
            logger.debug("Registering custom editor "+PartnerAssociationPropertyEditor);
        }
        registry.registerCustomEditor(PartnerAssociation.class, PartnerAssociationPropertyEditor);
        
        // partnerKey
        PropertyEditor PartnerKeyPropertyEditor = new PartnerKeyPropertyEditor(getPropertyLoader());
        if (logger.isDebugEnabled()) {
            logger.debug("Registering custom editor "+PartnerKeyPropertyEditor);
        }
        registry.registerCustomEditor(PartnerKey.class, PartnerKeyPropertyEditor);
        
        // partner
        PropertyEditor PartnerPropertyEditor = new PartnerPropertyEditor(getPropertyLoader());
        if (logger.isDebugEnabled()) {
            logger.debug("Registering custom editor "+PartnerPropertyEditor);
        }
        registry.registerCustomEditor(Partner.class, PartnerPropertyEditor);
        
        // phone
        PropertyEditor PhonePropertyEditor = new PhonePropertyEditor(getPropertyLoader());
        if (logger.isDebugEnabled()) {
            logger.debug("Registering custom editor "+PhonePropertyEditor);
        }
        registry.registerCustomEditor(Phone.class, PhonePropertyEditor);

        // PartnerAssociationFilter
        PropertyEditor PartnerAssociationFilterPropertyEditor = new PartnerAssociationFilterPropertyEditor(getPropertyLoader());
        if (logger.isDebugEnabled()) {
            logger.debug("Registering custom editor "+PartnerAssociationFilterPropertyEditor);
        }
        registry.registerCustomEditor(PartnerAssociationFilter.class, PartnerAssociationFilterPropertyEditor);

        // Account
        PropertyEditor AccountPropertyEditor = new AccountPropertyEditor(getPropertyLoader());
        if (logger.isDebugEnabled()) {
            logger.debug("Registering custom editor "+AccountPropertyEditor);
        }
        registry.registerCustomEditor(Account.class, AccountPropertyEditor);

    }

    public static final Log logger = LogFactory.getLog(PartnerPropertyEditorRegistrar.class);

}

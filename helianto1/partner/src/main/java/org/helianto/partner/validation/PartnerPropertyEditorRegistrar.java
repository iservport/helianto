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
import org.helianto.partner.Agent;
import org.helianto.partner.Contact;
import org.helianto.partner.Customer;
import org.helianto.partner.Partner;
import org.helianto.partner.PartnerAssociation;
import org.helianto.partner.PartnerAssociationFilter;
import org.helianto.partner.PartnerKey;
import org.helianto.partner.Phone;
import org.helianto.partner.Supplier;
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
        PropertyEditor partnerAssociationPropertyEditor = new PartnerAssociationPropertyEditor(getPropertyLoader());
        if (logger.isDebugEnabled()) {
            logger.debug("Registering custom editor "+partnerAssociationPropertyEditor);
        }
        registry.registerCustomEditor(PartnerAssociation.class, partnerAssociationPropertyEditor);
        
        // partnerKey
        PropertyEditor partnerKeyPropertyEditor = new PartnerKeyPropertyEditor(getPropertyLoader());
        if (logger.isDebugEnabled()) {
            logger.debug("Registering custom editor "+partnerKeyPropertyEditor);
        }
        registry.registerCustomEditor(PartnerKey.class, partnerKeyPropertyEditor);
        
        // partner
        PropertyEditor partnerPropertyEditor = new PartnerPropertyEditor(getPropertyLoader());
        if (logger.isDebugEnabled()) {
            logger.debug("Registering custom editor "+partnerPropertyEditor);
        }
        registry.registerCustomEditor(Partner.class, partnerPropertyEditor);
        
        // Agent
        PropertyEditor agentPropertyEditor = new AgentPropertyEditor(getPropertyLoader());
        if (logger.isDebugEnabled()) {
            logger.debug("Registering custom editor "+agentPropertyEditor);
        }
        registry.registerCustomEditor(Agent.class, agentPropertyEditor);
        
        // Customer
        PropertyEditor customerPropertyEditor = new CustomerPropertyEditor(getPropertyLoader());
        if (logger.isDebugEnabled()) {
            logger.debug("Registering custom editor "+customerPropertyEditor);
        }
        registry.registerCustomEditor(Customer.class, customerPropertyEditor);
        
        // Supplier
        PropertyEditor supplierPropertyEditor = new SupplierPropertyEditor(getPropertyLoader());
        if (logger.isDebugEnabled()) {
            logger.debug("Registering custom editor "+supplierPropertyEditor);
        }
        registry.registerCustomEditor(Supplier.class, supplierPropertyEditor);

        // phone
        PropertyEditor phonePropertyEditor = new PhonePropertyEditor(getPropertyLoader());
        if (logger.isDebugEnabled()) {
            logger.debug("Registering custom editor "+phonePropertyEditor);
        }
        registry.registerCustomEditor(Phone.class, phonePropertyEditor);

        // PartnerAssociationFilter
        PropertyEditor partnerAssociationFilterPropertyEditor = new PartnerAssociationFilterPropertyEditor(getPropertyLoader());
        if (logger.isDebugEnabled()) {
            logger.debug("Registering custom editor "+partnerAssociationFilterPropertyEditor);
        }
        registry.registerCustomEditor(PartnerAssociationFilter.class, partnerAssociationFilterPropertyEditor);

        // Account
        PropertyEditor accountPropertyEditor = new AccountPropertyEditor(getPropertyLoader());
        if (logger.isDebugEnabled()) {
            logger.debug("Registering custom editor "+accountPropertyEditor);
        }
        registry.registerCustomEditor(Account.class, accountPropertyEditor);

    }

    public static final Log logger = LogFactory.getLog(PartnerPropertyEditorRegistrar.class);

}

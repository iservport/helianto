package org.helianto.core.creation;

import java.util.Date;

import org.helianto.core.Contact;
import org.helianto.core.Credential;
import org.helianto.core.Customer;
import org.helianto.core.Division;
import org.helianto.core.Entity;
import org.helianto.core.Partner;
import org.helianto.core.Supplier;

public class PartnerCreatorImpl extends EntityCreatorImpl implements PartnerCreator {
	
	private Partner partnerFactory(Entity entity, String alias) {
		Partner partner = new Partner();
		partner.setEntity(entity);
		partner.setAlias(alias);
		partner.setRelatedSince(new Date());
		partner.setState(PartnerState.ACTIVE.getValue());
		partner.setStrong(false);
		return partner;
	}

    public Customer customerFactory(Entity entity, String alias) {
    	return (Customer) partnerFactory(entity, alias);
    }
    
    public Supplier supplierFactory(Entity entity, String alias) {
    	return (Supplier) partnerFactory(entity, alias);
    }
    
    public Division divisionFactory(Entity entity, String alias) {
    	return (Division) partnerFactory(entity, alias);
    }
    
    public Contact contactFactory(Partner partner, Credential cred) {
        Contact cntct = new Contact();
        cntct.setPartner(partner);
        cntct.setCredential(cred);
        return cntct;
    }

}

package org.helianto.core.creation;

import java.util.Date;

import org.helianto.core.Agent;
import org.helianto.core.Bank;
import org.helianto.core.Contact;
import org.helianto.core.Credential;
import org.helianto.core.Customer;
import org.helianto.core.Division;
import org.helianto.core.Entity;
import org.helianto.core.Manufacturer;
import org.helianto.core.Partner;
import org.helianto.core.Supplier;

public class PartnerCreatorImpl extends EntityCreatorImpl implements PartnerCreator {
	
    private Partner partnerFactory(Class clazz, Entity entity, String alias) {
        Partner partner;
        try {
            partner = (Partner) clazz.newInstance();
            partner.setEntity(entity);
            partner.setAlias(alias);
            partner.setRelatedSince(new Date());
            partner.setState(PartnerState.ACTIVE.getValue());
            partner.setStrong(false);
            return partner;
        } catch (Exception e) {
            throw new RuntimeException("Can't instantiate "+clazz);
        }
    }

    public Customer customerFactory(Entity entity, String alias) {
    	return (Customer) partnerFactory(Customer.class, entity, alias);
    }
    
    public Supplier supplierFactory(Entity entity, String alias) {
    	return (Supplier) partnerFactory(Supplier.class, entity, alias);
    }
    
    public Division divisionFactory(Entity entity, String alias) {
    	return (Division) partnerFactory(Division.class, entity, alias);
    }
    
    public Bank bankFactory(Entity entity, String alias) {
        return (Bank) partnerFactory(Bank.class, entity, alias);
    }

    public Agent agentFactory(Entity entity, String alias) {
        return (Agent) partnerFactory(Agent.class, entity, alias);
    }

    public Manufacturer manufacturerFactory(Entity entity, String alias) {
        return (Manufacturer) partnerFactory(Manufacturer.class, entity, alias);
    }

    public Contact contactFactory(Partner partner, Credential cred) {
        Contact cntct = new Contact();
        cntct.setPartner(partner);
        cntct.setCredential(cred);
        return cntct;
    }

}

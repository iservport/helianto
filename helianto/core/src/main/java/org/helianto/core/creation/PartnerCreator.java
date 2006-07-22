package org.helianto.core.creation;

import java.util.Date;

import org.helianto.core.Agent;
import org.helianto.core.Bank;
import org.helianto.core.Contact;
import org.helianto.core.Customer;
import org.helianto.core.Division;
import org.helianto.core.Entity;
import org.helianto.core.Identity;
import org.helianto.core.Manufacturer;
import org.helianto.core.Partner;
import org.helianto.core.PartnerRole;
import org.helianto.core.Supplier;
import org.helianto.core.type.PartnerState;
import org.helianto.core.type.ShareMode;

public class PartnerCreator extends EntityCreator {
	
    public static Partner partnerFactory(Entity entity, String alias) {
        Partner partner = new Partner();
        partner.setEntity(entity);
        partner.setAlias(alias);
        partner.setRelatedSince(new Date());
        partner.setShareMode(ShareMode.RESTRICTED.getValue());
    	return partner;
    }
    
    public static Contact contactFactory(Partner partner, Identity identity) {
        Contact cntct = new Contact();
        cntct.setPartner(partner);
        cntct.setIdentity(identity);
        return cntct;
    }

    private static Object partnerRoleFactory(Class clazz, Partner partner) {
        PartnerRole partnerRole;
        try {
            partnerRole = (PartnerRole) clazz.newInstance();
            partnerRole.setPartner(partner);
            partnerRole.setPartnerState(PartnerState.ACTIVE.getValue());
            return partnerRole;
        } catch (Exception e) {
            throw new RuntimeException("Can't instantiate "+clazz);
        }
    }

    // for test purposes only
    public static PartnerRole partnerRoleFactory(Partner partner) {
        return (PartnerRole) partnerRoleFactory(PartnerRole.class, partner);
      }
      
    public static Customer customerFactory(Partner partner) {
        return (Customer) partnerRoleFactory(Customer.class, partner);
      }
      
    public static Supplier supplierFactory(Partner partner) {
      return (Supplier) partnerRoleFactory(Supplier.class, partner);
    }
    
    public static Division divisionFactory(Partner partner) {
        return (Division) partnerRoleFactory(Division.class, partner);
    }
    
    public static Bank bankFactory(Partner partner) {
        return (Bank) partnerRoleFactory(Bank.class, partner);
    }

    public static Agent agentFactory(Partner partner) {
        return (Agent) partnerRoleFactory(Agent.class, partner);
    }

    public static Manufacturer manufacturerFactory(Partner partner) {
        return (Manufacturer) partnerRoleFactory(Manufacturer.class, partner);
    }

}

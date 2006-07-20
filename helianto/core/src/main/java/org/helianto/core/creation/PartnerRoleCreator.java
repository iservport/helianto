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

public class PartnerRoleCreator extends EntityCreator {
	
    private static Object partnerRoleFactory(Class clazz, Partner partner) {
        PartnerRole partnerRole;
        try {
        	partnerRole = (PartnerRole) clazz.newInstance();
        	partnerRole.setPartner(partner);
            return partnerRole;
        } catch (Exception e) {
            throw new RuntimeException("Can't instantiate "+clazz);
        }
    }

//    public static Customer customerFactory(Entity entity, String alias) {
//    	return (Customer) partnerRoleFactory(Customer.class, entity, alias);
//    }
//    
//    public static Supplier supplierFactory(Entity entity, String alias) {
//    	return (Supplier) partnerRoleFactory(Supplier.class, entity, alias);
//    }
//    
    public static Division divisionFactory(Partner partner) {
    	return (Division) partnerRoleFactory(Division.class, partner);
    }
    
//    public static Bank bankFactory(Entity entity, String alias) {
//        return (Bank) partnerRoleFactory(Bank.class, entity, alias);
//    }
//
//    public static Agent agentFactory(Entity entity, String alias) {
//        return (Agent) partnerRoleFactory(Agent.class, entity, alias);
//    }
//
//    public static Manufacturer manufacturerFactory(Entity entity, String alias) {
//        return (Manufacturer) partnerRoleFactory(Manufacturer.class, entity, alias);
//    }
//
}

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
import org.helianto.core.Self;
import org.helianto.core.Supplier;
import org.helianto.core.type.PartnerState;
import org.helianto.core.type.ShareMode;

public class PartnerCreator extends EntityCreator {
	
    private static Object selfFactory(Class clazz, Entity entity, String alias) {
    	Self self;
        try {
        	self = (Partner) clazz.newInstance();
        	self.setEntity(entity);
        	self.setAlias(alias);
            return self;
        } catch (Exception e) {
            throw new RuntimeException("Can't instantiate "+clazz);
        }
    }

    public static Self selfFactory(Entity entity, String alias) {
    	return (Self) selfFactory(Self.class, entity, alias);
    }
    
    public static Partner partnerFactory(Entity entity, String alias) {
        Partner partner = (Partner) selfFactory(Partner.class, entity, alias);
        partner.setRelatedSince(new Date());
//        partner.setShareMode(ShareMode.RESTRICTED.getValue());
    	return partner;
    }
    
    public static Contact contactFactory(Partner partner, Identity identity) {
        Contact cntct = new Contact();
        cntct.setPartner(partner);
        cntct.setIdentity(identity);
        return cntct;
    }

}

package org.helianto.core;

import java.util.Date;

public class PartnerCreatorImpl implements PartnerCreator {

    public Customer customerFactory(Entity entity, String alias) {
        Customer customer = new Customer();
        customer.setEntity(entity);
        customer.setAlias(alias);
        customer.setRelatedSince(new Date());
        customer.setState(PartnerState.ACTIVE.getValue());
        customer.setStrong(false);
        return customer;
    }
    
    public Supplier supplierFactory(Entity entity, String alias) {
        Supplier supplier = new Supplier();
        supplier.setEntity(entity);
        supplier.setAlias(alias);
        supplier.setRelatedSince(new Date());
        supplier.setState(PartnerState.ACTIVE.getValue());
        supplier.setStrong(false);
        return supplier;
    }
    
    public Contact contactFactory(Partner partner, Credential cred) {
        Contact cntct = new Contact();
        cntct.setPartner(partner);
        cntct.setCredential(cred);
        return cntct;
    }

}

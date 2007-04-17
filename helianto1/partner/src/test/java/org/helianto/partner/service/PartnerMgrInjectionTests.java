package org.helianto.partner.service;

import org.helianto.partner.orm.AbstractPartnerDaoImplConfig;

/**
 * <code>PartnerMgrImpl</code> injection tests.
 *
 * @author Mauricio Fernandes de Castro
 */
public class PartnerMgrInjectionTests extends AbstractPartnerDaoImplConfig {
    
    private PartnerMgr partnerMgr;
    
    public void testInit() {
        partnerMgr.getClass();
        // the container invokes init
    }
    
    //- setters

    public void setPartnerMgr(PartnerMgr partnerMgr) {
        this.partnerMgr = partnerMgr;
    }
    
}


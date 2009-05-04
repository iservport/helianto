package org.helianto.partner.service;

import org.helianto.partner.orm.AbstractPartnerDaoImplConfig;

/**
 * Injection tests.
 *
 * @author Mauricio Fernandes de Castro
 */
public class InjectionTests extends AbstractPartnerDaoImplConfig {
    
    public void testInit() {
        partnerMgr.getClass();
        accountMgr.getClass();
    }
    
    //- collabs

    private PartnerMgr partnerMgr;
    private AccountMgr accountMgr;
    
    public void setPartnerMgr(PartnerMgr partnerMgr) {
        this.partnerMgr = partnerMgr;
    }
    
    public void setAccountMgr(AccountMgr accountMgr) {
        this.accountMgr = accountMgr;
    }
    
}


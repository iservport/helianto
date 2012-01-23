package org.helianto.inventory.test;

import java.math.BigDecimal;

import org.helianto.core.Identity;
import org.helianto.core.test.DomainTestSupport;
import org.helianto.core.test.IdentityTestSupport;
import org.helianto.inventory.ProcessAgreement;
import org.helianto.partner.domain.Partner;

/**
 * Class to support <code>ProcessAgreement</code> tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class ProcessAgreementTestSupport {

    private static int testKey;
    public static float maxAmount = 0;
    public static int maxPastDays = 0;

    /**
     * Test support method to create a <code>Agreement</code>.
     */
    public static ProcessAgreement createProcessAgreement() {
    	return createProcessAgreement(new Partner());
    }
    
    /**
     * Test support method to create a <code>Agreement</code>.
     * 
     * @param partner
     */
    public static ProcessAgreement createProcessAgreement(Partner partner) {
    	return createProcessAgreement(partner, IdentityTestSupport.createIdentity());
    }
    
    /**
     * Test support method to create a <code>Agreement</code>.
     * 
     * @param partner
     * @param identity
     */
    public static ProcessAgreement createProcessAgreement(Partner partner, Identity identity) {
    	return createProcessAgreement(partner, identity, DomainTestSupport.getNonRepeatableLongValue(testKey++));
    }
    
    /**
     * Test support method to create a <code>Agreement</code>.
     * 
     * @param partner
     * @param identity
     * @param internalNumber
     */
    public static ProcessAgreement createProcessAgreement(Partner partner, Identity identity, long internalNumber) {
    	ProcessAgreement agreement = new ProcessAgreement(partner);
        if (maxAmount > 0) {
        	agreement.setRequirementAmount(BigDecimal.valueOf(Math.random()*maxAmount));
        }
        return agreement;
    }


}

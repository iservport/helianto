package org.helianto.partner.test;

import org.helianto.partner.PartnerRegistry;
import org.helianto.partner.TransportPartner;

/**
 * Class to support <code>TransportPartner</code> tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class TransportPartnerTestSupport {

    /**
     * Test support method to create a <code>TransportPartner</code>.
     */
    public static TransportPartner createTransportPartner() {
        return TransportPartnerTestSupport.createTransportPartner(PartnerRegistryTestSupport.createPartnerRegistry());
    }
    
    /**
     * Test support method to create a <code>TransportPartner</code>.
     * 
     * @param partnerRegistry 
     */
    public static TransportPartner createTransportPartner(PartnerRegistry partnerRegistry) {
    	TransportPartner transportPartner = TransportPartner.transportPartnerFactory(partnerRegistry);
        return transportPartner;
    }

}

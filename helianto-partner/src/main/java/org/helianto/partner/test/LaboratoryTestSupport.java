package org.helianto.partner.test;

import java.util.ArrayList;
import java.util.List;

import org.helianto.partner.PartnerRegistry;
import org.helianto.partner.Laboratory;

/**
 * Class to support <code>LaboratoryDao</code> tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class LaboratoryTestSupport {

    /**
     * Test support method to create a <code>Laboratory</code>.
     * @param partnerRegistry optional PartnerRegistry 
     */
    public static Laboratory createLaboratory(Object... args) {
        PartnerRegistry partnerRegistry;
        try {
            partnerRegistry = (PartnerRegistry) args[0];
        } catch(ArrayIndexOutOfBoundsException e) {
            partnerRegistry = PartnerRegistryTestSupport.createPartnerRegistry();
        }
        Laboratory laboratory = Laboratory.laboratoryFactory(partnerRegistry);
        return laboratory;
    }

    /**
     * Test support method to create a <code>Laboratory</code> list.
     *
     * @param laboratoryListSize
     */
    public static List<Laboratory> createLaboratoryList(int laboratoryListSize) {
        List<PartnerRegistry> partnerRegistryList = PartnerRegistryTestSupport.createPartnerRegistryList(laboratoryListSize);

        return createLaboratoryList(partnerRegistryList);
    }

    /**
     * Test support method to create a <code>Laboratory</code> list.
     *
     * @param partnerRegistryList
     */
    public static List<Laboratory> createLaboratoryList(List<PartnerRegistry> partnerRegistryList) {
        List<Laboratory> laboratoryList = new ArrayList<Laboratory>();
        for (PartnerRegistry partnerRegistry: partnerRegistryList) {
    	    laboratoryList.add(createLaboratory(partnerRegistry));
        }
        return laboratoryList;
    }

}

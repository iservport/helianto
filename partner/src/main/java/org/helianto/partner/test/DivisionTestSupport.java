package org.helianto.partner.test;

import java.util.ArrayList;
import java.util.List;

import org.helianto.partner.Division;
import org.helianto.partner.PartnerRegistry;

/**
 * Class to support <code>Division</code> tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class DivisionTestSupport {

    /**
     * Test support method to create a <code>Division</code>.
     * @param partnerRegistry optional partnerRegistry 
     */
    public static Division createDivision(Object... args) {
        PartnerRegistry partnerRegistry;
        try {
            partnerRegistry = (PartnerRegistry) args[0];
        } catch(ArrayIndexOutOfBoundsException e) {
            partnerRegistry = PartnerRegistryTestSupport.createPartnerRegistry();
	    } catch(ClassCastException e) {
	        partnerRegistry = PartnerRegistryTestSupport.createPartnerRegistry();
	        try {
	            String partnerAlias = (String) args[0];
	            partnerRegistry.setPartnerAlias(partnerAlias);
	        } catch(Exception e1) {
	            // nop
	        }
	
	    }
        Division customer = Division.divisionFactory(partnerRegistry);
        return customer;
    }

    /**
     * Test support method to create a <code>Division</code> list.
     *
     * @param divisionListSize
     */
    public static List<Division> createDivisionList(int divisionListSize) {
        List<PartnerRegistry> partnerRegistryList = PartnerRegistryTestSupport.createPartnerRegistryList(divisionListSize);

        return createDivisionList(partnerRegistryList);
    }

    /**
     * Test support method to create a <code>Division</code> list.
     *
     * @param partnerRegistryList
     */
    public static List<Division> createDivisionList(List<PartnerRegistry> partnerRegistryList) {
        List<Division> divisionList = new ArrayList<Division>();
        for (PartnerRegistry partnerRegistry: partnerRegistryList) {
   	        divisionList.add(createDivision(partnerRegistry));
        }
        return divisionList;
    }

}

package org.helianto.core.creation;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Set;

import org.helianto.core.Agent;
import org.helianto.core.Bank;
import org.helianto.core.Contact;
import org.helianto.core.Customer;
import org.helianto.core.Division;
import org.helianto.core.Entity;
import org.helianto.core.Identity;
import org.helianto.core.KeyType;
import org.helianto.core.Manufacturer;
import org.helianto.core.Partner;
import org.helianto.core.PartnerData;
import org.helianto.core.PartnerKey;
import org.helianto.core.Province;
import org.helianto.core.SharedEntity;
import org.helianto.core.Supplier;
import org.helianto.core.type.PartnerState;
import org.helianto.core.type.ShareMode;

/**
 * Partner required classes factory methods.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class PartnerCreator extends CreatorSupport {
	
    /**
     * Default <code>PartnerData</code> creator.
     * 
     * @param entity
     * @param province
     * @param uniqueAlias
     */
    public static PartnerData partnerDataFactory(Entity entity, Province province, String uniqueAlias) {
        PartnerData partnerData = new PartnerData();
        partnerData.setEntity(entity);
        partnerData.setAlias(uniqueAlias);
        partnerData.setAddress1("");
        partnerData.setAddress2("");
        partnerData.setAddress3("");
        partnerData.setCityName("");
        partnerData.setProvince(province);
        partnerData.setPostalCode("");
        if (logger.isDebugEnabled()) {
            logger.debug("Created: "+partnerData);
        }
        return partnerData;
    }
    
    /**
     * Default <code>SharedEntity</code> creator.
     * 
     * @param requiredEntity
     * @param partnerData
     */
    public static SharedEntity sharedEntityFactory(Entity requiredEntity, PartnerData partnerData) {
        SharedEntity sharedEntity = new SharedEntity();
        sharedEntity.setSharedEntity(requiredEntity);
        sharedEntity.setSharedSince(new Date());
        sharedEntity.setShareMode(ShareMode.RESTRICTED.getValue());
        if (logger.isDebugEnabled()) {
            logger.debug("Created: "+sharedEntity);
        }
    	return sharedEntity;
    }
    
    /**
     * Default <code>Contact</code> creator.
     * 
     * @param partnerData
     * @param identity
     */
    public static Contact contactFactory(PartnerData partnerData, Identity identity) {
        Contact contact = new Contact();
        contact.setPartnerData(partnerData);
        contact.setIdentity(identity);
        if (logger.isDebugEnabled()) {
            logger.debug("Created: "+contact);
        }
        return contact;
    }

    /**
     * Default <code>Customer</code> creator.
     * 
     * @param requiredPartnerData
     */
    public static Customer customerFactory(PartnerData requiredPartnerData) {
        return (Customer) internalPartnerFactory(Customer.class, requiredPartnerData);
      }
      
    /**
     * Default <code>Supplier</code> creator.
     * 
     * @param requiredPartnerData
     */
    public static Supplier supplierFactory(PartnerData requiredPartnerData) {
      return (Supplier) internalPartnerFactory(Supplier.class, requiredPartnerData);
    }
    
    /**
     * Default <code>Division</code> creator.
     * 
     * @param requiredPartnerData
     */
    public static Division divisionFactory(PartnerData requiredPartnerData) {
        return (Division) internalPartnerFactory(Division.class, requiredPartnerData);
    }
    
    /**
     * Default <code>Bank</code> creator.
     * 
     * @param requiredPartnerData
     */
    public static Bank bankFactory(PartnerData requiredPartnerData) {
        return (Bank) internalPartnerFactory(Bank.class, requiredPartnerData);
    }

    /**
     * Default <code>Agent</code> creator.
     * 
     * @param requiredPartnerData
     */
    public static Agent agentFactory(PartnerData requiredPartnerData) {
        return (Agent) internalPartnerFactory(Agent.class, requiredPartnerData);
    }

    /**
     * Default <code>Manufacturer</code> creator.
     * 
     * @param requiredPartnerData
     */
    public static Manufacturer manufacturerFactory(PartnerData requiredPartnerData) {
        return (Manufacturer) internalPartnerFactory(Manufacturer.class, requiredPartnerData);
    }

    /**
     * Default <code>PartnerKey</code> creator.
     * 
     * @param partner
     * @param keyType
     * @param keyValue
     */
    public static PartnerKey entityKeyFactory(Partner partner, KeyType keyType, String keyValue) {
        PartnerKey partnerKey = new PartnerKey();
        partnerKey.setPartner(partner);
        partnerKey.setKeyType(keyType);
        partnerKey.setKeyValue(keyValue);
        if (logger.isDebugEnabled()) {
            logger.debug("Created: "+partnerKey);
        }
        return partnerKey;
    }
    
    /**
     * Implements <code>Comparator</code> using the <code>sequence</code> field.
     * 
     * @author Mauricio Fernandes de Castro
     */
    public class PartnerComparator implements Comparator<Partner> {

        /**
         * Compare by sequence. 
         */
        public int compare(Partner arg0, Partner arg1) {
            return arg0.getSequence() - arg1.getSequence();
        }

    }

    /* Convenience factory method; for tests purpose only
     */

    /**
     * Default <code>Partner</code> creator.
     * 
     * @param requiredPartnerData
     */
    public static Partner partnerFactory(PartnerData requiredPartnerData) {
        return (Partner) internalPartnerFactory(Partner.class, requiredPartnerData);
    }
    
    private static Partner internalPartnerFactory(Class<? extends Partner> clazz, PartnerData requiredPartnerData) {
        try {
            Partner partner = clazz.newInstance();
            partner.setPartnerData(requiredPartnerData);
            partner.setPartnerState(PartnerState.ACTIVE.getValue());
            Set<Partner> partnerList = requiredPartnerData.getPartners();
            byte sequence = 0;
            if (!partnerList.isEmpty()) {
                sequence = ((Partner) Collections.max(partnerList, new PartnerCreator().new PartnerComparator())).getSequence();
            }
            partner.setSequence(++sequence);
            partnerList.add(partner);
            if (logger.isDebugEnabled()) {
                logger.debug("Created: "+partner);
            }
            return partner;
        } catch (Exception e) {
            throw new RuntimeException("Can't instantiate "+clazz);
        }
    }

}

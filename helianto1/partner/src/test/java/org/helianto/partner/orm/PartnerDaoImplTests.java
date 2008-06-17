package org.helianto.partner.orm;

import java.util.List;

import org.helianto.partner.Account;
import org.helianto.partner.Partner;
import org.helianto.partner.SimplePartnerRegistry;
import org.helianto.partner.dao.PartnerDao;
import org.helianto.partner.test.AccountTestSupport;
import org.helianto.partner.test.PartnerTestSupport;
import org.springframework.dao.DataIntegrityViolationException;

/**
 * <code>PartnerDao</code> tests.
 *
 * @author Mauricio Fernandes de Castro
 */
public class PartnerDaoImplTests extends AbstractPartnerDaoImplConfig {
    
    private PartnerDao partnerDao;
    
    /*
     * Hook to persist one <code>Partner</code>.
     */  
    protected Partner writePartner() {
        Partner partner = PartnerTestSupport.createPartner();
        partnerDao.persistPartner(partner);
        partnerDao.flush();
        partnerDao.clear();
        return partner;
    }
    
    /**
     * Find by natural id.
     */  
    public void testFindOnePartner() {
        Partner partner = writePartner();

        assertEquals(partner,  partnerDao.findPartnerByNaturalId(partner.getPartnerRegistry()));
    }
    
    /*
     * Hook to persist a <code>Partner</code> list.
     */  
    protected List<Partner> writePartnerList() {
        int partnerListSize = 10;
        List<Partner> partnerList = PartnerTestSupport.createPartnerList(partnerListSize);
        assertEquals(partnerListSize, partnerList.size());
        for (Partner partner: partnerList) {
            partnerDao.persistPartner(partner);
        }
        partnerDao.flush();
        partnerDao.clear();
        return partnerList;
    }
    
    /**
     * Find from a list.
     */  
    public void testFindListPartner() {
        List<Partner> partnerList = writePartnerList();

        Partner partner = partnerList.get((int) (Math.random()*partnerList.size()));
        assertEquals(partner,  partnerDao.findPartnerByNaturalId(partner.getPartnerRegistry()));
        
        List<SimplePartnerRegistry> simplePartnerList = partnerDao.findSimplePartnerRegistries("");
        for (SimplePartnerRegistry sp: simplePartnerList) {
        	System.out.println(sp+" >"+sp.getType());
        }
    }

    public void testFindListSimplePartnerRegistry() {
        List<Partner> partnerList = writePartnerList();

        Partner partner = partnerList.get((int) (Math.random()*partnerList.size()));
        List<SimplePartnerRegistry> simplePartnerList = partnerDao.findSimplePartnerRegistries("partnerRegistry.entity.id = "+partner.getPartnerRegistry().getEntity().getId() +
        		"and partnerRegistry.partnerAlias = '"+partner.getPartnerRegistry().getPartnerAlias()+"'");
        for (SimplePartnerRegistry sp: simplePartnerList) {
        	System.out.println(sp+" >"+sp.getType());
        }
    }

    /**
     * Merge and duplicate.
     */  
    public void testPartnerDuplicate() {
        Partner partner =  writePartner();
        Partner partnerCopy = PartnerTestSupport.createPartner(partner.getPartnerRegistry());

        try {
            partnerDao.mergePartner(partnerCopy); fail();
        } catch (DataIntegrityViolationException e) { 
        } catch (Exception e) { fail(); }
    }
    
    /**
     * Remove.
     */  
    public void testRemovePartner() {
        List<Partner> partnerList = writePartnerList();
        Partner partner = partnerList.get((int) (Math.random()*partnerList.size()));
        partnerDao.removePartner(partner);

        assertNull(partnerDao.findPartnerByNaturalId(partner.getPartnerRegistry()));
    }

    /**
     * Account association test
     */
    public void testAccountAssociation() {
        Partner partner = PartnerTestSupport.createPartner();
        Account account = AccountTestSupport.createAccount(partner.getPartnerRegistry().getEntity());
        partner.setAccount(account);
        partnerDao.persistPartner(partner);
        partnerDao.flush();
        partnerDao.clear();
        
        Partner loadedPartner = partnerDao.findPartnerByNaturalId(partner.getPartnerRegistry());
        assertEquals(partner,  loadedPartner);
        assertEquals(account, loadedPartner.getAccount());
    }

    //- setters

    public void setPartnerDao(PartnerDao partnerDao) {
        this.partnerDao = partnerDao;
    }
    
}


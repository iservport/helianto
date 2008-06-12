package org.helianto.core.orm;

import java.util.List;

import org.helianto.core.Identity;
import org.helianto.core.dao.IdentityDao;
import org.helianto.core.test.IdentityTestSupport;
import org.springframework.dao.DataIntegrityViolationException;



/**
 * <code>IdentityDao</code> tests.
 *
 * @author Mauricio Fernandes de Castro
 */
public class IdentityDaoImplTests extends AbstractHibernateIntegrationTest {

    private IdentityDao identityDao;
    
    /*
     * Hook to persist one <code>Identity</code>.
     */  
    protected Identity writeIdentity() {
        Identity identity = IdentityTestSupport.createIdentity();
        identityDao.persistIdentity(identity);
        identityDao.flush();
        identityDao.clear();
        return identity;
    }
    
    /**
     * Find by natural id.
     */  
    public void testFindOneIdentity() {
        Identity identity = writeIdentity();

        assertEquals(identity,  identityDao.findIdentityByNaturalId(identity.getPrincipal()));
    }
    
    /*
     * Hook to persist a <code>Identity</code> list.
     */  
    protected List<Identity> writeIdentityList() {
        int identityListSize = 10;
        List<Identity> identityList = IdentityTestSupport.createIdentityList(identityListSize);
        assertEquals(identityListSize, identityList.size());
        for (Identity identity: identityList) {
            identityDao.persistIdentity(identity);
        }
        identityDao.flush();
        identityDao.clear();
        return identityList;
    }
    
    /**
     * Find from a list.
     */  
    public void testFindListIdentity() {
        List<Identity> identityList = writeIdentityList();

        Identity identity = identityList.get((int) (Math.random()*identityList.size()));
        assertEquals(identity,  identityDao.findIdentityByNaturalId(identity.getPrincipal()));
        assertEquals(identity,  identityDao.findIdentities("identity.principal = '"+identity.getPrincipal()+"'").get(0));
    }

    /**
     * Merge and duplicate.
     */  
    public void testIdentityDuplicate() {
        Identity identity =  writeIdentity();
        Identity identityCopy = IdentityTestSupport.createIdentity(identity.getPrincipal());

        try {
            identityDao.mergeIdentity(identityCopy); fail();
        } catch (DataIntegrityViolationException e) { 
        } catch (Exception e) { fail(); }
    }
    
    /**
     * Remove.
     */  
    public void testRemoveIdentity() {
        List<Identity> identityList = writeIdentityList();
        Identity identity = identityList.get((int) (Math.random()*identityList.size()));
        identityDao.removeIdentity(identity);

        assertNull(identityDao.findIdentityByNaturalId(identity.getPrincipal()));
    }
    
    //- setters

    public void setIdentityDao(IdentityDao identityDao) {
        this.identityDao = identityDao;
    }
    
}


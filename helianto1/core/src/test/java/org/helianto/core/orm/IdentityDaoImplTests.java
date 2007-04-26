package org.helianto.core.orm;

import org.helianto.core.test.AbstractIntegrationTest;
import org.helianto.core.test.AuthorizationTestSupport;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

import org.helianto.core.Entity;
import org.helianto.core.Identity;
import org.helianto.core.User;
import org.helianto.core.dao.AuthorizationDao;
import org.helianto.core.dao.IdentityDao;
import org.helianto.core.hibernate.filter.IdentityFilter;
import org.helianto.core.test.IdentityTestSupport;



/**
 * <code>IdentityDao</code> tests.
 *
 * @author Mauricio Fernandes de Castro
 */
public class IdentityDaoImplTests extends AbstractIntegrationTest {

    private IdentityDao identityDao;
    private AuthorizationDao authorizationDao;
    
    @Override
    protected String[] getConfigLocations() {
        return new String[] { 
                "deploy/dataSource.xml",
                "deploy/sessionFactory.xml",
                "deploy/transaction.xml",
                "deploy/support.xml",
                "deploy/core.xml",
                "deploy/org.helianto.core.xml"
                };
    }
    
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
    
    //- additional

    public void testFindIdentities() {
        // write list
        int e = 1;
        int d = 3;
        List<User> userList = AuthorizationTestSupport.createUserList(e, d);
        userList.addAll(AuthorizationTestSupport.createUserList(e, d));
        for (User user: userList) {
        	authorizationDao.persistUserGroup(user);
        }
        assertEquals(2*d, userList.size());
        User user = userList.get((int) (Math.random()*e*d));
        // read
        IdentityFilter identityFilter = new IdentityFilter();
        identityFilter.setUser(user);
        
        List<Identity> identityList = identityDao.findIdentityByCriteria(identityFilter);
        assertEquals(d, identityList.size());
        int index = (int) (Math.random()*d);
        System.out.println("INDEX "+index);
        Identity identity = identityList.get(index);
        Entity loaded = 
            identity
            .getUsers()   // users that share this identity
            .iterator() 
            .next()       // the first one
            .getEntity(); // the parent inside the association
        assertEquals(loaded.getId(), user.getEntity().getId());
    }

    //- setters

    public void setIdentityDao(IdentityDao identityDao) {
        this.identityDao = identityDao;
    }
    
    public void setAuthorizationDao(AuthorizationDao authorizationDao) {
        this.authorizationDao = authorizationDao;
    }
    
}


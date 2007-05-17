package org.helianto.core.orm;

import java.util.List;

import org.helianto.core.UserAssociation;
import org.helianto.core.dao.UserAssociationDao;
import org.helianto.core.test.AbstractIntegrationTest;
import org.helianto.core.test.UserAssociationTestSupport;
import org.springframework.dao.DataIntegrityViolationException;
/**
 * <code>UserAssociationDao</code> tests.
 *
 * @author Mauricio Fernandes de Castro
 */
public class UserAssociationDaoImplTests extends AbstractIntegrationTest {

    private UserAssociationDao userAssociationDao;
    
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
     * Hook to persist one <code>UserAssociation</code>.
     */  
    protected UserAssociation writeUserAssociation() {
        UserAssociation userAssociation = UserAssociationTestSupport.createUserAssociation();
        userAssociationDao.persistUserAssociation(userAssociation);
        userAssociationDao.flush();
        userAssociationDao.clear();
        return userAssociation;
    }
    
    /**
     * Find by natural id.
     */  
    public void testFindOneUserAssociation() {
        UserAssociation userAssociation = writeUserAssociation();

        assertEquals(userAssociation,  userAssociationDao.findUserAssociationByNaturalId(userAssociation.getParent(), userAssociation.getChild()));
    }
    
    /*
     * Hook to persist a <code>UserAssociation</code> list.
     */  
    protected List<UserAssociation> writeUserAssociationList() {
        int parentListSize = 2;
        int childListSize = 3;
        List<UserAssociation> userAssociationList = UserAssociationTestSupport.createUserAssociationList(parentListSize, childListSize);
        assertEquals(parentListSize * childListSize, userAssociationList.size());
        for (UserAssociation userAssociation: userAssociationList) {
            userAssociationDao.persistUserAssociation(userAssociation);
        }
        userAssociationDao.flush();
        userAssociationDao.clear();
        return userAssociationList;
    }
    
    /**
     * Find from a list.
     */  
    public void testFindListUserAssociation() {
        List<UserAssociation> userAssociationList = writeUserAssociationList();

        UserAssociation userAssociation = userAssociationList.get((int) (Math.random()*userAssociationList.size()));
        assertEquals(userAssociation,  userAssociationDao.findUserAssociationByNaturalId(userAssociation.getParent(), userAssociation.getChild()));
    }

    /**
     * Merge and duplicate.
     */  
    public void testUserAssociationDuplicate() {
        UserAssociation userAssociation =  writeUserAssociation();
        UserAssociation userAssociationCopy = UserAssociationTestSupport.createUserAssociation(userAssociation.getParent(), userAssociation.getChild());

        try {
            userAssociationDao.mergeUserAssociation(userAssociationCopy); fail();
        } catch (DataIntegrityViolationException e) { 
        } catch (Exception e) { fail(); }
    }
    
    /**
     * Remove.
     */  
    public void testRemoveUserAssociation() {
        List<UserAssociation> userAssociationList = writeUserAssociationList();
        UserAssociation userAssociation = userAssociationList.get((int) (Math.random()*userAssociationList.size()));
        userAssociationDao.removeUserAssociation(userAssociation);

        assertNull(userAssociationDao.findUserAssociationByNaturalId(userAssociation.getParent(), userAssociation.getChild()));
    }

    //- setters

    public void setUserAssociationDao(UserAssociationDao userAssociationDao) {
        this.userAssociationDao = userAssociationDao;
    }
    
}


package org.helianto.core.orm;

import java.util.List;

import org.helianto.core.KeyType;
import org.helianto.core.dao.KeyTypeDao;
import org.helianto.core.test.AbstractIntegrationTest;
import org.helianto.core.test.KeyTypeTestSupport;
import org.springframework.dao.DataIntegrityViolationException;
/**
 * <code>KeyTypeDao</code> tests.
 *
 * @author Mauricio Fernandes de Castro
 */
public class KeyTypeDaoImplTests extends AbstractIntegrationTest {

    private KeyTypeDao keyTypeDao;
    
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
     * Hook to persist one <code>KeyType</code>.
     */  
    protected KeyType writeKeyType() {
        KeyType keyType = KeyTypeTestSupport.createKeyType();
        keyTypeDao.persistKeyType(keyType);
        keyTypeDao.flush();
        keyTypeDao.clear();
        return keyType;
    }
    
    /**
     * Find by natural id.
     */  
    public void testFindOneKeyType() {
        KeyType keyType = writeKeyType();

        assertEquals(keyType,  keyTypeDao.findKeyTypeByNaturalId(keyType.getOperator(), keyType.getKeyCode()));
    }
    
    /*
     * Hook to persist a <code>KeyType</code> list.
     */  
    protected List<KeyType> writeKeyTypeList() {
        int keyTypeListSize = 10;
        int operatorListSize = 2;
        List<KeyType> keyTypeList = KeyTypeTestSupport.createKeyTypeList(keyTypeListSize, operatorListSize);
        assertEquals(keyTypeListSize * operatorListSize, keyTypeList.size());
        for (KeyType keyType: keyTypeList) {
            keyTypeDao.persistKeyType(keyType);
        }
        keyTypeDao.flush();
        keyTypeDao.clear();
        return keyTypeList;
    }
    
    /**
     * Find from a list.
     */  
    public void testFindListKeyType() {
        List<KeyType> keyTypeList = writeKeyTypeList();

        KeyType keyType = keyTypeList.get((int) (Math.random()*keyTypeList.size()));
        assertEquals(keyType,  keyTypeDao.findKeyTypeByNaturalId(keyType.getOperator(), keyType.getKeyCode()));
    }

    /**
     * Merge and duplicate.
     */  
    public void testKeyTypeDuplicate() {
        KeyType keyType =  writeKeyType();
        KeyType keyTypeCopy = KeyTypeTestSupport.createKeyType(keyType.getOperator(), keyType.getKeyCode());

        try {
            keyTypeDao.mergeKeyType(keyTypeCopy); fail();
        } catch (DataIntegrityViolationException e) { 
        } catch (Exception e) { fail(); }
    }
    
    /**
     * Remove.
     */  
    public void testRemoveKeyType() {
        List<KeyType> keyTypeList = writeKeyTypeList();
        KeyType keyType = keyTypeList.get((int) (Math.random()*keyTypeList.size()));
        keyTypeDao.removeKeyType(keyType);

        assertNull(keyTypeDao.findKeyTypeByNaturalId(keyType.getOperator(), keyType.getKeyCode()));
    }

    //- setters

    public void setKeyTypeDao(KeyTypeDao keyTypeDao) {
        this.keyTypeDao = keyTypeDao;
    }
    
}


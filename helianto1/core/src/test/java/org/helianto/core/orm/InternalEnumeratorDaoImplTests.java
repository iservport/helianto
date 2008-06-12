package org.helianto.core.orm;

import java.util.List;

import org.helianto.core.InternalEnumerator;
import org.helianto.core.dao.InternalEnumeratorDao;
import org.helianto.core.test.InternalEnumeratorTestSupport;
import org.springframework.dao.DataIntegrityViolationException;
/**
 * <code>InternalEnumeratorDao</code> tests.
 *
 * @author Mauricio Fernandes de Castro
 */
public class InternalEnumeratorDaoImplTests extends AbstractHibernateIntegrationTest {

    private InternalEnumeratorDao internalEnumeratorDao;
    
    /*
     * Hook to persist one <code>InternalEnumerator</code>.
     */  
    protected InternalEnumerator writeInternalEnumerator() {
        InternalEnumerator internalEnumerator = InternalEnumeratorTestSupport.createInternalEnumerator();
        internalEnumeratorDao.persistInternalEnumerator(internalEnumerator);
        internalEnumeratorDao.flush();
        internalEnumeratorDao.clear();
        return internalEnumerator;
    }
    
    /**
     * Find by natural id.
     */  
    public void testFindOneInternalEnumerator() {
        InternalEnumerator internalEnumerator = writeInternalEnumerator();

        assertEquals(internalEnumerator,  internalEnumeratorDao.findInternalEnumeratorByNaturalId(internalEnumerator.getEntity(), internalEnumerator.getTypeName()));
    }
    
    /*
     * Hook to persist a <code>InternalEnumerator</code> list.
     */  
    protected List<InternalEnumerator> writeInternalEnumeratorList() {
        int internalEnumeratorListSize = 10;
        int entityListSize = 2;
        List<InternalEnumerator> internalEnumeratorList = InternalEnumeratorTestSupport.createInternalEnumeratorList(internalEnumeratorListSize, entityListSize);
        assertEquals(internalEnumeratorListSize * entityListSize, internalEnumeratorList.size());
        for (InternalEnumerator internalEnumerator: internalEnumeratorList) {
            internalEnumeratorDao.persistInternalEnumerator(internalEnumerator);
        }
        internalEnumeratorDao.flush();
        internalEnumeratorDao.clear();
        return internalEnumeratorList;
    }
    
    /**
     * Find from a list.
     */  
    public void testFindListInternalEnumerator() {
        List<InternalEnumerator> internalEnumeratorList = writeInternalEnumeratorList();

        InternalEnumerator internalEnumerator = internalEnumeratorList.get((int) (Math.random()*internalEnumeratorList.size()));
        assertEquals(internalEnumerator,  internalEnumeratorDao.findInternalEnumeratorByNaturalId(internalEnumerator.getEntity(), internalEnumerator.getTypeName()));
    }

    /**
     * Merge and duplicate.
     */  
    public void testInternalEnumeratorDuplicate() {
        InternalEnumerator internalEnumerator =  writeInternalEnumerator();
        InternalEnumerator internalEnumeratorCopy = InternalEnumeratorTestSupport.createInternalEnumerator(internalEnumerator.getEntity(), internalEnumerator.getTypeName());

        try {
            internalEnumeratorDao.mergeInternalEnumerator(internalEnumeratorCopy); fail();
        } catch (DataIntegrityViolationException e) { 
        } catch (Exception e) { fail(); }
    }
    
    /**
     * Remove.
     */  
    public void testRemoveInternalEnumerator() {
        List<InternalEnumerator> internalEnumeratorList = writeInternalEnumeratorList();
        InternalEnumerator internalEnumerator = internalEnumeratorList.get((int) (Math.random()*internalEnumeratorList.size()));
        internalEnumeratorDao.removeInternalEnumerator(internalEnumerator);

        assertNull(internalEnumeratorDao.findInternalEnumeratorByNaturalId(internalEnumerator.getEntity(), internalEnumerator.getTypeName()));
    }

    //- setters

    public void setInternalEnumeratorDao(InternalEnumeratorDao internalEnumeratorDao) {
        this.internalEnumeratorDao = internalEnumeratorDao;
    }
    
}


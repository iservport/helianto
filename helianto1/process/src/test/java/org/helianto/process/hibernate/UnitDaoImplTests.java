package org.helianto.process.hibernate;

import java.util.List;

import org.helianto.core.test.AbstractIntegrationTest;
import org.helianto.process.Unit;
import org.helianto.process.dao.UnitDao;
import org.helianto.process.test.UnitTestSupport;
import org.springframework.dao.DataIntegrityViolationException;

/**
 * <code>UnitDao</code> tests.
 *
 * @author Mauricio Fernandes de Castro
 */
public class UnitDaoImplTests extends AbstractIntegrationTest {
    
    private UnitDao unitDao;
    
    @Override
    protected String[] getConfigLocations() {
        return new String[] { 
                "deploy/dataSource.xml",
                "deploy/sessionFactory.xml",
                "deploy/transaction.xml",
                "deploy/core.xml",
                "deploy/process.xml"
                };
    }
    
    /*
     * Hook to persist one <code>Unit</code>.
     */  
    protected Unit writeUnit() {
        Unit unit = UnitTestSupport.createUnit();
        unitDao.persistUnit(unit);
        unitDao.flush();
        unitDao.clear();
        return unit;
    }
    
    /**
     * Find by natural id.
     */  
    public void testFindOneUnit() {
        Unit unit = writeUnit();

        assertEquals(unit,  unitDao.findUnitByNaturalId(unit.getEntity(), unit.getUnitCode()));
    }
    
    /*
     * Hook to persist a <code>Unit</code> list.
     */  
    protected List<Unit> writeUnitList() {
        int unitListSize = 10;
        int entityListSize = 2;
        List<Unit> unitList = UnitTestSupport.createUnitList(unitListSize, entityListSize);
        assertEquals(unitListSize * entityListSize, unitList.size());
        for (Unit unit: unitList) {
            unitDao.persistUnit(unit);
        }
        unitDao.flush();
        unitDao.clear();
        return unitList;
    }
    
    /**
     * Find from a list.
     */  
    public void testFindListUnit() {
        List<Unit> unitList = writeUnitList();

        Unit unit = unitList.get((int) (Math.random()*unitList.size()));
        assertEquals(unit,  unitDao.findUnitByNaturalId(unit.getEntity(), unit.getUnitCode()));
    }

    /**
     * Merge and duplicate.
     */  
    public void testUnitDuplicate() {
        Unit unit =  writeUnit();
        Unit unitCopy = UnitTestSupport.createUnit(unit.getEntity(), unit.getUnitCode());

        try {
            unitDao.mergeUnit(unitCopy); fail();
        } catch (DataIntegrityViolationException e) { 
        } catch (Exception e) { fail(); }
    }
    
    /**
     * Remove.
     */  
    public void testRemoveUnit() {
        List<Unit> unitList = writeUnitList();
        Unit unit = unitList.get((int) (Math.random()*unitList.size()));
        unitDao.removeUnit(unit);

        assertNull(unitDao.findUnitByNaturalId(unit.getEntity(), unit.getUnitCode()));
    }

    //- setters

    public void setUnitDao(UnitDao unitDao) {
        this.unitDao = unitDao;
    }
    
}


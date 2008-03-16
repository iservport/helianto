package org.helianto.core.orm;

import java.util.List;

import org.helianto.core.Unit;
import org.helianto.core.dao.UnitDao;
import org.helianto.core.test.AbstractIntegrationTest;
import org.helianto.core.test.UnitTestSupport;
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
                "deploy/support.xml",
                "deploy/org.helianto.core.xml",
                "deploy/core.xml"
                };
    }
    
    /*
     * Hook to persist one <code>Unit</code>.
     */  
    protected Unit writeUnit(Unit unit) {
        unitDao.persistUnit(unit);
        unitDao.flush();
        unitDao.clear();
        return unit;
    }
    
    protected Unit writeUnit() {
        Unit unit = UnitTestSupport.createUnit();
        writeUnit(unit);
        return unit;
    }
    
    /**
     * Find by natural id.
     */  
    public void testFindOneUnit() {
        Unit unit = UnitTestSupport.createUnit();
        writeUnit(unit);

        assertEquals(unit,  unitDao.findUnitByNaturalId(unit.getEntity(), unit.getUnitCode()));
    }
    
    /**
     * Find by natural id.
     */  
    public void testFindUnits() {
    	List<Unit> unitList = writeUnitList();

        assertEquals(10,  unitDao.findUnits("entity.id = "+unitList.get(0).getEntity().getId()).size());
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


package org.helianto.core.orm;

import java.util.List;

import org.helianto.core.Entity;
import org.helianto.core.dao.EntityDao;
import org.helianto.core.test.EntityTestSupport;
import org.springframework.dao.DataIntegrityViolationException;
/**
 * <code>EntityDao</code> tests.
 *
 * @author Mauricio Fernandes de Castro
 */
public class EntityDaoImplTests extends AbstractHibernateIntegrationTest {

    private EntityDao entityDao;
    
    /*
     * Hook to persist one <code>Entity</code>.
     */  
    protected Entity writeEntity() {
        Entity entity = EntityTestSupport.createEntity();
        entityDao.persistEntity(entity);
        entityDao.flush();
        entityDao.clear();
        return entity;
    }
    
    /**
     * Find by natural id.
     */  
    public void testFindOneEntity() {
        Entity entity = writeEntity();

        assertEquals(entity,  entityDao.findEntityByNaturalId(entity.getOperator(), entity.getAlias()));
    }
    
    /*
     * Hook to persist a <code>Entity</code> list.
     */  
    protected List<Entity> writeEntityList() {
        int entityListSize = 10;
        int operatorListSize = 2;
        List<Entity> entityList = EntityTestSupport.createEntityList(entityListSize, operatorListSize);
        assertEquals(entityListSize * operatorListSize, entityList.size());
        for (Entity entity: entityList) {
            entityDao.persistEntity(entity);
        }
        entityDao.flush();
        entityDao.clear();
        return entityList;
    }
    
    /**
     * Find from a list.
     */  
    public void testFindListEntity() {
        List<Entity> entityList = writeEntityList();

        Entity entity = entityList.get((int) (Math.random()*entityList.size()));
        assertEquals(entity,  entityDao.findEntityByNaturalId(entity.getOperator(), entity.getAlias()));
    }

    /**
     * Merge and duplicate.
     */  
    public void testEntityDuplicate() {
        Entity entity =  writeEntity();
        Entity entityCopy = EntityTestSupport.createEntity(entity.getOperator(), entity.getAlias());

        try {
            entityDao.mergeEntity(entityCopy); fail();
        } catch (DataIntegrityViolationException e) { 
        } catch (Exception e) { fail(); }
    }
    
    /**
     * Remove.
     */  
    public void testRemoveEntity() {
        List<Entity> entityList = writeEntityList();
        Entity entity = entityList.get((int) (Math.random()*entityList.size()));
        entityDao.removeEntity(entity);

        assertNull(entityDao.findEntityByNaturalId(entity.getOperator(), entity.getAlias()));
    }

    //- setters

    public void setEntityDao(EntityDao entityDao) {
        this.entityDao = entityDao;
    }
    
}


package org.helianto.core.service;

import java.util.ArrayList;
import java.util.List;

import org.helianto.core.Entity;
import org.helianto.core.Home;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class SimpleCoreMgrImplTests extends AbstractCoreTest {
	
    private SimpleCoreMgr simpleCoreMgr;
    private HibernateTemplate hibernateTemplate;
	
    public void setSimpleCoreMgr(SimpleCoreMgr simpleCoreMgr) {
        this.simpleCoreMgr = simpleCoreMgr;
    }

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    public void testCreateDefaultEntitySuccess() {
        
        Entity entity = simpleCoreMgr.createDefaultEntity("TEST");
        assertEquals("TEST",entity.getAlias());
        Home home = entity.getHome();
        assertEquals("TEST", home.getHomeName());
        
    }

    @SuppressWarnings("unchecked")
    public void testPersistDefaultEntitySuccess() {
        
        Entity entity = simpleCoreMgr.createDefaultEntity("TEST");
        simpleCoreMgr.persistDefaultEntity(entity);
        
        hibernateTemplate.flush();
        
        List<Home> homeList = hibernateTemplate.find("from Home");
        assertEquals(1, homeList.size());
        Home h = homeList.get(0);
        assertEquals (entity.getHome(), h);
        List<Entity> entityList = hibernateTemplate.find("from Entity");
        assertEquals(1, entityList.size());
        Entity e = entityList.get(0);
        assertEquals(entity, e);
        
    }
    
    public void testFindDefaultEntitySuccess() {
        
//        List homeList = (List) hibernateTemplate.find(SimpleCoreMgrImpl.HOME_QRY);
        
//        Entity entity = simpleCoreMgr.createDefaultEntity("TEST");
//        simpleCoreMgr.persistDefaultEntity(entity);
//        Entity e = simpleCoreMgr.findDefaultEntity();
//        assertNotNull(e);
        
    }


}

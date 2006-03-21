package org.helianto.core.service;

import java.util.List;

import org.helianto.core.DefaultEntity;
import org.helianto.core.Entity;
import org.helianto.core.Home;
import org.helianto.core.PersonalData;
import org.helianto.core.User;
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
        
        DefaultEntity defaultEntity = simpleCoreMgr.createDefaultEntity("TEST");
        assertEquals("TEST",defaultEntity.getEntity().getAlias());
        assertEquals("TEST",defaultEntity.getEntity().getHome().getHomeName());
        
    }

    @SuppressWarnings("unchecked")
    public void testPersistDefaultEntitySuccess() {
        
        DefaultEntity defaultEntity = simpleCoreMgr.createDefaultEntity("TEST");
        simpleCoreMgr.persistDefaultEntity(defaultEntity);
        
        hibernateTemplate.flush();
        
        List<Home> homeList = hibernateTemplate.find("from Home");
        assertEquals(1, homeList.size());
        Home h = homeList.get(0);
        assertEquals (defaultEntity.getEntity().getHome(), h);
        List<Entity> entityList = hibernateTemplate.find("from Entity");
        assertEquals(1, entityList.size());
        Entity e = entityList.get(0);
        assertEquals(defaultEntity.getEntity(), e);
        
    }
    
    public void testFindDefaultEntitySuccess() {
        
        DefaultEntity defaultEntity = simpleCoreMgr.createDefaultEntity("TEST");
        simpleCoreMgr.persistDefaultEntity(defaultEntity);
        
        hibernateTemplate.flush();
        
        Entity e = simpleCoreMgr.findDefaultEntity();
        assertEquals(defaultEntity.getEntity(), e);
        
    }
    
    public void testCreateSimpleUser() {
        DefaultEntity defaultEntity = simpleCoreMgr.createDefaultEntity("TEST");
        simpleCoreMgr.persistDefaultEntity(defaultEntity);

        User user = simpleCoreMgr.createSimpleUser();
        assertEquals(defaultEntity.getEntity(), user.getEntity());
        
    }

    public void testCreateSimpleUserEntity() {
        DefaultEntity defaultEntity = simpleCoreMgr.createDefaultEntity("TEST");
        simpleCoreMgr.persistDefaultEntity(defaultEntity);

        User user = simpleCoreMgr.createSimpleUser(defaultEntity.getEntity());
        assertEquals(defaultEntity.getEntity(), user.getEntity());
        assertEquals("", user.getCredential().getPrincipal());
        
    }


}

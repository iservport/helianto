package org.helianto.core.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.helianto.core.DefaultEntity;
import org.helianto.core.Entity;
import org.helianto.core.Home;
import org.helianto.core.Identity;
import org.helianto.core.User;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class SimpleCoreMgrImplTests extends AbstractCoreTest {
	
    // collaborator fields
    
    private SimpleCoreMgr simpleCoreMgr;
    private HibernateTemplate hibernateTemplate;
    
    // utility fields
    private final Log logger = LogFactory.getLog(getClass());
    private DefaultEntity defaultEntity;
    
    // tests
    
    @Override
    protected void onSetUpInTransaction() throws Exception {
        super.onSetUpInTransaction();
        defaultEntity = simpleCoreMgr.createDefaultEntity("TEST");
    }
    
//    public void testInstallWithDefaults() {
//    	Division division = simpleCoreMgr.installWithDefaults("TEST");
//    	assertEquals("TEST", division.getAlias());
//    }
//	
    public void testCreateDefaultEntitySuccess() {
        
        assertEquals("TEST",defaultEntity.getEntity().getAlias());
        assertEquals("TEST",defaultEntity.getEntity().getHome().getHomeName());
        
    }

    public void testPersistDefaultEntitySuccess() {
        
        simpleCoreMgr.persistDefaultEntity(defaultEntity);
        hibernateTemplate.flush();
        
        List<Home> homeList = (ArrayList<Home>) hibernateTemplate.find("from Home");
        assertEquals(1, homeList.size());
        Home h = homeList.get(0);
        assertEquals (defaultEntity.getEntity().getHome(), h);
        List<Entity> entityList = (ArrayList<Entity>) hibernateTemplate.find("from Entity");
        assertEquals(1, entityList.size());
        Entity e = entityList.get(0);
        assertEquals(defaultEntity.getEntity(), e);
        
    }
    
    public void testChangeEntityToDefault() {
        //TODO
    }
    
    public void testFindDefaultEntitySuccess() {
        
        simpleCoreMgr.persistDefaultEntity(defaultEntity);
        hibernateTemplate.flush();
        
        Entity e = simpleCoreMgr.findDefaultEntity();
        assertEquals(defaultEntity.getEntity(), e);
        
    }
    
    public void testCreateSimpleUser() {

        simpleCoreMgr.persistDefaultEntity(defaultEntity);
        hibernateTemplate.flush();
        
        User user = simpleCoreMgr.createSimpleUser();
        assertEquals(defaultEntity.getEntity(), user.getEntity());
        
    }

    public void testCreateSimpleUserCredential() {

        simpleCoreMgr.persistDefaultEntity(defaultEntity);
        hibernateTemplate.flush();
        
        Identity identity = simpleCoreMgr.createIdentity();
        
        User user = simpleCoreMgr.createSimpleUser(identity);
        assertEquals(defaultEntity.getEntity(), user.getEntity());
        assertSame(identity, user.getIdentity());
        
    }
    
    public void testLocaleFromValidHome() {
        
        simpleCoreMgr.persistDefaultEntity(defaultEntity);
        hibernateTemplate.flush();
        
        Home home = defaultEntity.getEntity().getHome();
        Locale locale = simpleCoreMgr.getLocale(home);
        assertEquals(home.getLanguage(), locale.getLanguage());
        assertEquals(home.getCountry(), locale.getCountry());

    }
    
    public void testLocaleFromInvalidHome() {
        
        Locale locale = simpleCoreMgr.getLocale(null);
        Locale defaultLocale = Locale.getDefault();
        assertEquals(locale, defaultLocale);

    }
    
    public void testConvertToLowerCaseSuccess() {
        
        Locale locale = simpleCoreMgr.getLocale(null);
        assertEquals("abcde123", 
                SimpleCoreMgrImpl.convertToLowerCase(locale, "ABCDE123"));
        
    }
    
    public void testConvertToLowerCaseFailure() {
        
        Locale locale = simpleCoreMgr.getLocale(null);
        try {
            SimpleCoreMgrImpl.convertToLowerCase(locale, null);
            fail();
        } catch (Exception e) {
            // ok
        }
        try {
            SimpleCoreMgrImpl.convertToLowerCase(locale, "");
            fail();
        } catch (Exception e) {
            // ok
        }
        
    }
    
    public void testPersistUserFailureEmptyPrincipal() {
        
        // TODO refactor with userDao stub or mock

        simpleCoreMgr.persistDefaultEntity(defaultEntity);
        hibernateTemplate.flush();
        
        try {
            User user = simpleCoreMgr.createSimpleUser();
            user.getIdentity().setPrincipal("");
            simpleCoreMgr.persistUser(user);
            fail();
        } catch (Exception e) {
            // ok
            logger.error("Expected exception is "+e.getMessage());
        }
        
    }

    // colaborator mutators
    
    public void setSimpleCoreMgr(SimpleCoreMgr simpleCoreMgr) {
        this.simpleCoreMgr = simpleCoreMgr;
    }

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

}

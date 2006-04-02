package org.helianto.core.service;

import java.util.List;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.helianto.core.Credential;
import org.helianto.core.DefaultEntity;
import org.helianto.core.Entity;
import org.helianto.core.Home;
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
	
    public void testCreateDefaultEntitySuccess() {
        
        assertEquals("TEST",defaultEntity.getEntity().getAlias());
        assertEquals("TEST",defaultEntity.getEntity().getHome().getHomeName());
        
    }

    @SuppressWarnings("unchecked")
    public void testPersistDefaultEntitySuccess() {
        
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
    
    public void testChangeEntityToDefault() {
        //TODO
    }
    
    public void testFindDefaultEntitySuccess() {
        
        simpleCoreMgr.persistDefaultEntity(defaultEntity);
        hibernateTemplate.flush();
        
        Entity e = simpleCoreMgr.findDefaultEntity();
        assertEquals(defaultEntity.getEntity(), e);
        
    }
    
    public void testCreateEmptyCredential() {
        assertNotNull(simpleCoreMgr.createEmptyCredential());
    }
    
    public void testCreateSimpleUser() {

        simpleCoreMgr.persistDefaultEntity(defaultEntity);
        hibernateTemplate.flush();
        
        User user = simpleCoreMgr.createSimpleUser();
        assertEquals(defaultEntity.getEntity(), user.getEntity());
        
    }

    public void testCreateSimpleUserEntity() {

        simpleCoreMgr.persistDefaultEntity(defaultEntity);
        hibernateTemplate.flush();
        
        User user = simpleCoreMgr.createSimpleUser(defaultEntity.getEntity());
        assertEquals(defaultEntity.getEntity(), user.getEntity());
        assertEquals("", user.getCredential().getPrincipal());
        
    }
    
    public void testCreateSimpleUserCredential() {

        simpleCoreMgr.persistDefaultEntity(defaultEntity);
        hibernateTemplate.flush();
        
        Credential credential = simpleCoreMgr.createEmptyCredential();
        
        User user = simpleCoreMgr.createSimpleUser(credential);
        assertEquals(defaultEntity.getEntity(), user.getEntity());
        assertSame(credential, user.getCredential());
        
    }
    
    public void testCreateUser() {

        simpleCoreMgr.persistDefaultEntity(defaultEntity);
        hibernateTemplate.flush();
        
        Credential credential = simpleCoreMgr.createEmptyCredential();
        
        assertNotNull(simpleCoreMgr.createUser(credential, defaultEntity.getEntity()));
        
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
    
    @SuppressWarnings("unchecked")
    public void testPersistUserSuccess() {
        
        simpleCoreMgr.persistDefaultEntity(defaultEntity);
        hibernateTemplate.flush();
        
        User user = simpleCoreMgr.createSimpleUser();
        user.getCredential().setPrincipal("ABC");
        simpleCoreMgr.persistUser(user);
        hibernateTemplate.flush();
        
        List<User> userList = hibernateTemplate.find("from User");
        assertEquals(1, userList.size());
        User u = userList.get(0);
        assertEquals (user, u);

    }
    
    public void testPersistUserFailureNullPrincipal() {
        
        simpleCoreMgr.persistDefaultEntity(defaultEntity);
        hibernateTemplate.flush();
        
        try {
            User user = simpleCoreMgr.createSimpleUser();
            user.getCredential().setPrincipal(null);
            simpleCoreMgr.persistUser(user);
            fail();
        } catch (Exception e) {
            logger.error("Expected exception is "+e.getMessage());
            // ok
        }
        
    }

    public void testPersistUserFailureEmptyPrincipal() {
        
        simpleCoreMgr.persistDefaultEntity(defaultEntity);
        hibernateTemplate.flush();
        
        try {
            User user = simpleCoreMgr.createSimpleUser();
            user.getCredential().setPrincipal("");
            simpleCoreMgr.persistUser(user);
            fail();
        } catch (Exception e) {
            // ok
            logger.error("Expected exception is "+e.getMessage());
        }
        
    }

    public void testIsPrincipalUnique() {

        simpleCoreMgr.persistDefaultEntity(defaultEntity);
        hibernateTemplate.flush();
        
        User user = simpleCoreMgr.createSimpleUser();
        user.getCredential().setPrincipal("ABC");
        assertTrue(simpleCoreMgr.isPrincipalUnique(user));
        
        simpleCoreMgr.persistUser(user);
        hibernateTemplate.flush();
        
        assertFalse(simpleCoreMgr.isPrincipalUnique(user));
        
    }
    
    public void testListUserByEntity() {
        //TODO
    }
    
    // colaborator setters
    
    public void setSimpleCoreMgr(SimpleCoreMgr simpleCoreMgr) {
        this.simpleCoreMgr = simpleCoreMgr;
    }

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

}

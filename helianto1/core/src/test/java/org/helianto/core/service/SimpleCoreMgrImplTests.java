package org.helianto.core.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class SimpleCoreMgrImplTests extends AbstractCoreTest {
	
    // collaborator fields
    
    private HibernateTemplate hibernateTemplate;
    
    // utility fields
    private final Log logger = LogFactory.getLog(getClass());
    
    // tests
    
    public void test() {
        // TODO
    }
    
    
//    public void testConvertToLowerCaseSuccess() {
//        
//        Locale locale = simpleCoreMgr.getLocale(null);
//        assertEquals("abcde123", 
//                SimpleCoreMgrImpl.convertToLowerCase(locale, "ABCDE123"));
//        
//    }
//    
//    public void testConvertToLowerCaseFailure() {
//        
//        Locale locale = simpleCoreMgr.getLocale(null);
//        try {
//            SimpleCoreMgrImpl.convertToLowerCase(locale, null);
//            fail();
//        } catch (Exception e) {
//            // ok
//        }
//        try {
//            SimpleCoreMgrImpl.convertToLowerCase(locale, "");
//            fail();
//        } catch (Exception e) {
//            // ok
//        }
//        
//    }
//    
    // colaborator mutators
    
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

}

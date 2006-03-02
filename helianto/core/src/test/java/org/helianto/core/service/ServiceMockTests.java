package org.helianto.core.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.helianto.core.Entity;
import org.helianto.core.InternalEnumerator;
import org.helianto.core.dao.GenericDao;
import org.jmock.Mock;
import org.jmock.MockObjectTestCase;

public class ServiceMockTests extends MockObjectTestCase {
	
    private final Log logger = LogFactory.getLog(ServiceMockTests.class);
    private CoreMgr mgr = new CoreMgrImpl();
    private Mock mockDAO = null;

    protected void setUp() throws Exception {
        mockDAO = new Mock(GenericDao.class);
        mgr.setGenericDao((GenericDao) mockDAO.proxy());
    }
    
    public void testUpdateEnumerator() {
    	
    	//params
    	Entity entity = new Entity();
        String type= "A_TYPE";
    	//populate result list with one entry
    	InternalEnumerator enumG = new InternalEnumerator();
    	enumG.setId(new Integer(1));
        enumG.setEntity(entity);
        enumG.setTypeName(type);
    	enumG.setLastNumber(10);
    	
        // set expected behavior on dao
    	String query = CoreMgrImpl.ENUM_QRY;
        mockDAO.expects(atLeastOnce())
    		.method("findUnique")
    		.with( stringContains(query), isA(Object[].class))
    		.will(returnValue(enumG));
        mockDAO.expects(atLeastOnce())
    		.method("update")
    		.with( same(enumG));

        int result = mgr.currentNumber(entity, type);
        logger.info(""+result);

        // verify expectations
        mockDAO.verify();
        assertEquals(result, 10);

    }

    public void testCreateEnumerator() {
    	
    	//params
        Entity entity = new Entity();
        String type= "A_TYPE";
    	
        // set expected behavior on dao
        String query = CoreMgrImpl.ENUM_QRY;
        mockDAO.expects(atLeastOnce())
        .method("findUnique")
        .with( stringContains(query), isA(Object[].class))
    		.will(returnValue(null));
        mockDAO.expects(atLeastOnce())
    		.method("save")
    		.with( isA(InternalEnumerator.class));

        int result = mgr.currentNumber(entity, type);
        logger.info(""+result);

        // verify expectations
        mockDAO.verify();
        assertEquals(result, 1);

    }

}

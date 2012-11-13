package org.helianto.core.service;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.reset;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.helianto.core.Entity;
import org.helianto.core.KeyType;
import org.helianto.core.Operator;
import org.helianto.core.Province;
import org.helianto.core.Service;
import org.helianto.core.UserGroup;
import org.helianto.core.UserRole;
import org.helianto.core.repository.BasicDao;
import org.helianto.core.repository.FilterDao;
import org.helianto.user.UserMgr;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * 
 * @author mauriciofernandesdecastro
 */
public class PostInstallationMgrImplTests {
	
	PostInstallationMgrImpl postInstallationMgr;
	
	@Test
	public void inProgress() {
		System.out.println("Not yet finished...");
	}
	
	public void installProvince() {
		Operator defaultOperator = new Operator("DEFAULT");
		List<Province> provinceList = new ArrayList<Province>();
		Province p1 = new Province(defaultOperator, "P1", "Province 1");
		provinceList.add(p1);
		Province p2 = new Province(defaultOperator, "P2", "Province 2");
		provinceList.add(p2);
//		Province parent
		
		EasyMock.expect(provinceDao.findUnique(defaultOperator, "P1")).andReturn(p1);
		EasyMock.expect(provinceDao.findUnique(defaultOperator, "P2")).andReturn(null);
		EasyMock.replay(provinceDao);
		
		postInstallationMgr.installProvinces(defaultOperator, provinceList);
	}

//	public void installProvinces(Operator defaultOperator, List<Province> provinceList) {
//		
//		logger.debug("Will install {} province(s) ...", provinceList.size());
//		for (Province p: provinceList) {
//			Province province = provinceDao.findUnique(defaultOperator, p.getProvinceCode());
//	    	if (province==null) {
//	    		logger.debug("New province {}", p.getProvinceCode());
//	    		p.setOperator(defaultOperator);
//	    		if (p.getParent()!=null) {
//	    			Province parent = provinceDao.findUnique(defaultOperator, p.getParent().getProvinceCode());
//	    			if (parent==null) {
//	    				logger.debug("New parent {}", p.getParent().getProvinceCode());
//	    				p.setParent(parent);
//	    			}
//	    		}
//		        provinceDao.saveOrUpdate(p);
//	    	}
//	    	else {
//		    	logger.debug("Province AVAILABLE as {}.", province);	    		
//	    	}
//		}
//		
//	}
	
	// collabs
	
	private BasicDao<Operator> operatorDao;
	private BasicDao<Province> provinceDao;
	private BasicDao<KeyType> keyTypeDao;
	private BasicDao<Service> serviceDao;
	private BasicDao<Entity> entityDao;
	private BasicDao<UserGroup> userGroupDao;
	private BasicDao<UserRole> userRoleDao;
	private ProvinceResourceParserStrategy provinceResourceParserStrategy;
	private IdentityMgr identityMgr;
	private UserMgr userMgr;
    
    @SuppressWarnings("unchecked")
	@Before
    public void setUp() {
        operatorDao = createMock(FilterDao.class);
        provinceDao = createMock(FilterDao.class);
        keyTypeDao = createMock(FilterDao.class);
        serviceDao = createMock(FilterDao.class);
        entityDao = createMock(FilterDao.class);
        userGroupDao = createMock(FilterDao.class);
        userRoleDao = createMock(FilterDao.class);
        provinceResourceParserStrategy = createMock(ProvinceResourceParserStrategy.class);
        identityMgr = createMock(IdentityMgr.class);
        userMgr = createMock(UserMgr.class);
        postInstallationMgr = new PostInstallationMgrImpl();
        postInstallationMgr.setOperatorDao(operatorDao);
        postInstallationMgr.setProvinceDao(provinceDao);
        postInstallationMgr.setKeyTypeDao(keyTypeDao);
        postInstallationMgr.setServiceDao(serviceDao);
        postInstallationMgr.setEntityDao(entityDao);
        postInstallationMgr.setUserGroupDao(userGroupDao);
        postInstallationMgr.setUserRoleDao(userRoleDao);
        postInstallationMgr.setProvinceResourceParserStrategy(provinceResourceParserStrategy);
        postInstallationMgr.setIdentityMgr(identityMgr);
        postInstallationMgr.setUserMgr(userMgr);
    }
    
    @After
    public void tearDown() {
        reset(operatorDao);
        reset(provinceDao);
        reset(keyTypeDao);
        reset(serviceDao);
        reset(entityDao);
        reset(userGroupDao);
        reset(userRoleDao);
        reset(provinceResourceParserStrategy);
        reset(identityMgr);
        reset(userMgr);
    }
    
}

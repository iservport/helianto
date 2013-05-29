package org.helianto.core.service;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.reset;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertSame;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.helianto.core.IdentityMgr;
import org.helianto.core.domain.KeyType;
import org.helianto.core.domain.Operator;
import org.helianto.core.domain.Province;
import org.helianto.core.domain.Service;
import org.helianto.core.repository.ContextRepository;
import org.helianto.core.repository.EntityRepository;
import org.helianto.core.repository.KeyTypeRepository;
import org.helianto.core.repository.ProvinceRepository;
import org.helianto.core.repository.ServiceRepository;
import org.helianto.core.service.internal.ProvinceResourceParserStrategy;
import org.helianto.user.UserMgr;
import org.helianto.user.repository.UserGroupRepository;
import org.helianto.user.repository.UserRoleRepository;
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
		
		EasyMock.expect(provinceRepository.findByOperatorAndProvinceCode(defaultOperator, "P1")).andReturn(p1);
		EasyMock.expect(provinceRepository.findByOperatorAndProvinceCode(defaultOperator, "P2")).andReturn(null);
		EasyMock.replay(provinceRepository);
		
		postInstallationMgr.installProvinces(defaultOperator, provinceList);
	}

//	public void installProvinces(Operator defaultOperator, List<Province> provinceList) {
//		
//		logger.debug("Will install {} province(s) ...", provinceList.size());
//		for (Province p: provinceList) {
//			Province province = provinceRepository.findUnique(defaultOperator, p.getProvinceCode());
//	    	if (province==null) {
//	    		logger.debug("New province {}", p.getProvinceCode());
//	    		p.setOperator(defaultOperator);
//	    		if (p.getParent()!=null) {
//	    			Province parent = provinceRepository.findUnique(defaultOperator, p.getParent().getProvinceCode());
//	    			if (parent==null) {
//	    				logger.debug("New parent {}", p.getParent().getProvinceCode());
//	    				p.setParent(parent);
//	    			}
//	    		}
//		        provinceRepository.saveOrUpdate(p);
//	    	}
//	    	else {
//		    	logger.debug("Province AVAILABLE as {}.", province);	    		
//	    	}
//		}
//		
//	}
	
	@Test
	public void installKey() {
		Operator operator = new Operator("DEFAULT");
		KeyType keyType = new KeyType(operator, "CODE");
		
		expect(keyTypeRepository.findByOperatorAndKeyCode(operator, "CODE")).andReturn(keyType);
		replay(keyTypeRepository);
		
		assertSame(keyType, postInstallationMgr.installKey(operator, "CODE"));
		verify(keyTypeRepository);

	}
	
	@Test
	public void installKeyNull() {
		Operator operator = new Operator("DEFAULT");
		KeyType keyType = new KeyType(operator, "CODE");
		
		expect(keyTypeRepository.findByOperatorAndKeyCode(operator, "CODE")).andReturn(null);
		expect(keyTypeRepository.saveAndFlush(new KeyType(operator, "CODE"))).andReturn(keyType);
		replay(keyTypeRepository);
		
		assertSame(keyType, postInstallationMgr.installKey(operator, "CODE"));
		verify(keyTypeRepository);

	}
	
	@Test
	public void installServiceNull() {
		Operator operator = new Operator("DEFAULT");
		Service service = new Service(operator, "CODE");
		
		serviceRepository.flush();
		expect(serviceRepository.findByOperatorAndServiceName(operator, "CODE")).andReturn(null);
		expect(serviceRepository.saveAndFlush(service)).andReturn(service);
		replay(serviceRepository);
		
		assertSame(service, postInstallationMgr.installService(operator, "CODE"));
		verify(serviceRepository);

	}
	
	// collabs
	
	private ContextRepository contextRepository;
	private ProvinceRepository provinceRepository;
	private KeyTypeRepository keyTypeRepository;
	private ServiceRepository serviceRepository;
	private EntityRepository entityRepository;
	private UserGroupRepository userGroupRepository;
	private UserRoleRepository userRoleRepository;
	private ProvinceResourceParserStrategy provinceResourceParserStrategy;
	private IdentityMgr identityMgr;
	private UserMgr userMgr;
    
	@Before
    public void setUp() {
        contextRepository = createMock(ContextRepository.class);
        provinceRepository = createMock(ProvinceRepository.class);
        keyTypeRepository = createMock(KeyTypeRepository.class);
        serviceRepository = createMock(ServiceRepository.class);
        entityRepository = createMock(EntityRepository.class);
        userGroupRepository = createMock(UserGroupRepository.class);
        userRoleRepository = createMock(UserRoleRepository.class);
        provinceResourceParserStrategy = createMock(ProvinceResourceParserStrategy.class);
        identityMgr = createMock(IdentityMgr.class);
        userMgr = createMock(UserMgr.class);
        postInstallationMgr = new PostInstallationMgrImpl();
        postInstallationMgr.setContextRepository(contextRepository);
        postInstallationMgr.setProvinceRepository(provinceRepository);
        postInstallationMgr.setKeyTypeRepository(keyTypeRepository);
        postInstallationMgr.setServiceRepository(serviceRepository);
        postInstallationMgr.setEntityRepository(entityRepository);
        postInstallationMgr.setUserRoleRepository(userRoleRepository);
        postInstallationMgr.setProvinceResourceParserStrategy(provinceResourceParserStrategy);
        postInstallationMgr.setIdentityMgr(identityMgr);
        postInstallationMgr.setUserMgr(userMgr);
    }
    
    @After
    public void tearDown() {
        reset(contextRepository);
        reset(provinceRepository);
        reset(keyTypeRepository);
        reset(serviceRepository);
        reset(entityRepository);
        reset(userGroupRepository);
        reset(userRoleRepository);
        reset(provinceResourceParserStrategy);
        reset(identityMgr);
        reset(userMgr);
    }
    
}

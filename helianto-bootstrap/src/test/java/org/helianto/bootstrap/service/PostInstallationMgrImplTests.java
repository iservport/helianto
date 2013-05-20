package org.helianto.bootstrap.service;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.reset;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.helianto.core.domain.Operator;
import org.helianto.core.domain.Province;
import org.helianto.core.repository.KeyTypeRepository;
import org.helianto.core.repository.OperatorRepository;
import org.helianto.core.repository.ProvinceRepository;
import org.helianto.core.service.IdentityMgr;
import org.helianto.core.service.strategy.ProvinceResourceParserStrategy;
import org.helianto.user.UserMgr;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * 
 * @author mauriciofernandesdecastro
 */
public class PostInstallationMgrImplTests {
	
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
		
		contextInstallationMgr.installProvinces(defaultOperator, provinceList);
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
	
	// collabs
	
	ContextInstallationMgrImpl contextInstallationMgr;
	
	private OperatorRepository operatorRepository;
	private ProvinceRepository provinceRepository;
	private KeyTypeRepository keyTypeRepository;
	private ProvinceResourceParserStrategy provinceResourceParserStrategy;
	private IdentityMgr identityMgr;
	private UserMgr userMgr;
    
	@Before
    public void setUp() {
    	operatorRepository = createMock(OperatorRepository.class);
    	provinceRepository = createMock(ProvinceRepository.class);
    	keyTypeRepository = createMock(KeyTypeRepository.class);
        provinceResourceParserStrategy = createMock(ProvinceResourceParserStrategy.class);
        identityMgr = createMock(IdentityMgr.class);
        userMgr = createMock(UserMgr.class);
        contextInstallationMgr = new ContextInstallationMgrImpl(operatorRepository, provinceRepository, keyTypeRepository, provinceResourceParserStrategy);
    }
    
    @After
    public void tearDown() {
        reset(operatorRepository);
        reset(provinceRepository);
        reset(keyTypeRepository);
        reset(provinceResourceParserStrategy);
        reset(identityMgr);
        reset(userMgr);
    }
    
}

package org.helianto.core.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.easymock.EasyMock;
import org.helianto.core.PublicEntityMgr;
import org.helianto.core.domain.Entity;
import org.helianto.core.domain.Operator;
import org.helianto.core.domain.PublicAddress;
import org.helianto.core.domain.PublicEntity;
import org.helianto.core.filter.Filter;
import org.helianto.core.filter.classic.TestingFilter;
import org.helianto.core.repository.PublicAddressRepository;
import org.helianto.core.repository.PublicEntityRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class PublicEntityMgrTests {
	
	private PublicEntityMgr publicEntityMgr;
	
	@Test
	public void findPublicAddress() {
		Filter filter = new TestingFilter();
		List<PublicAddress> publicAddressList = new ArrayList<PublicAddress>();
		
		EasyMock.expect(publicAddressRepository.find(filter)).andReturn(publicAddressList);
		EasyMock.replay(publicAddressRepository);
		
		assertEquals(publicAddressList, publicEntityMgr.findPublicAddress(filter));
		EasyMock.verify(publicAddressRepository);
	}
	
	@Test
	public void storePublicAddress() {
		PublicAddress publicAddress = new PublicAddress(operator, "POSTALCODE");
		
		EasyMock.expect(publicAddressRepository.saveAndFlush(publicAddress)).andReturn(publicAddress);
		EasyMock.replay(publicAddressRepository);
		
		assertEquals(publicAddress, publicEntityMgr.storePublicAddress(publicAddress));
		EasyMock.verify(publicAddressRepository);
	}
	
	@Test
	public void removePublicAddress() {
		PublicAddress publicAddress = new PublicAddress(operator, "POSTALCODE");
		
		publicAddressRepository.delete(publicAddress);
		EasyMock.replay(publicAddressRepository);
		
		publicEntityMgr.removePublicAddress(publicAddress);
		EasyMock.verify(publicAddressRepository);
	}
	
	///
	
	@Test
	public void findPublicEntities() {
		Filter filter = new TestingFilter();
		List<PublicEntity> publicEntityList = new ArrayList<PublicEntity>();
		
		EasyMock.expect(publicEntityRepository.find(filter)).andReturn(publicEntityList);
		EasyMock.replay(publicEntityRepository);
		
		assertEquals(publicEntityList, publicEntityMgr.findPublicEntities(filter));
		EasyMock.verify(publicEntityRepository);
	}
	
	@Test
	public void storePublicEntity() {
		PublicEntity publicEntity = new PublicEntity(entity);
		entity.setInstallDate(new Date());
		
		EasyMock.expect(publicEntityRepository.saveAndFlush(publicEntity)).andReturn(publicEntity);
		EasyMock.replay(publicEntityRepository);
		
		assertEquals(publicEntity, publicEntityMgr.storePublicEntity(publicEntity));
		EasyMock.verify(publicEntityRepository);
	}
	
	@Test
	public void removePublicEntity() {
		PublicEntity publicEntity = new PublicEntity(entity);
		
		publicEntityRepository.delete(publicEntity);
		EasyMock.replay(publicEntityRepository);
		
		publicEntityMgr.removePublicEntity(publicEntity);
		EasyMock.verify(publicEntityRepository);
	}
	

	
	// collabs
	
	private Operator operator;
	private Entity entity;
	private PublicAddressRepository publicAddressRepository;
	private PublicEntityRepository publicEntityRepository;
	
	@Before
	public void setUp() {
		operator = new Operator("DEFAULT");
		entity = new Entity(operator, "ENTITY");
		publicEntityMgr = new PublicEntityMgrImpl();
		publicAddressRepository = EasyMock.createMock(PublicAddressRepository.class);
		((PublicEntityMgrImpl) publicEntityMgr).setPublicAddressRepository(publicAddressRepository);
		publicEntityRepository = EasyMock.createMock(PublicEntityRepository.class);
		((PublicEntityMgrImpl) publicEntityMgr).setPublicEntityRepository(publicEntityRepository);
	}
	
	@After
	public void tearDown() {
		EasyMock.reset(publicAddressRepository);
		EasyMock.reset(publicEntityRepository);
	}

}

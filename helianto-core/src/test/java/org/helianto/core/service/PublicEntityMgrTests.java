package org.helianto.core.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.easymock.EasyMock;
import org.helianto.core.domain.Entity;
import org.helianto.core.domain.Operator;
import org.helianto.core.domain.PublicAddress;
import org.helianto.core.domain.PublicEntity;
import org.helianto.core.filter.Filter;
import org.helianto.core.filter.TestingFilter;
import org.helianto.core.repository.FilterDao;
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
		
		EasyMock.expect(publicAddressDao.find(filter)).andReturn(publicAddressList);
		EasyMock.replay(publicAddressDao);
		
		assertEquals(publicAddressList, publicEntityMgr.findPublicAddress(filter));
		EasyMock.verify(publicAddressDao);
	}
	
	@Test
	public void storePublicAddress() {
		PublicAddress publicAddress = new PublicAddress(operator, "POSTALCODE");
		
		publicAddressDao.saveOrUpdate(publicAddress);
		EasyMock.replay(publicAddressDao);
		
		assertEquals(publicAddress, publicEntityMgr.storePublicAddress(publicAddress));
		EasyMock.verify(publicAddressDao);
	}
	
	@Test
	public void removePublicAddress() {
		PublicAddress publicAddress = new PublicAddress(operator, "POSTALCODE");
		
		publicAddressDao.remove(publicAddress);
		EasyMock.replay(publicAddressDao);
		
		publicEntityMgr.removePublicAddress(publicAddress);
		EasyMock.verify(publicAddressDao);
	}
	
	///
	
	@Test
	public void findPublicEntities() {
		Filter filter = new TestingFilter();
		List<PublicEntity> publicEntityList = new ArrayList<PublicEntity>();
		
		EasyMock.expect(publicEntityDao.find(filter)).andReturn(publicEntityList);
		EasyMock.replay(publicEntityDao);
		
		assertEquals(publicEntityList, publicEntityMgr.findPublicEntities(filter));
		EasyMock.verify(publicEntityDao);
	}
	
	@Test
	public void storePublicEntity() {
		PublicEntity publicEntity = new PublicEntity(entity);
		entity.setInstallDate(new Date());
		
		publicEntityDao.saveOrUpdate(publicEntity);
		EasyMock.replay(publicEntityDao);
		
		assertEquals(publicEntity, publicEntityMgr.storePublicEntity(publicEntity));
		EasyMock.verify(publicEntityDao);
	}
	
	@Test
	public void removePublicEntity() {
		PublicEntity publicEntity = new PublicEntity(entity);
		
		publicEntityDao.remove(publicEntity);
		EasyMock.replay(publicEntityDao);
		
		publicEntityMgr.removePublicEntity(publicEntity);
		EasyMock.verify(publicEntityDao);
	}
	

	
	// collabs
	
	private Operator operator;
	private Entity entity;
	private FilterDao<PublicAddress> publicAddressDao;
	private FilterDao<PublicEntity> publicEntityDao;
	
	@SuppressWarnings("unchecked")
	@Before
	public void setUp() {
		operator = new Operator("DEFAULT");
		entity = new Entity(operator, "ENTITY");
		publicEntityMgr = new PublicEntityMgrImpl();
		publicAddressDao = EasyMock.createMock(FilterDao.class);
		((PublicEntityMgrImpl) publicEntityMgr).setPublicAddressDao(publicAddressDao);
		publicEntityDao = EasyMock.createMock(FilterDao.class);
		((PublicEntityMgrImpl) publicEntityMgr).setPublicEntityDao(publicEntityDao);
	}
	
	@After
	public void tearDown() {
		EasyMock.reset(publicAddressDao);
		EasyMock.reset(publicEntityDao);
	}

}

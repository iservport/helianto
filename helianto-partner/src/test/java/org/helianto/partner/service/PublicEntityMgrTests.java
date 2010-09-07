package org.helianto.partner.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.easymock.classextension.EasyMock;
import org.helianto.core.Entity;
import org.helianto.core.Operator;
import org.helianto.core.repository.FilterDao;
import org.helianto.partner.PublicAddress;
import org.helianto.partner.PublicAddressFilter;
import org.helianto.partner.PublicEntity;
import org.helianto.partner.PublicEntityFilter;
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
		PublicAddressFilter filter = new PublicAddressFilter(operator);
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
		PublicEntityFilter filter = new PublicEntityFilter(operator);
		List<PublicEntity> publicEntityList = new ArrayList<PublicEntity>();
		
		EasyMock.expect(publicEntityDao.find(filter)).andReturn(publicEntityList);
		EasyMock.replay(publicEntityDao);
		
		assertEquals(publicEntityList, publicEntityMgr.findPublicEntities(filter));
		EasyMock.verify(publicEntityDao);
	}
	
	@Test
	public void storePublicEntity() {
		PublicEntity publicEntity = new PublicEntity(entity);
		
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
	private FilterDao<PublicAddress, PublicAddressFilter> publicAddressDao;
	private FilterDao<PublicEntity, PublicEntityFilter> publicEntityDao;

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

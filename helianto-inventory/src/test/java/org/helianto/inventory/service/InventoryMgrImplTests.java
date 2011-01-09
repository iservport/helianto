package org.helianto.inventory.service;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.reset;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertSame;

import java.util.ArrayList;
import java.util.List;

import org.helianto.core.Entity;
import org.helianto.core.filter.ListFilter;
import org.helianto.core.repository.FilterDao;
import org.helianto.core.service.SequenceMgr;
import org.helianto.inventory.ProcessAgreement;
import org.helianto.inventory.ProcessRequirement;
import org.helianto.inventory.ProcessRequirementFilter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class InventoryMgrImplTests {

	@Test
	public void findProcessRequirements() {
		List<ProcessRequirement> requirementList = new ArrayList<ProcessRequirement>();
		ListFilter filter = new ProcessRequirementFilter(new Entity());
		
		expect(processRequirementDao.find(filter)).andReturn(requirementList);
		replay(processRequirementDao);
		
		assertSame(requirementList, inventoryMgr.findProcessRequirements(filter));
		verify(processRequirementDao);
	}
	
	@Test
	public void storeProcessRequirement() {
		ProcessRequirement requirement = new ProcessRequirement();
		
		sequenceMgr.validateInternalNumber(requirement);
		replay(sequenceMgr);
		
		processRequirementDao.saveOrUpdate(requirement);
		replay(processRequirementDao);
		
		assertSame(requirement, inventoryMgr.storeProcessRequirement(requirement));
		verify(processRequirementDao);
	}
	
	// collabs
	
	private InventoryMgrImpl inventoryMgr;
	private FilterDao<ProcessRequirement> processRequirementDao;
	private FilterDao<ProcessAgreement> agreementDao;
	private SequenceMgr sequenceMgr;
	
	@SuppressWarnings("unchecked")
	@Before
	public void setUp() {
		inventoryMgr = new InventoryMgrImpl();
		processRequirementDao = createMock(FilterDao.class);
		inventoryMgr.setProcessRequirementDao(processRequirementDao);
		agreementDao = createMock(FilterDao.class);
		inventoryMgr.setAgreementDao(agreementDao);
		sequenceMgr = createMock(SequenceMgr.class);
		inventoryMgr.setSequenceMgr(sequenceMgr);
	}
	
	@After
	public void tearDown() {
		reset(processRequirementDao);
		reset(agreementDao);
		reset(sequenceMgr);
	}
	

}

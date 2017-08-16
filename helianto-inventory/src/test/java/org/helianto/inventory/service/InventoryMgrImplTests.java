package org.helianto.inventory.service;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.reset;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertSame;

import org.helianto.classic.SequenceMgr;
import org.helianto.inventory.domain.ProcessAgreement;
import org.helianto.inventory.domain.ProcessRequirement;
import org.helianto.inventory.domain.Tax;
import org.helianto.inventory.repository.ProcessAgreementRepository;
import org.helianto.inventory.repository.ProcessRequirementRepository;
import org.helianto.inventory.repository.TaxRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class InventoryMgrImplTests {

	@Test
	public void storeProcessRequirement() {
		ProcessRequirement requirement = new ProcessRequirement();
		
		sequenceMgr.validateInternalNumber(requirement);
		replay(sequenceMgr);
		
		expect(processRequirementRepository.saveAndFlush(requirement)).andReturn(requirement);
		replay(processRequirementRepository);
		
		assertSame(requirement, inventoryMgr.storeProcessRequirement(requirement));
		verify(processRequirementRepository);
	}
	
	@Test
	public void storeProcessAgreement() {
		ProcessAgreement agreement = new ProcessAgreement();
		
		sequenceMgr.validateInternalNumber(agreement);
		replay(sequenceMgr);
		
		expect(processAgreementRepository.saveAndFlush(agreement)).andReturn(agreement);
		replay(processAgreementRepository);
		
		assertSame(agreement, inventoryMgr.storeProcessAgreement(agreement));
		verify(processAgreementRepository);
		verify(sequenceMgr);
	}
	
	@Test
	public void storeTax() {
		Tax tax = new Tax();
		
		expect(taxRepository.saveAndFlush(tax)).andReturn(tax);
		replay(taxRepository);
		
		assertSame(tax, inventoryMgr.storeTax(tax));
		verify(taxRepository);
	}
	
	// collabs
	
	private InventoryMgrImpl inventoryMgr;
	private ProcessRequirementRepository processRequirementRepository;
	private ProcessAgreementRepository processAgreementRepository;
	private TaxRepository taxRepository;
	private SequenceMgr sequenceMgr;
	
	@Before
	public void setUp() {
		inventoryMgr = new InventoryMgrImpl();
		processRequirementRepository = createMock(ProcessRequirementRepository.class);
		inventoryMgr.setProcessRequirementRepository(processRequirementRepository);
		processAgreementRepository = createMock(ProcessAgreementRepository.class);
		inventoryMgr.setProcessAgreementRepository(processAgreementRepository);
		taxRepository = createMock(TaxRepository.class);
		inventoryMgr.setTaxRepository(taxRepository);
		sequenceMgr = createMock(SequenceMgr.class);
		inventoryMgr.setSequenceMgr(sequenceMgr);
	}
	
	@After
	public void tearDown() {
		reset(processRequirementRepository);
		reset(processAgreementRepository);
		reset(taxRepository);
		reset(sequenceMgr);
	}
	

}

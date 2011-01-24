package org.helianto.inventory;

import static org.junit.Assert.assertEquals;

import org.helianto.core.KeyType;
import org.helianto.inventory.filter.TaxFilterAdapter;
import org.helianto.partner.test.PartnerTestSupport;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class TaxFilterAdapterTests {
	
	@Test
	public void empty() {
		assertEquals("", filter.createCriteriaAsString());
	}
	
	@Test
	public void processAgreement() {
		target.setProcessAgreement(processAgreement);
		assertEquals("alias.processAgreement.id = 1 ", filter.createCriteriaAsString());
	}
	
	@Test
	public void keyType() {
		target.setKeyType(keyType);
		assertEquals("alias.keyType.id = 2 ", filter.createCriteriaAsString());
	}
	
	@Test
	public void key() {
		target.setProcessAgreement(processAgreement);
		target.setKeyType(keyType);
		assertEquals("alias.processAgreement.id = 1 AND alias.keyType.id = 2 ", filter.createCriteriaAsString());
	}
	
	@Test
	public void keyCode() {
		target.setProcessAgreement(processAgreement);
		filter.setKeyCode("CODE");
		assertEquals("alias.processAgreement.id = 1 AND alias.keyType.keyCode = 'CODE' ", filter.createCriteriaAsString());
	}
	
	private TaxFilterAdapter filter;
	private Tax target;
	private ProcessAgreement processAgreement;
	private KeyType keyType;
	
	@Before
	public void setUp() {
		target = new Tax();
		filter = new TaxFilterAdapter(target);
		processAgreement = new ProcessAgreement(PartnerTestSupport.createPartner());
		processAgreement.setId(1);
		keyType = new KeyType(processAgreement.getEntity().getOperator(), "KEYCODE");
		keyType.setId(2);
	}

}

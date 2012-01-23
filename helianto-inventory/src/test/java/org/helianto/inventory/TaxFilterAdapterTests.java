package org.helianto.inventory;

import static org.junit.Assert.assertEquals;

import org.helianto.core.Entity;
import org.helianto.core.KeyType;
import org.helianto.core.test.EntityTestSupport;
import org.helianto.inventory.filter.TaxFilterAdapter;
import org.helianto.partner.domain.Partner;
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
		Entity entity = EntityTestSupport.createEntity();
		processAgreement = new ProcessAgreement(new Partner(entity, "PARTNER"));
		processAgreement.setId(1);
		keyType = new KeyType(entity.getOperator(), "KEYCODE");
		keyType.setId(2);
	}

}

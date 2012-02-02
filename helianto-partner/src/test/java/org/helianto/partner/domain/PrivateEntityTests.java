package org.helianto.partner.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.helianto.core.Entity;
import org.helianto.core.base.AbstractAddress;
import org.helianto.core.service.SequenceMgrImpl;
import org.helianto.core.test.DomainTestSupport;
import org.helianto.partner.domain.PrivateEntity2;
import org.junit.Test;

/**
 * <code>PrivateEntity2</code> domain tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class PrivateEntityTests {
    
	@Test
	public void constructor() {
		PrivateEntity2 privateEntity = new PrivateEntity2();
		assertTrue(privateEntity instanceof AbstractAddress);
		assertEquals("", privateEntity.getEntityAlias());
		assertEquals("", privateEntity.getEntityName());
	}
    
	@Test
	public void constructorEntity() {
		Entity entity = new Entity();
		PrivateEntity2 privateEntity = new PrivateEntity2(entity);
		assertTrue(privateEntity instanceof AbstractAddress);
		assertEquals(entity, privateEntity.getEntity());
	}
    
//	@Test
//	public void number() {
//		Entity entity = new Entity();
//		PrivateEntity2 privateEntity = new PrivateEntity2(entity, "#");
//		privateEntity.setInternalNumber(10000);
//		assertEquals("10000", privateEntity.getEntityAlias());
//		// do not change again
//		privateEntity.setInternalNumber(2);
//		assertEquals("10000", privateEntity.getEntityAlias());
//	}
//    
	@Test
	public void force() {
		Entity entity = new Entity();
		PrivateEntity2 privateEntity = new PrivateEntity2(entity, "ENTITY");
		privateEntity.setAutoNumber(true);
		SequenceMgrImpl sequenceMgr = new SequenceMgrImpl() {
			@Override public long newInternalNumber(Entity entity, String internalNumberKey) {
				return 200;
			}
		};
		sequenceMgr.validateInternalNumber(privateEntity);
		assertEquals("200", privateEntity.getEntityAlias());
	}
    
    /**
     * Test <code>PartnerRegistry</code> equals() method.
     */
	@Test
    public void testPartnerRegistryEquals() {
        Entity entity = new Entity();
        String partnerAlias = DomainTestSupport.STRING_TEST_VALUE;
        
        PrivateEntity2 privateEntity = new PrivateEntity2(entity, partnerAlias);
        PrivateEntity2 copy = (PrivateEntity2) DomainTestSupport.minimalEqualsTest(privateEntity);
        
        copy.setEntity(null);
        copy.setEntityAlias(partnerAlias);
        assertFalse(privateEntity.equals(copy));

        copy.setEntity(entity);
        copy.setEntityAlias(null);
        assertFalse(privateEntity.equals(copy));

        copy.setEntity(entity);
        copy.setEntityAlias(partnerAlias);

        assertTrue(privateEntity.equals(copy));
    }

}
    
    
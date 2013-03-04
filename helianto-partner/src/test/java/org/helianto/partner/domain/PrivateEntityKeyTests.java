package org.helianto.partner.domain;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.helianto.core.domain.KeyType;
import org.helianto.core.test.DomainTestSupport;
import org.junit.Test;

/**
 * <code>PrivateEntityKey</code> domain tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class PrivateEntityKeyTests {
    
	@Test
    public void constructor() {
        PrivateEntity2 partnerRegistry = new PrivateEntity2();
        KeyType keyType = new KeyType();
        
        PrivateEntityKey partnerRegistryKey = new PrivateEntityKey(partnerRegistry, keyType);
        
        assertSame(partnerRegistry, partnerRegistryKey.getPrivateEntity());
        assertSame(keyType, partnerRegistryKey.getKeyType());
        
    }
    
	@Test
    public void equality() {
        PrivateEntity2 partnerRegistry = new PrivateEntity2();
        KeyType keyType = new KeyType();
        
        PrivateEntityKey partnerRegistryKey = new PrivateEntityKey(partnerRegistry, keyType);
        PrivateEntityKey copy = (PrivateEntityKey) DomainTestSupport.minimalEqualsTest(partnerRegistryKey);
        
        copy.setPrivateEntity(null);
        copy.setKeyType(keyType);
        assertFalse(partnerRegistryKey.equals(copy));

        copy.setPrivateEntity(partnerRegistry);
        copy.setKeyType(null);
        assertFalse(partnerRegistryKey.equals(copy));

        copy.setPrivateEntity(partnerRegistry);
        copy.setKeyType(keyType);

        assertTrue(partnerRegistryKey.equals(copy));
    }

}
    
    

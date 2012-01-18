package org.helianto.partner.domain;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.helianto.core.Category;
import org.helianto.core.Entity;
import org.helianto.core.def.CategoryGroup;
import org.helianto.core.test.DomainTestSupport;
import org.helianto.core.test.EntityTestSupport;
import org.helianto.partner.domain.Partner;
import org.helianto.partner.domain.PartnerCategory;
import org.helianto.partner.domain.PrivateEntity;
import org.junit.Test;

/**
 * @author Mauricio Fernandes de Castro
 */
public class PartnerCategoryTests {
    
	@Test
    public void testPartnerKeyEquals() {
		Entity entity = EntityTestSupport.createEntity();
        PrivateEntity privateEntity = new PrivateEntity(entity);
        Partner partner = new Partner(privateEntity);
        Category category = new Category(entity, CategoryGroup.NOT_DEFINED, "");
        
        PartnerCategory partnerKey = new PartnerCategory(partner, category);
        PartnerCategory copy = (PartnerCategory) DomainTestSupport.minimalEqualsTest(partnerKey);
        
        copy.setPartner(null);
        copy.setCategory(category);
        assertFalse(partnerKey.equals(copy));

        copy.setPartner(partner);
        copy.setCategory(null);
        assertFalse(partnerKey.equals(copy));

        copy.setPartner(partner);
        copy.setCategory(category);

        assertTrue(partnerKey.equals(copy));
    }

}
    
    

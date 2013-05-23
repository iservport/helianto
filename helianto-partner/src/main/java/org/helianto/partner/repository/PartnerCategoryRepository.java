package org.helianto.partner.repository;

import java.io.Serializable;

import org.helianto.core.data.FilterRepository;
import org.helianto.core.domain.Category;
import org.helianto.partner.domain.Partner;
import org.helianto.partner.domain.PartnerCategory;

/**
 * Partner category repository.
 * 
 * @author mauriciofernandesdecastro
 */
public interface PartnerCategoryRepository extends FilterRepository<PartnerCategory, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param partner
	 * @param category
	 */
	PartnerCategory findByPartnerAndCategory(Partner partner, Category category);
	
}

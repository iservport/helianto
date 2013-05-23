package org.helianto.partner.repository;

import java.io.Serializable;

import org.helianto.core.def.CategoryGroup;
import org.helianto.core.domain.Category;
import org.helianto.core.repository.CategoryRepository;
import org.helianto.core.test.AbstractJpaRepositoryIntegrationTest;
import org.helianto.partner.domain.Partner;
import org.helianto.partner.domain.PartnerCategory;
import org.helianto.partner.domain.PrivateEntity;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class PartnerCategoryRepositoryTests 
	extends AbstractJpaRepositoryIntegrationTest<PartnerCategory, PartnerCategoryRepository> {

	@Autowired
	private PartnerCategoryRepository repository;
	
	@Override
	protected PartnerCategoryRepository getRepository() {
		return repository;
	}

    private PrivateEntity privateEntity;
    private Partner partner;
    private Category category;
	
	@Autowired
	protected PrivateEntityRepository privateEntityRepository;

	@Autowired
	protected PartnerRepository partnerRepository;

	@Autowired
	protected CategoryRepository categoryRepository;

	@Override
	protected PartnerCategory getNewTarget() {
		return new PartnerCategory(partner, category);
	}

	@Override
	protected Serializable getTargetId(PartnerCategory target) {
		return target.getId();
	}

	@Override
	protected PartnerCategory findByKey() {
		return getRepository().findByPartnerAndCategory(partner, category);
	}
	
	@Override
	protected void setUp() {
		privateEntity = privateEntityRepository.save(new PrivateEntity(entity, "PARTNER"));
		partner = partnerRepository.save(new Partner(privateEntity));
		category = categoryRepository.save(new Category(entity, CategoryGroup.NOT_DEFINED, "CAT"));
	}

}

package org.helianto.partner.repository;

import java.io.Serializable;

import org.helianto.core.domain.Category;
import org.helianto.core.repository.CategoryRepository;
import org.helianto.core.test.AbstractJpaRepositoryIntegrationTest;
import org.helianto.partner.domain.Partner;
import org.helianto.partner.domain.PrivateEntity;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class PartnerRepositoryTests 
	extends AbstractJpaRepositoryIntegrationTest<Partner, PartnerRepository> {

	@Autowired
	private PartnerRepository repository;
	
	@Override
	protected PartnerRepository getRepository() {
		return repository;
	}

    private PrivateEntity privateEntity;
	
    private Category category;
	
	@Autowired
	protected PrivateEntityRepository privateEntityRepository;

	@Autowired
	protected CategoryRepository categoryRepository;

	@Override
	protected Partner getNewTarget() {
		privateEntity = privateEntityRepository.save(new PrivateEntity(entity, "PARTNER"));
		category = categoryRepository.save(new Category(entity, 'X', "CATEGORY"));
		return new Partner(privateEntity, category);
	}

	@Override
	protected Serializable getTargetId(Partner target) {
		return target.getId();
	}

	@Override
	protected Partner findByKey() {
		return getRepository().findByPrivateEntityAndCategory(privateEntity, category);
	}
	
}

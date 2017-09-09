package org.helianto.core.repository;

import java.io.Serializable;

import org.helianto.core.domain.Category2;
import org.helianto.core.test.AbstractJpaRepositoryIntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class CategoryRepositoryTests 
	extends AbstractJpaRepositoryIntegrationTest<Category2, CategoryRepository> {

	@Autowired
	private CategoryRepository repository;
	
	protected CategoryRepository getRepository() {
		return repository;
	}
	
	protected Category2 getNewTarget() {
		return new Category2(entity, 'N', "CODE");
	}
	
	protected Serializable getTargetId(Category2 target) {
		return target.getId();
	}
	
	protected Category2 findByKey() {
		return getRepository().findByEntityAndCategoryGroupAndCategoryCode(entity, 'N', "CODE");
	}
	
}

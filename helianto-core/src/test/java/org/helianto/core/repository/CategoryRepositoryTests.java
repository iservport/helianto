package org.helianto.core.repository;

import java.io.Serializable;

import org.helianto.core.domain.Category;
import org.helianto.core.test.AbstractJpaRepositoryIntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class CategoryRepositoryTests 
	extends AbstractJpaRepositoryIntegrationTest<Category, CategoryRepository> {

	@Autowired
	private CategoryRepository repository;
	
	protected CategoryRepository getRepository() {
		return repository;
	}
	
	protected Category getNewTarget() {
		return new Category(entity, 'N', "CODE");	
	}
	
	protected Serializable getTargetId(Category target) {
		return target.getId();
	}
	
	protected Category findByKey() {
		return getRepository().findByEntityAndCategoryGroupAndCategoryCode(entity, 'N', "CODE");
	}
	
}

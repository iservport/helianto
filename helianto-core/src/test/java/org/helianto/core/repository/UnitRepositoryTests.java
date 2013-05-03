package org.helianto.core.repository;

import java.io.Serializable;

import org.helianto.core.def.CategoryGroup;
import org.helianto.core.domain.Category;
import org.helianto.core.domain.Unit;
import org.helianto.core.test.AbstractJpaRepositoryIntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class UnitRepositoryTests extends AbstractJpaRepositoryIntegrationTest<Unit, UnitRepository> {

	@Autowired
	private CategoryRepository categoryRepository;
	
	private Category category;
	
	@Autowired
	private UnitRepository repository;
	
	protected UnitRepository getRepository() {
		return repository;
	}
	
	protected Unit getNewTarget() {
		return new Unit(category, "CODE");
	}
	
	protected Serializable getTargetId(Unit target) {
		return target.getId();
	}
	
	protected Unit findByKey() {
		return getRepository().findByCategoryAndUnitCode(category, "CODE");
	}
	
	@Override
	protected void setUp() {
		category = categoryRepository.save(new Category(entity, CategoryGroup.NOT_DEFINED, "CAT"));
	}
	
}

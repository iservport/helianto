package org.helianto.core.repository;

import static org.junit.Assert.assertEquals;

import java.io.Serializable;
import java.util.List;

import org.helianto.core.domain.ContextGroup;
import org.helianto.core.test.AbstractJpaRepositoryIntegrationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class ContextGroupRepositoryTests 
	extends AbstractJpaRepositoryIntegrationTest<ContextGroup, ContextGroupRepository> 
{

	@Autowired
	private ContextGroupRepository repository;
	
	protected ContextGroupRepository getRepository() {
		return repository;
	}
	
	protected ContextGroup getNewTarget() {
		return new ContextGroup(operator, "CODE");		
	}
	
	protected Serializable getTargetId(ContextGroup target) {
		return target.getId();
	}
	
	protected ContextGroup findByKey() {
		return getRepository().findByContextIdAndContextGroupCode(operator.getId(), "CODE");
	}
	
	@Test
	@Transactional
	public void adapter() {
		ContextGroup contextGroup = getRepository().saveAndFlush(getNewTarget());
		Pageable page = new PageRequest(0, 1);
		List<ContextGroupReadAdapter> contextList = getRepository().findByContextId(operator.getId(), page);
		for (ContextGroupReadAdapter adapter: contextList) {
			assertEquals(adapter.getContextGroupCode(), contextGroup.getContextGroupCode());
			assertEquals(adapter.getContextId(), contextGroup.getContext().getId());
			break;
		}
	}
	
}

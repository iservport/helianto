package org.helianto.core.repository;

import static org.junit.Assert.assertEquals;

import java.io.Serializable;
import java.util.List;

import org.helianto.core.domain.Feature;
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
public class FeatureRepositoryTests 
	extends AbstractJpaRepositoryIntegrationTest<Feature, FeatureRepository> 
{

	@Autowired
	private FeatureRepository repository;
	
	protected FeatureRepository getRepository() {
		return repository;
	}
	
	protected Feature getNewTarget() {
		return new Feature(operator, "CODE");		
	}
	
	protected Serializable getTargetId(Feature target) {
		return target.getId();
	}
	
	protected Feature findByKey() {
		return getRepository().findByContextIdAndFeatureCode(operator.getId(), "CODE");
	}
	
	@Test
	@Transactional
	public void adapter() {
		Feature feature = getRepository().saveAndFlush(getNewTarget());
		Pageable page = new PageRequest(0, 1);
		List<FeatureReadAdapter> featureList = getRepository().findByContextAndFeatureType(operator, 'S', page);
		for (FeatureReadAdapter adapter: featureList) {
			assertEquals(adapter.getFeatureCode(), feature.getFeatureCode());
			assertEquals(adapter.getContextId(), feature.getContext().getId());
			break;
		}
	}
	
}

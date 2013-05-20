package org.helianto.core.test;

import javax.annotation.Resource;

import org.helianto.core.domain.Entity;
import org.helianto.core.domain.Operator;
import org.helianto.core.repository.ContextRepository;
import org.helianto.core.repository.EntityRepository;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Base class to integration tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"classpath:/META-INF/spring/core-context.xml"})
@Transactional
@ActiveProfiles(profiles = {"hibernate", "jpa"})
public abstract class AbstractDaoIntegrationTest {

	/**
	 * Provide the test infrastructure with an entity.
	 */
	public static Entity entity;
	public static long testKey = 0;
	
	@Before
	public void prepareSetUp() {
		logger.debug("PREPARE TEST");
		Operator operator = contextRepository.save(OperatorTestSupport.createOperator());

		entity = entityRepository.saveAndFlush(EntityTestSupport.createEntity(operator));
		logger.debug("START TEST");
	}
	
	// collabs

    private EntityRepository entityRepository;
	private ContextRepository contextRepository;
	
	public ContextRepository getContextRepository() {
		return contextRepository;
	}
	@Resource
	public void setContextRepository(ContextRepository contextRepository) {
		this.contextRepository = contextRepository;
	}
	
	public EntityRepository getEntityRepository() {
		return entityRepository;
	}
	@Resource
	public void setEntityRepository(EntityRepository entityRepository) {
		this.entityRepository = entityRepository;
	}
	
	private static final Logger logger = LoggerFactory.getLogger(AbstractDaoIntegrationTest.class);
	
}

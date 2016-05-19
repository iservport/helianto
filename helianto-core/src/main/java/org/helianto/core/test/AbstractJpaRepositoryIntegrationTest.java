package org.helianto.core.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;
import java.util.List;

import org.helianto.core.config.HeliantoServiceConfig;
import org.helianto.core.domain.Entity;
import org.helianto.core.domain.Operator;
import org.helianto.core.repository.EntityRepository;
import org.helianto.core.repository.IdentityRepository;
import org.helianto.core.repository.OperatorRepository;
import org.helianto.user.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Base class to jpa repository integration tests.
 * 
 * @author Mauricio Fernandes de Castro
 * @param <R> repository under test
 * @param <T> target class
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={TestDataSourceConfig.class, HeliantoServiceConfig.class})
@ActiveProfiles("standalone")
@Transactional
public abstract class AbstractJpaRepositoryIntegrationTest<T, R extends JpaRepository<T, Serializable>> {

	@Autowired
    protected IdentityRepository identityRepository;
    
	@Autowired
    protected UserRepository userRepository;
    
	@Autowired
    protected EntityRepository entityRepository;
    
	@Autowired
    protected OperatorRepository operatorRepository;

    /**
	 * Provide the test infrastructure with an entity.
	 */
	public static Operator operator;
	public static Entity entity;
	public static long testKey = 0;
	
	/**
	 * Repository under test.
	 */
	protected abstract R getRepository();
	
	/**
	 * Test instance.
	 */
	protected abstract T getNewTarget();
	
	/**
	 * Target id getter.
	 * 
	 * @param target
	 */
	protected abstract Serializable getTargetId(T target);
	
	/**
	 * Key test method.
	 */
	protected abstract T findByKey();
	
	@Test
	@Transactional
	public void repository() throws Exception {
		T target = getNewTarget();
		getRepository().save(target);
		getRepository().flush();
		assertNotNull(getTargetId(target));
		T other = findByKey();
		assertEquals(target, other);
		assertTrue(isFoundAsExpected(other));
	}
	
	/**
	 * Override if you do not expect findByKey to produce a persistent instance.
	 * 
	 * @param other
	 */
	protected boolean isFoundAsExpected(T other) {
		Iterable<T> resultList = getRepository().findAll();
		return ((List<T>) resultList).contains(other);
	}
	
	@Before
	public void prepareSetUp() {
		logger.debug("PREPARE TEST");
		operator = operatorRepository.save(OperatorTestSupport.createOperator());
		entity = entityRepository.save(EntityTestSupport.createEntity(operator));
		logger.debug("Additional SETUP");
		setUp();
		logger.debug("START TEST");
	}
	
	protected void setUp() { }
		
	private static final Logger logger = LoggerFactory.getLogger(AbstractJpaRepositoryIntegrationTest.class);
	
}

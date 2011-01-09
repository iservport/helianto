package org.helianto.core.test;

import javax.annotation.Resource;

import org.helianto.core.Entity;
import org.helianto.core.Operator;
import org.helianto.core.repository.FilterDao;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
		"classpath:/META-INF/spring/hibernate-context.xml", 
		"classpath:/META-INF/spring/core-context.xml"})
@Transactional
public abstract class AbstractDaoIntegrationTest {

	/**
	 * Provide the test infrastructure with an entity.
	 */
	public static Entity entity;
	public static long testKey = 0;
	
	@Before
	public void prepareSetUp() {
		logger.debug("PREPARE TEST");
		Operator operator = OperatorTestSupport.createOperator();
		operatorDao.saveOrUpdate(operator);

		entity = EntityTestSupport.createEntity(operator);
			entityDao.saveOrUpdate(entity);
		logger.debug("START TEST");
	}
	
	// collabs

    private FilterDao<Entity> entityDao;
	private FilterDao<Operator> operatorDao;
	
	public FilterDao<Operator> getOperatorDao() {
		return operatorDao;
	}
	@Resource(name="operatorDao")
	public void setOperatorDao(FilterDao<Operator> operatorDao) {
		this.operatorDao = operatorDao;
	}
	
	public FilterDao<Entity> getEntityDao() {
		return entityDao;
	}
	@Resource(name="entityDao")
	public void setEntityDao(FilterDao<Entity> entityDao) {
		this.entityDao = entityDao;
	}
	
	private static final Logger logger = LoggerFactory.getLogger(AbstractDaoIntegrationTest.class);
	
}

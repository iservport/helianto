package org.helianto.core.test;

import javax.annotation.Resource;

import org.helianto.core.Entity;
import org.helianto.core.EntityFilter;
import org.helianto.core.Operator;
import org.helianto.core.OperatorFilter;
import org.helianto.core.repository.FilterDao;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Base class to integration tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"classpath:/META-INF/spring/hibernate-context.xml", 
		"classpath:/META-INF/spring/core-context.xml"})
public abstract class AbstractDaoIntegrationTest {

	/**
	 * Provide the test infrastructure with an entity.
	 */
	public static Entity entity;
	public static long testKey = 0;
	
	@Before
	public void setUp() {
		Operator operator = OperatorTestSupport.createOperator();
		operatorDao.saveOrUpdate(operator);

		entity = EntityTestSupport.createEntity(operator);
			entityDao.saveOrUpdate(entity);
	}
	
	// collabs

    private FilterDao<Entity, EntityFilter> entityDao;
	private FilterDao<Operator, OperatorFilter> operatorDao;
	
	public FilterDao<Operator, OperatorFilter> getOperatorDao() {
		return operatorDao;
	}
	@Resource(name="operatorDao")
	public void setOperatorDao(FilterDao<Operator, OperatorFilter> operatorDao) {
		this.operatorDao = operatorDao;
	}
	
	public FilterDao<Entity, EntityFilter> getEntityDao() {
		return entityDao;
	}
	@Resource(name="entityDao")
	public void setEntityDao(FilterDao<Entity, EntityFilter> entityDao) {
		this.entityDao = entityDao;
	}
	
}

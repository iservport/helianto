package org.helianto.core.test;

import javax.annotation.Resource;

import org.helianto.core.Entity;
import org.helianto.core.EntityFilter;
import org.helianto.core.dao.FilterDao;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.transaction.annotation.Transactional;

/**
 * Base class to integration tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
@ContextConfiguration(locations={
		"classpath:/META-INF/spring/hibernate-context.xml", 
		"classpath:/META-INF/spring/core-context.xml"})
public abstract class AbstractDaoIntegrationTest extends AbstractTransactionalJUnit4SpringContextTests {

	/**
	 * Provide the test infrastructure with an entity.
	 */
	public static Entity entity = EntityTestSupport.createEntity();
	public static long testKey = 0;
	
	@Test
	@Transactional
	public void findUnique() {
	}

	@Before
	public void setUp() {
		if (entity.getId()==0) {
			entityDao.persist(entity);
		}
	}
	
	// collabs

    @Resource
	protected FilterDao<Entity, EntityFilter> entityDao;
	
}

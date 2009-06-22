package org.helianto.core.test;

import org.junit.Test;
import org.junit.runner.RunWith;
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
		"classpath:/META-INF/spring/dataSource.xml", 
		"classpath:/META-INF/spring/hibernate-context.xml", 
		"classpath:/META-INF/spring/core-context.xml"})
public abstract class AbstractDaoIntegrationTest {

	@Test
	@Transactional
	public abstract void testFindUnique();

}

package org.helianto.core.test;

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

	@Test
	@Transactional
	public abstract void findUnique();

}

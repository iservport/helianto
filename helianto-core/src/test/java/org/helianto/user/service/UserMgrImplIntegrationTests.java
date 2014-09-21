package org.helianto.user.service;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.helianto.core.config.HeliantoServiceConfig;
import org.helianto.core.config.MessageTestConfig;
import org.helianto.core.test.TestDataSourceConfig;
import org.helianto.user.UserMgr;
import org.helianto.user.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={TestDataSourceConfig.class, HeliantoServiceConfig.class, MessageTestConfig.class})
@ActiveProfiles("standalone")
@Transactional
public class UserMgrImplIntegrationTests {

	@Test
	public void nature() {
		List<User> userList = userMgr.findUsers("DEFAULT", "manager", 'C');
		assertNotNull(userList);
	}
	
	@Autowired
	private UserMgr userMgr;

}

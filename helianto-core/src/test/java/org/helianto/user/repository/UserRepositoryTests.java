package org.helianto.user.repository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.helianto.core.config.HeliantoServiceConfig;
import org.helianto.core.test.TestDataSourceConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={TestDataSourceConfig.class, HeliantoServiceConfig.class})
@ActiveProfiles("standalone")
@Transactional
public class UserRepositoryTests {
	
	@Inject
	private UserRepository userRepository;

	@Test
	public void test() {
		Pageable page = new PageRequest(0,1,Direction.ASC, "userKey");
		List<Integer> exclude = new ArrayList<>();
		exclude.add(0);
		userRepository.searchByParentUserType(1,exclude, "", 'I', "I".toCharArray(), page);
	}

}

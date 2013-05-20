package org.helianto.core.service;

import static org.junit.Assert.assertSame;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.helianto.core.SequenceMgr;
import org.helianto.core.filter.Filter;
import org.helianto.core.form.CompositeIdentityForm;
import org.helianto.user.domain.UserRequest;
import org.helianto.user.form.UserRequestForm;
import org.helianto.user.repository.UserRequestRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class UserRequestMgrImplTests {
	
	@Test
	public void store() {
		UserRequest userRequest = new UserRequest();
		
		EasyMock.expect(userRequestRepository.saveAndFlush(userRequest)).andReturn(userRequest);
		EasyMock.replay(userRequestRepository);
		
		sequenceMgr.validateInternalNumber(userRequest);
		EasyMock.replay(sequenceMgr);
		
		assertSame(userRequest, loginRequestMgr.storeUserRequest(userRequest));
		EasyMock.verify(userRequestRepository);
		EasyMock.verify(sequenceMgr);
	}
	
	@Test
	public void find() {
		List<UserRequest> userRequestList = new ArrayList<UserRequest>();
		UserRequestForm form = new CompositeIdentityForm("TESTE");
		
		EasyMock.expect(userRequestRepository.find(EasyMock.isA(Filter.class))).andReturn(userRequestList);
		EasyMock.replay(userRequestRepository);
		
		assertSame(userRequestList, loginRequestMgr.findUserRequests(form));
		EasyMock.verify(userRequestRepository);
	}
	
	private UserRequestMgrImpl loginRequestMgr;
	private UserRequestRepository userRequestRepository;
	private SequenceMgr sequenceMgr;
	
	@SuppressWarnings("unchecked")
	@Before
	public void setUp() {
		loginRequestMgr = new UserRequestMgrImpl();
		userRequestRepository = EasyMock.createMock(UserRequestRepository.class);
		loginRequestMgr.setUserRequestRepository(userRequestRepository);
		sequenceMgr = EasyMock.createMock(SequenceMgr.class);
		loginRequestMgr.setSequenceMgr(sequenceMgr);
	}
	
	@After
	public void tearDown() {
		EasyMock.reset(userRequestRepository);
		EasyMock.reset(sequenceMgr);
	}

}

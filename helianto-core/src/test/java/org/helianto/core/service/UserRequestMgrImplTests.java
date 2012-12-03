package org.helianto.core.service;

import static org.junit.Assert.assertSame;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.helianto.core.filter.Filter;
import org.helianto.core.filter.form.CompositeIdentityForm;
import org.helianto.core.filter.form.UserRequestForm;
import org.helianto.core.repository.FilterDao;
import org.helianto.user.domain.UserRequest;
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
		
		userRequestDao.saveOrUpdate(userRequest);
		userRequestDao.flush();
		EasyMock.replay(userRequestDao);
		sequenceMgr.validateInternalNumber(userRequest);
		EasyMock.replay(sequenceMgr);
		
		assertSame(userRequest, loginRequestMgr.storeUserRequest(userRequest));
		EasyMock.verify(userRequestDao);
		EasyMock.verify(sequenceMgr);
	}
	
	@Test
	public void find() {
		List<UserRequest> userRequestList = new ArrayList<UserRequest>();
		UserRequestForm form = new CompositeIdentityForm("TESTE");
		
		EasyMock.expect(userRequestDao.find(EasyMock.isA(Filter.class))).andReturn(userRequestList);
		EasyMock.replay(userRequestDao);
		
		assertSame(userRequestList, loginRequestMgr.findUserRequests(form));
		EasyMock.verify(userRequestDao);
	}
	
	private UserRequestMgrImpl loginRequestMgr;
	private FilterDao<UserRequest> userRequestDao;
	private SequenceMgr sequenceMgr;
	
	@SuppressWarnings("unchecked")
	@Before
	public void setUp() {
		loginRequestMgr = new UserRequestMgrImpl();
		userRequestDao = EasyMock.createMock(FilterDao.class);
		loginRequestMgr.setUserRequestDao(userRequestDao);
		sequenceMgr = EasyMock.createMock(SequenceMgr.class);
		loginRequestMgr.setSequenceMgr(sequenceMgr);
	}
	
	@After
	public void tearDown() {
		EasyMock.reset(userRequestDao);
		EasyMock.reset(sequenceMgr);
	}

}

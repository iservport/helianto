package org.helianto.document.service;

import static org.junit.Assert.assertSame;

import java.util.ArrayList;
import java.util.List;

import org.easymock.classextension.EasyMock;
import org.helianto.core.filter.Filter;
import org.helianto.core.filter.TestingFilter;
import org.helianto.core.repository.FilterDao;
import org.helianto.core.service.SequenceMgr;
import org.helianto.document.LoginRequest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class LoginRequestMgrImplTests {
	
	@Test
	public void store() {
		LoginRequest loginRequest = new LoginRequest();
		
		loginRequestDao.saveOrUpdate(loginRequest);
		EasyMock.replay(loginRequestDao);
		sequenceMgr.validateInternalNumber(loginRequest);
		EasyMock.replay(sequenceMgr);
		
		assertSame(loginRequest, loginRequestMgr.storeLoginRequest(loginRequest));
		EasyMock.verify(loginRequestDao);
		EasyMock.verify(sequenceMgr);
	}
	
	@Test
	public void find() {
		List<LoginRequest> loginRequestList = new ArrayList<LoginRequest>();
		Filter filter = new TestingFilter();
		
		EasyMock.expect(loginRequestDao.find(filter)).andReturn(loginRequestList);
		EasyMock.replay(loginRequestDao);
		
		assertSame(loginRequestList, loginRequestMgr.findLoginRequests(filter));
		EasyMock.verify(loginRequestDao);
	}
	
	private LoginRequestMgrImpl loginRequestMgr;
	private FilterDao<LoginRequest> loginRequestDao;
	private SequenceMgr sequenceMgr;
	
	@SuppressWarnings("unchecked")
	@Before
	public void setUp() {
		loginRequestMgr = new LoginRequestMgrImpl();
		loginRequestDao = EasyMock.createMock(FilterDao.class);
		loginRequestMgr.setLoginRequestDao(loginRequestDao);
		sequenceMgr = EasyMock.createMock(SequenceMgr.class);
		loginRequestMgr.setSequenceMgr(sequenceMgr);
	}
	
	@After
	public void tearDown() {
		EasyMock.reset(loginRequestDao);
		EasyMock.reset(sequenceMgr);
	}

}

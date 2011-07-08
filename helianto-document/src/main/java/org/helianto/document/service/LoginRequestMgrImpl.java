package org.helianto.document.service;

import java.util.List;

import javax.annotation.Resource;

import org.helianto.core.filter.Filter;
import org.helianto.core.repository.FilterDao;
import org.helianto.core.service.SequenceMgr;
import org.helianto.document.LoginRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Login request service interface implementation.
 * 
 * @author mauriciofernandesdecastro
 */
@Service("loginRequestMgr")
public class LoginRequestMgrImpl implements LoginRequestMgr {

	public List<LoginRequest> findLoginRequests(Filter filter) {
		List<LoginRequest> loginRequestList = (List<LoginRequest>) loginRequestDao.find(filter);
		if (loginRequestList!=null) {
			logger.debug("Found {} login request(s)", loginRequestList.size());
		}
		return loginRequestList;
	}

	public LoginRequest storeLoginRequest(LoginRequest loginRequest) {
		loginRequestDao.saveOrUpdate(loginRequest);
		sequenceMgr.validateInternalNumber(loginRequest);
		return loginRequest;
	}
	
	// collabs
	
	private FilterDao<LoginRequest> loginRequestDao;
	private SequenceMgr sequenceMgr;
	
	@Resource(name="loginRequestDao")
	public void setLoginRequestDao(FilterDao<LoginRequest> loginRequestDao) {
		this.loginRequestDao = loginRequestDao;
	}
	
	@Resource
	public void setSequenceMgr(SequenceMgr sequenceMgr) {
		this.sequenceMgr = sequenceMgr;
	}
	
	private final static Logger logger = LoggerFactory.getLogger(LoginRequestMgrImpl.class);

}

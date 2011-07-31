package org.helianto.core.service;

import java.util.List;

import javax.annotation.Resource;

import org.helianto.core.UserRequest;
import org.helianto.core.filter.Filter;
import org.helianto.core.repository.FilterDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * User request service interface implementation.
 * 
 * @author mauriciofernandesdecastro
 */
@Service("userRequestMgr")
public class UserRequestMgrImpl implements UserRequestMgr {

	public List<UserRequest> findUserRequests(Filter filter) {
		List<UserRequest> userRequestList = (List<UserRequest>) userRequestDao.find(filter);
		if (userRequestList!=null) {
			logger.debug("Found {} user request(s)", userRequestList.size());
		}
		return userRequestList;
	}

	public UserRequest storeUserRequest(UserRequest userRequest) {
		sequenceMgr.validateInternalNumber(userRequest);
		userRequestDao.saveOrUpdate(userRequest);
		userRequestDao.flush();
		return userRequest;
	}
	
	// collabs
	
	private FilterDao<UserRequest> userRequestDao;
	private SequenceMgr sequenceMgr;
	
	@Resource(name="userRequestDao")
	public void setUserRequestDao(FilterDao<UserRequest> userRequestDao) {
		this.userRequestDao = userRequestDao;
	}
	
	@Resource
	public void setSequenceMgr(SequenceMgr sequenceMgr) {
		this.sequenceMgr = sequenceMgr;
	}
	
	private final static Logger logger = LoggerFactory.getLogger(UserRequestMgrImpl.class);

}

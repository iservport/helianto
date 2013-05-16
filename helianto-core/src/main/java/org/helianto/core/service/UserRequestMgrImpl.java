package org.helianto.core.service;

import java.util.List;

import javax.annotation.Resource;

import org.helianto.core.SequenceMgr;
import org.helianto.core.UserRequestMgr;
import org.helianto.core.filter.Filter;
import org.helianto.core.filter.UserRequestFormFilterAdapter;
import org.helianto.core.repository.FilterDao;
import org.helianto.user.domain.UserRequest;
import org.helianto.user.form.UserRequestForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * User request service interface implementation.
 * 
 * @author mauriciofernandesdecastro
 */
@Service("userRequestMgr")
public class UserRequestMgrImpl implements UserRequestMgr {

	@Transactional(readOnly=true)
	public List<UserRequest> findUserRequests(UserRequestForm form) {
		Filter filter = new UserRequestFormFilterAdapter(form);
		List<UserRequest> userRequestList = (List<UserRequest>) userRequestDao.find(filter);
		if (userRequestList!=null) {
			logger.debug("Found {} user request(s)", userRequestList.size());
		}
		return userRequestList;
	}

	@Transactional
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

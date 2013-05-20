package org.helianto.core.service;

import java.util.List;

import javax.annotation.Resource;

import org.helianto.core.SequenceMgr;
import org.helianto.core.UserRequestMgr;
import org.helianto.core.filter.Filter;
import org.helianto.core.filter.UserRequestFormFilterAdapter;
import org.helianto.user.domain.UserRequest;
import org.helianto.user.form.UserRequestForm;
import org.helianto.user.repository.UserRequestRepository;
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
		List<UserRequest> userRequestList = (List<UserRequest>) userRequestRepository.find(filter);
		if (userRequestList!=null) {
			logger.debug("Found {} user request(s)", userRequestList.size());
		}
		return userRequestList;
	}

	@Transactional
	public UserRequest storeUserRequest(UserRequest userRequest) {
		sequenceMgr.validateInternalNumber(userRequest);
		return userRequestRepository.saveAndFlush(userRequest);
	}
	
	// collabs
	
	private UserRequestRepository userRequestRepository;
	private SequenceMgr sequenceMgr;
	
	@Resource
	public void setUserRequestRepository(UserRequestRepository userRequestRepository) {
		this.userRequestRepository = userRequestRepository;
	}
	
	@Resource
	public void setSequenceMgr(SequenceMgr sequenceMgr) {
		this.sequenceMgr = sequenceMgr;
	}
	
	private final static Logger logger = LoggerFactory.getLogger(UserRequestMgrImpl.class);

}

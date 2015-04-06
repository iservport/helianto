package org.helianto.core.service;

import javax.annotation.Resource;

import org.helianto.core.SequenceMgr;
import org.helianto.core.UserRequestMgr;
import org.helianto.user.domain.UserRequest;
import org.helianto.user.repository.UserRequestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * User request service interface implementation.
 * 
 * @author mauriciofernandesdecastro
 * @deprecated
 */
@Service("userRequestMgr")
public class UserRequestMgrImpl implements UserRequestMgr {

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

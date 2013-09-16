package org.helianto.security.service;

import java.util.List;

import javax.annotation.Resource;

import org.helianto.core.SecurityMgr;
import org.helianto.core.domain.Identity;
import org.helianto.core.domain.IdentitySecurity;
import org.helianto.core.repository.IdentitySecurityRepository;
import org.helianto.user.domain.User;
import org.helianto.user.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

/**
 * Base class to security details services.
 * 
 * @author mauriciofernandesdecastro
 */
public class AbstractDetailsService {

	@Transactional
	protected IdentitySecurity loadIdentitySecurityByKey(String[] keys) throws UsernameNotFoundException, DataAccessException {

		String consumerKey = keys[0];
		IdentitySecurity identitySecurity = null;
		// user input may be the numeric identity id
		try {
			List<IdentitySecurity> identitySecurityList = identitySecurityRepository.findByIdentityId(Long.parseLong(consumerKey));
			if (identitySecurityList!=null && identitySecurityList.size()>0) {
				identitySecurity = identitySecurityList.get(0);
			}
		}
		catch(Exception e) {
			logger.debug("Username is not a number");
		}
		
		if (identitySecurity==null) {
			identitySecurity = identitySecurityRepository.findByConsumerKey(consumerKey);			
		}
		if (identitySecurity==null) {
			logger.info("Unable to load by user name with {}.", consumerKey);
			throw new UsernameNotFoundException("Unable to find user name for "+consumerKey);
		}
		return identitySecurity;
		
	}
	
	protected String[] split(String keys) {
		if (keys!=null && !keys.isEmpty()) {
			return keys.split(",");
		}
		throw new UsernameNotFoundException("There must be at least one key!");
	}
	
	protected List<User> loadUserListByIdentity(Identity identity) {
		List<User> userList = userRepository.findByIdentityIdOrderByLastEventDesc(identity.getId());
		logger.debug("Found {} user(s) matching {}.", userList.size(), identity.getDisplayName());
		
		if (userList==null || userList.size()==0) {
			throw new UsernameNotFoundException("Unable to find any user for "+identity.getDisplayName());
		}
		
		return userList;
	}
	
	//- collabs

    protected SecurityMgr securityMgr;
    protected IdentitySecurityRepository identitySecurityRepository;
    protected UserRepository userRepository;
    
    @Resource
    public void setSecurityMgr(SecurityMgr securityMgr) {
        this.securityMgr = securityMgr;
    }

    @Resource
    public void setIdentitySecurityRepository(IdentitySecurityRepository identitySecurityRepository) {
		this.identitySecurityRepository = identitySecurityRepository;
	}
    
    @Resource
    public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
    
    private static Logger logger = LoggerFactory.getLogger(AbstractDetailsService.class);
    
}

package org.helianto.security.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.helianto.core.SecurityMgr;
import org.helianto.core.domain.IdentitySecurity;
import org.helianto.core.repository.IdentitySecurityRepository;
import org.helianto.core.security.UserDetailsAdapter;
import org.helianto.core.security.UserSelectorStrategy;
import org.helianto.user.domain.User;
import org.helianto.user.domain.UserRole;
import org.helianto.user.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Return an <code>UserDetails</code> instance.
 * 
 * @author mauriciofernandesdecastro
 */
@Service("userDetailsService")
@Transactional
public class UserDetailsServiceImpl
	implements UserDetailsService {

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {

		if (username==null) {
			throw new UsernameNotFoundException("User name must not be null!");
		}
		String[] keys =username.split(",");
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
		String entityKey = null;
		if (keys.length>1) {
			entityKey = keys[1];
		}
		List<User> userList = userRepository.findByIdentityIdOrderByLastEventDesc(identitySecurity.getIdentity().getId());
		logger.debug("Found {} user(s) matching {}.", userList.size(), username);
		
		if (userList==null || userList.size()==0) {
			throw new UsernameNotFoundException("Unable to find any user for "+username);
		}
		
		User user = userSelectorStrategy.selectUser(userList, entityKey);
		user.setLastEvent(new Date());
		Set<UserRole> roles = securityMgr.findRoles(user, true);
		user = userRepository.saveAndFlush(user);
		
		UserDetails userDetails = new UserDetailsAdapter(user, identitySecurity, roles);
		
		if (!userDetails.isEnabled()) {
			logger.error("User not enabled");
			throw new DisabledException("User not enabled");
		}

		if (!userDetails.isAccountNonLocked()) {
			logger.error("User is Locked");
			throw new LockedException("User is Locked");
		}
		
		if (!userDetails.isCredentialsNonExpired()) {
			logger.error("Password Expired");
			throw new CredentialsExpiredException("Password Expired");
		}
		
		return userDetails;
	}
	
	//- collabs

    private SecurityMgr securityMgr;
    private UserSelectorStrategy userSelectorStrategy;
    private IdentitySecurityRepository identitySecurityRepository;
    private UserRepository userRepository;
    
    @Resource
    public void setSecurityMgr(SecurityMgr securityMgr) {
        this.securityMgr = securityMgr;
    }

    @Resource
    public void setUserSelectorStrategy(UserSelectorStrategy userSelectorStrategy) {
		this.userSelectorStrategy = userSelectorStrategy;
	}
    
    @Resource
    public void setIdentitySecurityRepository(IdentitySecurityRepository identitySecurityRepository) {
		this.identitySecurityRepository = identitySecurityRepository;
	}
    
    @Resource
    public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
    
    private static Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
    
}

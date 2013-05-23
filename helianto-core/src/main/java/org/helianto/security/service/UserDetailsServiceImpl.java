package org.helianto.security.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.helianto.core.SecurityMgr;
import org.helianto.core.domain.Credential;
import org.helianto.core.security.UserDetailsAdapter;
import org.helianto.core.security.UserSelectorStrategy;
import org.helianto.user.UserMgr;
import org.helianto.user.domain.User;
import org.helianto.user.domain.UserGroup;
import org.helianto.user.domain.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Create an <code>UserDetail</code> instance from the first valid user and the credential that
 * matches username.
 * 
 * @author mauriciofernandesdecastro
 */
@Service("userDetailsService")
@Transactional
public class UserDetailsServiceImpl
	implements UserDetailsService {

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
		Credential credential = securityMgr.findCredentialByPrincipal(username);
		if (credential==null) {
			throw new UsernameNotFoundException("Unable to find credential for "+username);
		}
		@SuppressWarnings("unchecked")
		List<UserGroup> userList = (List<UserGroup>) userMgr.findUsers(username);
		logger.debug("Found {} user(s) matching {}.", userList.size(), username);
		if (userList!=null && userList.size()>0) {
			User user = userSelectorStrategy.selectUser(userList);
			user.setLastEvent(new Date());
			userMgr.storeUserGroup(user);
			Set<UserRole> roles = securityMgr.findRoles(user, true);
			return new UserDetailsAdapter((User) userMgr.storeUserGroup(user), credential, roles);
		}
		throw new UsernameNotFoundException("Unable to find any user for "+username);
	}
	
	//- collabs

    private SecurityMgr securityMgr;
    private UserMgr userMgr;
    private UserSelectorStrategy userSelectorStrategy;
    
    @Resource
    public void setSecurityMgr(SecurityMgr securityMgr) {
        this.securityMgr = securityMgr;
    }

    @Resource
    public void setUserMgr(UserMgr userMgr) {
        this.userMgr = userMgr;
    }
    
    @Resource
    public void setUserSelectorStrategy(UserSelectorStrategy userSelectorStrategy) {
		this.userSelectorStrategy = userSelectorStrategy;
	}
    
    private static Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
    
}

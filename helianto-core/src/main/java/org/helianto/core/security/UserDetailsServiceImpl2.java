package org.helianto.core.security;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.helianto.core.Credential;
import org.helianto.core.User;
import org.helianto.core.UserGroup;
import org.helianto.core.UserRole;
import org.helianto.core.filter.UserFilterAdapter;
import org.helianto.core.service.SecurityMgr;
import org.helianto.user.UserMgr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Create an <code>UserDetail</code> instance from the first valid user and the credential that
 * matches username.
 * 
 * @author mauriciofernandesdecastro
 */
public class UserDetailsServiceImpl2 implements UserDetailsService {

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
		Credential credential = securityMgr.findCredentialByPrincipal(username);
		if (credential==null) {
			throw new UsernameNotFoundException("Unable to find credential for "+username);
		}
		UserFilterAdapter filter = new UserFilterAdapter(new User());
		filter.setUserKey(username);
		@SuppressWarnings("unchecked")
		List<UserGroup> userList = (List<UserGroup>) userMgr.findUsers(filter);
		logger.debug("Found {} user(s) matching {}.", userList.size(), username);
		if (userList!=null && userList.size()>0) {
			User user = userSelectorStrategy.selectUser(userList);
			user.setLastEvent(new Date());
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
    
    private static Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl2.class);
    
}

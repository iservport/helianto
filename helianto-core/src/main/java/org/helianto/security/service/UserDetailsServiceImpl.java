package org.helianto.security.service;

import java.util.List;

import javax.annotation.Resource;

import org.helianto.core.domain.IdentitySecurity;
import org.helianto.core.security.UserDetailsAdapter;
import org.helianto.core.security.UserSelectorStrategy;
import org.helianto.user.domain.User;
import org.springframework.dao.DataAccessException;
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
public class UserDetailsServiceImpl
	extends AbstractDetailsService
	implements UserDetailsService 
{

	@Transactional
	public UserDetails loadUserByUsername(String userKey) throws UsernameNotFoundException, DataAccessException {

		String[] keys = split(userKey);
		
		IdentitySecurity identitySecurity = loadIdentitySecurityByKey(keys);
		List<User> userList = loadUserListByIdentity(identitySecurity.getIdentity());
		
		String entityKey = null;
		if (keys.length>1) {
			entityKey = keys[1];
		}
		
		UserDetailsAdapter userDetails = userSelectorStrategy.selectUser(userList, identitySecurity, entityKey);
		User user = userRepository.saveAndFlush(userDetails.getUser());
		userDetails.grantAuthorities(securityMgr.findRoles(user, true));
		return userDetails;
		
	}
	
	// collabs
    protected UserSelectorStrategy userSelectorStrategy;
    
    @Resource
    public void setUserSelectorStrategy(UserSelectorStrategy userSelectorStrategy) {
		this.userSelectorStrategy = userSelectorStrategy;
	}
    
}

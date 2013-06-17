package org.helianto.security.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.helianto.core.SecurityMgr;
import org.helianto.core.domain.ConnectionData;
import org.helianto.core.repository.ConnectionDataRepository;
import org.helianto.core.security.UserDetailsAdapter;
import org.helianto.core.security.UserSelectorStrategy;
import org.helianto.user.domain.User;
import org.helianto.user.domain.UserRole;
import org.helianto.user.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Return an <code>UserDetails</code> instance.
 * 
 * <p>
 * This is a custom implementation of <code>org.springframework.security.core.userdetails.UserDetailsService</code>.
 * </p>
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
		ConnectionData connectionData = null;
		// user input may be the numeric identity id
		try {
			List<ConnectionData> connectionDataList = connectionDataRepository.findByIdentityId(Long.parseLong(consumerKey));
			if (connectionDataList!=null && connectionDataList.size()>0) {
				connectionData = connectionDataList.get(0);
			}
		}
		catch(Exception e) {
			logger.debug("Username is not a number");
		}
		if (connectionData==null) {
			connectionData = connectionDataRepository.findByConsumerKey(consumerKey);			
		}
		if (connectionData==null) {
			logger.info("Unable to load by user name with {}.", consumerKey);
			throw new UsernameNotFoundException("Unable to find user name for "+consumerKey);
		}
		String entityKey = null;
		if (keys.length>1) {
			entityKey = keys[1];
		}
		List<User> userList = userRepository.findByIdentityIdOrderByLastEventDesc(connectionData.getIdentity().getId());
		logger.debug("Found {} user(s) matching {}.", userList.size(), username);
		if (userList!=null && userList.size()>0) {
			User user = userSelectorStrategy.selectUser(userList, entityKey);
			user.setLastEvent(new Date());
			Set<UserRole> roles = securityMgr.findRoles(user, true);
			user = userRepository.saveAndFlush(user);
			return new UserDetailsAdapter(user, connectionData, roles);
		}
		throw new UsernameNotFoundException("Unable to find any user for "+username);
	}
	
	//- collabs

    private SecurityMgr securityMgr;
    private UserSelectorStrategy userSelectorStrategy;
    private ConnectionDataRepository connectionDataRepository;
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
    public void setConnectionDataRepository(ConnectionDataRepository connectionDataRepository) {
		this.connectionDataRepository = connectionDataRepository;
	}
    
    @Resource
    public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
    
    private static Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
    
}

package org.helianto.security.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.helianto.security.domain.UserAuthority;
import org.helianto.security.internal.UserDetailsAdapter;
import org.helianto.security.repository.UserAuthorityRepository;
import org.helianto.user.domain.UserGroup;
import org.helianto.user.repository.UserGroupRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

/**
 * Service to update authorization.
 * 
 * @author mauriciofernandesdecastro
 */
@Service
public class AuthorizationChecker {

    private static Logger logger = LoggerFactory.getLogger(AuthorizationChecker.class);

	@Inject
    protected UserAuthorityRepository userAuthorityRepository;
    
	@Inject
    protected UserGroupRepository userGroupRepository;
    
	/**
	 * List parent groups.
	 * 
	 * @param userId
	 */
	public List<UserGroup> listParentGroups(int userId) {
		return userGroupRepository.findParentsByChildId(userId);
	}
	
	/**
	 * Updates authorities for the given user.
	 * 
	 * @param userDetailsAdapter
	 * @param parentGroups
	 */
	public UserDetailsAdapter updateAuthorities(UserDetailsAdapter userDetailsAdapter, List<UserGroup> parentGroups) {
		List<UserAuthority> adapterList = userAuthorityRepository.findByUserGroupIdOrderByServiceCodeAsc(parentGroups);
        List<String> roleNames = UserAuthority.getRoleNames(adapterList);
        
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String roleName: roleNames) {
            authorities.add(new SimpleGrantedAuthority(roleName));
            logger.debug("Granted authority: {}.", roleName);
        }
        authorities.add(new SimpleGrantedAuthority("ROLE_CONTEXT_"+userDetailsAdapter.getContextId()));
        logger.debug("Granted authority for context id");
        authorities.add(new SimpleGrantedAuthority("ROLE_ENTITY_ID_"+userDetailsAdapter.getEntityId()));
        logger.debug("Granted authority for entity id");
        authorities.add(new SimpleGrantedAuthority("ROLE_USER_ID_"+userDetailsAdapter.getUserId()));
        logger.debug("Granted authority for user id");
        authorities.add(new SimpleGrantedAuthority("ROLE_SELF_ID_"+userDetailsAdapter.getIdentityId()));
        logger.debug("Granted authority for identity id");
        userDetailsAdapter.setAuthorities(authorities);
        return userDetailsAdapter;
	}
	
	/**
	 * Updates authorities for the given user.
	 * 
	 * @param userDetailsAdapter
	 */
	public UserDetailsAdapter updateAuthorities(UserDetailsAdapter userDetailsAdapter) {
		return updateAuthorities(userDetailsAdapter, listParentGroups(userDetailsAdapter.getUserId()));
	}
}

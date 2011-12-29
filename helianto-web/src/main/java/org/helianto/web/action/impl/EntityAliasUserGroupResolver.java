package org.helianto.web.action.impl;

import java.util.List;

import javax.annotation.Resource;

import org.helianto.core.UserGroup;
import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.core.filter.Filter;
import org.helianto.core.filter.base.AbstractFilter;
import org.helianto.core.service.UserMgr;
import org.helianto.web.action.UserGroupResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Use an entity alias to resolve user group.
 * 
 * @author mauriciofernandesdecastro
 */
public class EntityAliasUserGroupResolver implements UserGroupResolver {

	public UserGroup resolveUserGroup(String entityAlias) {
		@SuppressWarnings("unchecked")
		List<UserGroup> userGroupList = (List<UserGroup>) userMgr.findUsers(createUserFilter(entityAlias));
		if (userGroupList!=null && userGroupList.size()>0) {
			return userGroupList.get(0);
		}
		logger.warn("Unable to resolve entity.");
		return null;
	}
	
	/**
	 * Create a simple filter to resolve user group.
	 * 
	 * @param entityAlias
	 */
	protected Filter createUserFilter(final String entityAlias) {
		Filter userFilter = new AbstractFilter() {
			
			private static final long serialVersionUID = 1L;

			@Override
			protected void doSelect(OrmCriteriaBuilder mainCriteriaBuilder) { }
			
			@Override
			public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
				appendEqualFilter("entity.alias", entityAlias, mainCriteriaBuilder);
				appendEqualFilter("userKey", "USER", mainCriteriaBuilder);
			}
		};
		return userFilter;
	}
	
	// collabs
	
	private UserMgr userMgr;
	
	@Resource
	public void setUserMgr(UserMgr userMgr) {
		this.userMgr = userMgr;
	}
	
	private static final Logger logger = LoggerFactory.getLogger(EntityAliasUserGroupResolver.class);

}

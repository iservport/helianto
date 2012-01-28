package org.helianto.web.action.impl;

import java.util.List;

import javax.annotation.Resource;

import org.helianto.core.UserGroup;
import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.core.filter.base.AbstractFilter;
import org.helianto.core.repository.FilterDao;
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
		List<UserGroup> userGroupList = (List<UserGroup>) userGroupDao.find(new UserGroupAliasFilter(entityAlias));
		if (userGroupList!=null && userGroupList.size()>0) {
			return userGroupList.get(0);
		}
		logger.warn("Unable to resolve entity.");
		return null;
	}
	
	/**
	 * User Group filter inner class.
	 * 
	 * @author mauriciofernandesdecastro
	 */
	protected class UserGroupAliasFilter extends AbstractFilter {
		
		private static final long serialVersionUID = 1L;
		private String entityAlias;
		
		/**
		 * Constructor.
		 * 
		 * @param entityAlias
		 */
		UserGroupAliasFilter(String entityAlias) {
			super();
			this.entityAlias = entityAlias;
		}

		@Override
		protected void doSelect(OrmCriteriaBuilder mainCriteriaBuilder) { }
		
		@Override
		public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
			appendEqualFilter("entity.alias", entityAlias, mainCriteriaBuilder);
			appendEqualFilter("userKey", "USER", mainCriteriaBuilder);
		}
	}
	
	// collabs
	
	private FilterDao<UserGroup> userGroupDao;
	
	@Resource
	public void setUserGroupDao(FilterDao<UserGroup> userGroupDao) {
		this.userGroupDao = userGroupDao;
	}
	
	private static final Logger logger = LoggerFactory.getLogger(EntityAliasUserGroupResolver.class);

}

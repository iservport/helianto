package org.helianto.web.action.impl;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.helianto.core.Credential;
import org.helianto.core.Entity;
import org.helianto.core.PublicEntity2;
import org.helianto.core.User;
import org.helianto.core.UserRole;
import org.helianto.core.filter.Filter;
import org.helianto.core.filter.UserFormFilterAdapter;
import org.helianto.core.filter.form.CompositeEntityForm;
import org.helianto.core.filter.form.CompositeUserForm;
import org.helianto.core.filter.form.PublicEntityForm;
import org.helianto.core.filter.form.UserGroupForm;
import org.helianto.core.security.PublicUserDetails;
import org.helianto.core.security.UserDetailsAdapter;
import org.helianto.core.service.PublicEntityMgr;
import org.helianto.core.service.SecurityMgr;
import org.helianto.core.service.UserMgr;
import org.helianto.web.action.AbstractFilterAction;
import org.helianto.web.model.impl.UserModelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.webflow.core.collection.MutableAttributeMap;

/**
 * Presentation logic to select authorized users.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Component("authorizationAction")
public class AuthorizationActionImpl extends AbstractFilterAction<User> {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected String getModelName() {
		return userModelBuilder.getModelName();
	}
	
	protected CompositeUserForm getForm(MutableAttributeMap attributes) {
		return userModelBuilder.getForm(attributes);
	}

	protected Filter doCreateFilter(MutableAttributeMap attributes,	PublicUserDetails userDetails) {
		return new UserFormFilterAdapter(getForm(attributes));
	}
	
	@Override
	protected List<User> doFilter(MutableAttributeMap attributes, Filter filter, PublicUserDetails userDetails) {
		CompositeUserForm form = getForm(attributes);
		form.setParentUserKey("USER");
		form.setEntity(null);
		form.setIdentity(userDetails.getUser().getIdentity());
		return doFilter(form);
	}
	
	protected List<User> doFilter(Filter filter) {
		@SuppressWarnings("unchecked")
		List<User> userList = (List<User>) userMgr.findUsers(filter);
		return userList;
	}
	
	protected List<User> doFilter(UserGroupForm form) {
		@SuppressWarnings("unchecked")
		List<User> userList = (List<User>) userMgr.findUsers(form);
		return userList;
	}
	
	protected User doCreate(MutableAttributeMap attributes, PublicUserDetails userDetails) {
		return new User();
	}
	
	protected User doStore(User target) {
		return (User) userMgr.storeUserGroup(target);
	}
	
	public String authorize(User user, Credential credential) {
		logger.debug("Ready to authorize user {} with current credential...", user);
		if (user!=null && credential!=null) {
			if (user.getIdentity().getId()!=credential.getIdentity().getId()) {
				throw new IllegalArgumentException("Unable to auhtorize different identity.");
			}
			Set<UserRole> roles = securityMgr.findRoles(user, true);
			UserDetailsAdapter userDetails = new UserDetailsAdapter(user, credential, roles);
			securityMgr.authenticate(userDetails);
			logger.debug("Authorized.");
			return "success";
		}
		else {
			throw new IllegalArgumentException("Unable to auhtorize null user.");
		}
	}
	
	public String makePublic(Entity entity) {
		logger.debug("Ready to make {} public.", entity);
		PublicEntity2 publicEntity = publicEntityMgr.storePublicEntity(new PublicEntity2(entity));
		logger.debug("Created {}.", publicEntity);
		return "success";
	}
	
	/**
	 * Find a public profile, or create one, if does not exist.
	 * 
	 * @param entity
	 */
	@SuppressWarnings("unchecked")
	public PublicEntity2 findPublicProfile(Entity entity) {
		PublicEntityForm form = new CompositeEntityForm(entity);
		form.setType('P');
		List<PublicEntity2> publicEntityList = (List<PublicEntity2>) publicEntityMgr.findPublicEntities(form);
		if (publicEntityList!=null && publicEntityList.size()>0) {
			logger.info("Found {}.", publicEntityList.get(0));
			return publicEntityList.get(0);
		}
		PublicEntity2 publicEntity = publicEntityMgr.storePublicEntity(new PublicEntity2(entity));
		logger.info("Created {}.", publicEntity);
		return publicEntity;
	}

	// collabs
	
	protected UserModelBuilder userModelBuilder;
	protected UserMgr userMgr;
	private SecurityMgr securityMgr;
	private PublicEntityMgr publicEntityMgr;

	@Resource
	public void setUserModelBuilder(UserModelBuilder userModelBuilder) {
		this.userModelBuilder = userModelBuilder;
	}
	
	@Resource
	public void setUserMgr(UserMgr userMgr) {
		this.userMgr = userMgr;
	}
	
	@Resource
	public void setSecurityMgr(SecurityMgr securityMgr) {
		this.securityMgr = securityMgr;
	}
	
	@Resource
	public void setPublicEntityMgr(PublicEntityMgr publicEntityMgr) {
		this.publicEntityMgr = publicEntityMgr;
	}
	
	private static final Logger logger = LoggerFactory.getLogger(AuthorizationActionImpl.class);

}

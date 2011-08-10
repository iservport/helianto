package org.helianto.web.action.impl;

import javax.annotation.Resource;

import org.helianto.core.Entity;
import org.helianto.core.security.PublicUserDetails;
import org.helianto.core.service.PostInstallationMgr;
import org.helianto.web.action.AbstractAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.webflow.core.collection.MutableAttributeMap;

/**
 * Presentation logic to install a new Entity.
 * 
 * @author mauriciofernandesdecastro
 */
@Component("entityAction")
public class EntityActionImpl extends AbstractAction<Entity> {

	private static final long serialVersionUID = 1L;

	@PreAuthorize("hasRole('ROLE_ADMIN_MANAGER')")
	@Override
	protected Entity doCreate(MutableAttributeMap attributes, PublicUserDetails userDetails) {
		return new Entity(userDetails.getUser());
	}

	@PreAuthorize("hasRole('ROLE_ADMIN_MANAGER')")
	@Override
	protected Entity doStore(Entity target) {
		if (target.getManager()!=null) {
			logger.debug("Installation required with manager {}.", target.getManager());
			return postInstallationMgr.installEntity(target, false);
		}
		throw new IllegalArgumentException("Entity installation requires a transient manager!");
	}
	
	// collabs
	
	private PostInstallationMgr postInstallationMgr;
	
	@Resource
	public void setPostInstallationMgr(PostInstallationMgr postInstallationMgr) {
		this.postInstallationMgr = postInstallationMgr;
	}
	
	private static final Logger logger = LoggerFactory.getLogger(EntityActionImpl.class);

}

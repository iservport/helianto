package org.helianto.web.action.impl;

import java.util.List;

import javax.annotation.Resource;

import org.helianto.core.Entity;
import org.helianto.core.filter.ListFilter;
import org.helianto.core.filter.classic.EntityFilter;
import org.helianto.core.security.PublicUserDetails;
import org.helianto.core.service.NamespaceMgr;
import org.helianto.core.service.PostInstallationMgr;
import org.helianto.web.action.AbstractFilterAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.webflow.core.collection.MutableAttributeMap;

/**
 * 
 * @author mauriciofernandesdecastro
 */
@Component("entityAction")
public class EntityAction extends AbstractFilterAction<Entity> {

	private static final long serialVersionUID = 1L;

	@Override
	protected String getTargetName() { return "entity"; }

	@Override
	protected ListFilter doCreateFilter(MutableAttributeMap attributes, PublicUserDetails userDetails) {
		return new EntityFilter(userDetails.getOperator());
	}

	@Override
	protected List<Entity> doFilter(ListFilter filter) {
		return namespaceMgr.findEntities((EntityFilter) filter);
	}

	@Override
	protected Entity doCreate(MutableAttributeMap attributes, PublicUserDetails userDetails) {
		return new Entity(userDetails.getUser());
	}

	@Override
	protected Entity doStore(Entity target) {
		Entity entity = null;
		if (target.getManager()!=null) {
			logger.debug("Installation required with manager {}.", target.getManager());
			entity = postInstallationMgr.installEntity(target);
		}
		else {
			entity = namespaceMgr.storeEntity(target);
		}
		return entity;
	}
	
	// collabs
	
	private NamespaceMgr namespaceMgr;
	private PostInstallationMgr postInstallationMgr;
	
	@Resource
	public void setNamespaceMgr(NamespaceMgr namespaceMgr) {
		this.namespaceMgr = namespaceMgr;
	}
	
	@Resource
	public void setPostInstallationMgr(PostInstallationMgr postInstallationMgr) {
		this.postInstallationMgr = postInstallationMgr;
	}
	
	private static final Logger logger = LoggerFactory.getLogger(EntityAction.class);

}

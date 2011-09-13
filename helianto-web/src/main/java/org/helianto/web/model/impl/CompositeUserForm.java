package org.helianto.web.model.impl;

import org.helianto.core.Entity;
import org.helianto.core.Operator;
import org.helianto.core.Service;
import org.helianto.core.UserGroup;
import org.helianto.core.filter.form.AbstractUserForm;
import org.helianto.core.filter.form.ServiceForm;
import org.helianto.core.filter.form.UserRoleForm;

/**
 * Composite user form.
 * 
 * @author mauriciofernandesdecastro
 */
public class CompositeUserForm extends AbstractUserForm implements UserRoleForm, ServiceForm {

    private static final long serialVersionUID = 1L;
    private Operator operator;
	private UserGroup userGroup;
	private Service service;
	private String serviceExtension;
	private char activityState = ' ';
    private String serviceName;
    private String serviceNameLike;

	/** 
	 * Empty constructor.
	 */
    public CompositeUserForm() {
    }
    
	/** 
	 * Key constructor.
	 * 
	 * @param entity
	 * @param userKey
	 */
    public CompositeUserForm(Entity entity, String userKey) {
    	this();
    	setEntity(entity);
    	setUserKey(userKey);
    }
    
	/** 
	 * Entity constructor.
	 * 
	 * @param parent
	 */
    public CompositeUserForm(UserGroup parent) {
    	this(parent.getEntity(), "");
    	setParent(parent);
    }
    
    /**
     * If entity is not null, use the entity operator.
     */
    public Operator getOperator() {
    	if (getEntity()!=null) {
    		this.operator = getEntity().getOperator();
    	}
		return operator;
	}
    public void setOperator(Operator operator) {
		this.operator = operator;
	}
    
	public UserGroup getUserGroup() {
		return userGroup;
	}
	public void setUserGroup(UserGroup userGroup) {
		this.userGroup = userGroup;
	}

	public Service getService() {
		return service;
	}
	public void setService(Service service) {
		this.service = service;
	}

	public String getServiceExtension() {
		return serviceExtension;
	}
	public void setServiceExtension(String serviceExtension) {
		this.serviceExtension = serviceExtension;
	}

	public char getActivityState() {
		return activityState;
	}
	public void setActivityState(char activityState) {
		this.activityState = activityState;
	}
	
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	
	public String getServiceNameLike() {
		return serviceNameLike;
	}
	public void setServiceNameLike(String serviceNameLike) {
		this.serviceNameLike = serviceNameLike;
	}
    
}

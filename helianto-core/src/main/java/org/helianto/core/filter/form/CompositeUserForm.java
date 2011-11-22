package org.helianto.core.filter.form;

import org.helianto.core.Entity;
import org.helianto.core.Operator;
import org.helianto.core.Service;

/**
 * Composite user form.
 * 
 * @author mauriciofernandesdecastro
 */
public class CompositeUserForm extends AbstractUserForm  implements UserRoleForm, ServiceForm {

	private static final long serialVersionUID = 1L;
	private Service service;
	private String serviceExtension;
	private char activityState = ' ';
    private String serviceName;
    private String serviceNameLike;
    
    /**
     * Default constructor.
     */
    public CompositeUserForm() {
		super();
	}
    
    /**
     * Entity constructor.
     * 
     * @param entity
     */
    public CompositeUserForm(Entity entity) {
		this();
		setEntity(entity);
	}
    
    public void reset() { 
    	setUserType(' ');
    	setUserState(' ');
    }
    
    public Operator getOperator() {
    	if (getEntity()!=null) {
    		return getEntity().getOperator();
    	}
    	return null;
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

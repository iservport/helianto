package org.helianto.core.filter.form;

import org.helianto.core.Entity;
import org.helianto.core.Operator;
import org.helianto.core.Province;
import org.helianto.core.Service;
import org.helianto.core.UserGroup;

/**
 * Composite user form.
 * 
 * @author mauriciofernandesdecastro
 */
public class CompositeUserForm 

	extends AbstractUserForm 
	
	implements 
	  AssociationForm
	, UserRoleForm
	, ServiceForm
	, PersonalAddressForm
	, Cloneable
	
{

	private static final long serialVersionUID = 1L;
	private int childId;
	private Service service;
	private String serviceExtension;
	private char activityState = ' ';
    private String serviceName;
    private String serviceNameLike;
	private char addressType = ' ';
    private String postalCode;
    private Province province;
    private String cityName;
    
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
    	setAddressType(' ');
    }
    
    public Operator getOperator() {
    	if (getEntity()!=null) {
    		return getEntity().getOperator();
    	}
    	return null;
    }
    
    public int getChildId() {
		return childId;
	}
    public void setChildId(int childId) {
		this.childId = childId;
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

	public char getAddressType() {
		return addressType;
	}
	public void setAddressType(char addressType) {
		this.addressType = addressType;
	}
    
    public String getPostalCode() {
        return this.postalCode;
    }
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Province getProvince() {
        return this.province;
    }
    public void setProvince(Province province) {
        this.province = province;
    }
    
    public String getCityName() {
		return cityName;
	}
    public void setCityName(String cityName) {
		this.cityName = cityName;
	}
    
    /**
     * Helper method to clone the form and set a parent.
     * 
     * @param parent
     */
    public CompositeUserForm clone(UserGroup parent) {
    	try {
    		CompositeUserForm form = (CompositeUserForm) super.clone();
    		form.setParent(parent);
    		return form;
		} catch (CloneNotSupportedException e) {
			throw new UnsupportedOperationException("Unable to clone CompositeUserForm.");
		}
    }
    
}

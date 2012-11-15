package org.helianto.user.form;

import java.util.List;

import org.helianto.core.Entity;
import org.helianto.core.Operator;
import org.helianto.core.Province;
import org.helianto.core.Service;
import org.helianto.core.UserGroup;
import org.helianto.core.filter.form.AssociationForm;
import org.helianto.core.filter.form.PersonalAddressForm;
import org.helianto.core.filter.form.ServiceForm;

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
	private List<UserGroup> parentList;
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
    
    /**
     * Parent constructor.
     * 
     * @param entity
     */
    public CompositeUserForm(UserGroup parent) {
		this();
		super.setUserGroupParent(parent);
	}
    
    /**
     * User key constructor.
     * 
     * @param userKey
     */
    public CompositeUserForm(String userKey) {
		this();
		setUserKey(userKey);
	}
    
    public void reset() { 
    	setUserType(' ');
    	setUserState(' ');
    	setAddressType(' ');
    }
    
    public int getParentId() {
    	return getUserGroupParentId();
    }
    
	public List<UserGroup> getParentList() {
		return parentList;
	}
	public void setParentList(List<UserGroup> parentList) {
		this.parentList = parentList;
	}
	
    public int[] getUserGroupParentIdArray() {
    	if (getParentList()!=null) {
    		int[] parentIdArray = new int[getParentList().size()];
    		int i = 0;
    		for (UserGroup parent: getParentList()) {
    			parentIdArray[i++] = parent.getId();
    		}
        	return parentIdArray;
    	}
    	return new int[0];
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
	
	public int getServiceId() {
		if (getService()!=null) {
			return getService().getId();
		}
		return 0;
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
    		form.setUserGroupParent(parent);
    		return form;
		} catch (CloneNotSupportedException e) {
			throw new UnsupportedOperationException("Unable to clone CompositeUserForm.");
		}
    }
    
}

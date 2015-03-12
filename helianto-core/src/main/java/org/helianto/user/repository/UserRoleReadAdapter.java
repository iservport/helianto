package org.helianto.user.repository;

import org.helianto.core.domain.Service;
import org.helianto.user.domain.UserGroup;
import org.helianto.user.domain.UserRole;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * User role read adapter.
 * 
 * @author mauriciofernandesdecastro
 */
public class UserRoleReadAdapter {
	
	private UserRole adaptee;
	
	private int id;
	
	private int serviceId;
	
	private int userGroupId;
	
	private String serviceName;
	
	private String serviceExtension;
	
	private Character activityState;
	
	private String partnershipExtension;
	
	/**
	 * Constructor.
	 */
	public UserRoleReadAdapter() {
		super();
	}
	
	/**
	 * Adaptee constructor.
	 * 
	 * @param userRole
	 */
	public UserRoleReadAdapter(UserRole userRole) {
		this();
		setAdaptee(userRole);
	}
	
	/**
	 * Constructor.
	 * 
	 * @param id
	 * @param serviceId
	 * @param userGroupId
	 * @param serviceName
	 * @param serviceExtension
	 * @param partnershipExtension
	 */
	public UserRoleReadAdapter(int id
			, int serviceId
			, int userGroupId
			, String serviceName
			, String serviceExtension
			, String partnershipExtension
			) {
		super();
		this.id = id;
		this.serviceId = serviceId;
		this.userGroupId = userGroupId;
		this.serviceName = serviceName;
		this.serviceExtension = serviceExtension;
		this.partnershipExtension = partnershipExtension;
	}

	/**
	 * Constructor.
	 * 
	 * @param id
	 * @param serviceId
	 * @param userGroupId
	 * @param serviceName
	 * @param serviceExtension
	 * @param activityState
	 * @param partnershipExtension
	 */
	public UserRoleReadAdapter(int id
			, int serviceId
			, int userGroupId
			, String serviceName
			, String serviceExtension
			, Character activityState
			, String partnershipExtension
			) {
		this(id, serviceId, userGroupId, serviceName, serviceExtension, partnershipExtension);
		this.activityState = activityState;
	}
	
	/**
	 * Builder.
	 */
	public UserRoleReadAdapter build() {
		this.id = adaptee.getId();
		this.serviceId = adaptee.getService().getId();
		this.userGroupId = adaptee.getUserGroup().getId();
		this.serviceName = adaptee.getService().getServiceName();
		this.serviceExtension = adaptee.getServiceExtension();
		this.activityState = adaptee.getActivityState();
		this.partnershipExtension = adaptee.getPartnershipExtension();
		return this;
	}
	
	/**
	 * Merger.
	 * 
	 * @param service
	 * @param userGroup
	 */
	public UserRole merge(Service service, UserGroup userGroup) {
		adaptee.setId(id);
		adaptee.setService(service);
		adaptee.setUserGroup(userGroup);
		adaptee.setActivityState(activityState);
		adaptee.setPartnershipExtension(partnershipExtension);
		return adaptee;
	}
	
	@JsonIgnore
	public UserRole getAdaptee() {
		return adaptee;
	}
	public UserRoleReadAdapter setAdaptee(UserRole adaptee) {
		this.adaptee = adaptee;
		return this;
	}

	public int getId() {
		return id;
	}
	
	public int getServiceId() {
		return serviceId;
	}

	public int getUserGroupId() {
		return userGroupId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public String getServiceExtension() {
		return serviceExtension;
	}
	
	public Character getActivityState() {
		return activityState;
	}

	public String getPartnershipExtension() {
		return partnershipExtension;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserRoleReadAdapter other = (UserRoleReadAdapter) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
package org.helianto.user.repository;

/**
 * User role read adapter.
 * 
 * @author mauriciofernandesdecastro
 */
public class UserRoleReadAdapter {
	
	private int id;
	
	private int serviceId;
	
	private int userGroupId;
	
	private String serviceName;
	
	private String serviceExtension;
	
	private String partnershipExtension;

	public int getId() {
		return id;
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
	public UserRoleReadAdapter(int id, int serviceId, int userGroupId,
			String serviceName, String serviceExtension,
			String partnershipExtension) {
		super();
		this.id = id;
		this.serviceId = serviceId;
		this.userGroupId = userGroupId;
		this.serviceName = serviceName;
		this.serviceExtension = serviceExtension;
		this.partnershipExtension = partnershipExtension;
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
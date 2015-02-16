package org.helianto.core.repository;

import java.io.Serializable;
import java.util.Date;

/**
 * Entity read adapter.
 * 
 * @author mauriciofernandesdecastro
 */
public class EntityReadAdapter 
	implements Serializable
{
	
	private static final long serialVersionUID = 1L;

	protected int id;
    
	protected int contextId;
    
	protected String entityAlias;
	
	protected Date installDate;
    
	protected String summary;

	protected String entityDomain;

	protected String externalLogoUrl;
	
	protected String customProperties;
	
	protected Character activityState;
    
	protected Character entityType;

	public EntityReadAdapter() {
		super();
	}
	
	/**
	 * Constructor.
	 * 
	 * @param id
	 * @param contextId
	 * @param entityAlias
	 * @param installDate
	 * @param summary
	 * @param entityDomain
	 * @param externalLogoUrl
	 * @param customProperties
	 * @param activityState
	 * @param entityType
	 */
	public EntityReadAdapter(int id
			, int contextId
			, String entityAlias
			, Date installDate
			, String summary
			, String entityDomain
			, String externalLogoUrl
			, String customProperties
			, Character activityState
			, Character entityType
			) {
		super();
		this.id = id;
		this.contextId = contextId;
		this.entityAlias = entityAlias;
		this.installDate = installDate;
		this.summary = summary;
		this.entityDomain = entityDomain;
		this.externalLogoUrl = externalLogoUrl;
		this.customProperties = customProperties;
		this.activityState = activityState;
		this.entityType = entityType;
	}

	/**
	 * Constructor.
	 * 
	 * @param id
	 * @param contextId
	 * @param entityAlias
	 * @param installDate
	 * @param summary
	 * @param entityDomain
	 * @param externalLogoUrl
	 * @param customProperties
	 * @param activityState
	 * @param entityType
	 */
	public EntityReadAdapter(int id
			, int contextId
			, String entityAlias
			, Date installDate
			, String summary
			, String entityDomain
			, String externalLogoUrl
			, String customProperties
			, String activityState
			, String entityType
			) {
		this(id
			, contextId
			, entityAlias
			, installDate
			, summary
			, entityDomain
			, externalLogoUrl
			, customProperties
			, activityState!=null  && activityState.length()>0 ? activityState.charAt(0) : 'I'
			, entityType!=null  && entityType.length()>0 ? entityType.charAt(0) : 'C'
		);
	}

	public int getId() {
		return id;
	}

	public int getContextId() {
		return contextId;
	}

	public String getEntityAlias() {
		return entityAlias;
	}

	public Date getInstallDate() {
		return installDate;
	}

	public String getSummary() {
		return summary;
	}

	public String getEntityDomain() {
		return entityDomain;
	}

	public String getExternalLogoUrl() {
		return externalLogoUrl;
	}

	public String getCustomProperties() {
		return customProperties;
	}

	public Character getActivityState() {
		return activityState;
	}

	public Character getEntityType() {
		return entityType;
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
		EntityReadAdapter other = (EntityReadAdapter) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
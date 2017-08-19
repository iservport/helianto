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

	protected Integer id;
    
	protected String contextName;
    
	protected Integer userId;
    
	protected String entityAlias;
	
	protected String entityCode;
	
	protected Date installDate;
    
	protected String summary;

	protected String entityDomain;

	protected String externalLogoUrl;
	
	protected String customProperties;
	
	protected Character activityState;
    
	protected Character entityType;

	protected Integer cityId;

	protected String cityName;

	protected Integer stateId;

	protected String stateCode;

	protected String stateName;

	protected Integer countryId;

	public EntityReadAdapter() {
		super();
	}
	
	/**
	 * Constructor.
	 * 
	 * @param id
	 * @param contextName
	 * @param userId
	 * @param entityAlias
	 * @param entityCode
	 * @param installDate
	 * @param summary
	 * @param entityDomain
	 * @param externalLogoUrl
	 * @param customProperties
	 * @param activityState
	 * @param entityType
	 * @param cityId
	 * @param cityName
	 * @param stateId
	 * @param stateCode
	 * @param stateName
	 * @param countryId
	 */
	public EntityReadAdapter(int id
			, String contextName
			, Integer userId
			, String entityAlias
			, String entityCode
			, Date installDate
			, String summary
			, String entityDomain
			, String externalLogoUrl
			, String customProperties
			, Character activityState
			, Character entityType
			, Integer cityId
			, String cityName
			, Integer stateId
			, String stateCode
			, String stateName
			, Integer countryId
			) {
		super();
		this.id = id;
		this.contextName = contextName;
		this.userId = userId;
		this.entityAlias = entityAlias;
		this.entityCode = entityCode;
		this.installDate = installDate;
		this.summary = summary;
		this.entityDomain = entityDomain;
		this.externalLogoUrl = externalLogoUrl;
		this.customProperties = customProperties;
		this.activityState = activityState;
		this.entityType = entityType;
		this.cityId = cityId;
		this.cityName = cityName;
		this.stateId = stateId;
		this.stateCode = stateCode;
		this.stateName = stateName;
		this.countryId = countryId;
	}

	/**
	 * Constructor.
	 * 
	 * @param id
	 * @param contextName
	 * @param userId
	 * @param entityAlias
	 * @param installDate
	 * @param summary
	 * @param entityDomain
	 * @param externalLogoUrl
	 * @param customProperties
	 * @param activityState
	 * @param entityType
	 * @param cityId
	 * @param cityName
	 * @param stateId
	 * @param stateCode
	 * @param stateName
	 * @param countryId
	 * 
	 * @deprecated
	 */
	public EntityReadAdapter(int id
			, String contextName
			, Integer userId
			, String entityAlias
			, Date installDate
			, String summary
			, String entityDomain
			, String externalLogoUrl
			, String customProperties
			, Character activityState
			, Character entityType
			, Integer cityId
			, String cityName
			, Integer stateId
			, String stateCode
			, String stateName
			, Integer countryId
			) {
		super();
		this.id = id;
		this.contextName = contextName;
		this.userId = userId;
		this.entityAlias = entityAlias;
		this.installDate = installDate;
		this.summary = summary;
		this.entityDomain = entityDomain;
		this.externalLogoUrl = externalLogoUrl;
		this.customProperties = customProperties;
		this.activityState = activityState;
		this.entityType = entityType;
		this.cityId = cityId;
		this.cityName = cityName;
		this.stateId = stateId;
		this.stateCode = stateCode;
		this.stateName = stateName;
		this.countryId = countryId;
	}

	/**
	 * Constructor.
	 * 
	 * @param id
	 * @param contextName
	 * @param userId
	 * @param entityAlias
	 * @param entityCode
	 * @param installDate
	 * @param summary
	 * @param entityDomain
	 * @param externalLogoUrl
	 * @param customProperties
	 * @param activityState
	 * @param entityType
	 * @param cityId
	 * @param cityName
	 * @param stateId
	 * @param stateCode
	 * @param stateName
	 * @param countryId
	 */
	public EntityReadAdapter(Integer id
			, String contextName
			, Integer userId
			, String entityAlias
			, String entityCode
			, Date installDate
			, String summary
			, String entityDomain
			, String externalLogoUrl
			, String customProperties
			, String activityState
			, String entityType
			, Integer cityId
			, String cityName
			, Integer stateId
			, String stateCode
			, String stateName
			, Integer countryId
			) {
		this(id
			, contextName
			, userId
			, entityAlias
			, entityCode
			, installDate
			, summary
			, entityDomain
			, externalLogoUrl
			, customProperties
			, activityState!=null  && activityState.length()>0 ? activityState.charAt(0) : 'I'
			, entityType!=null  && entityType.length()>0 ? entityType.charAt(0) : 'C'
			, cityId
			, cityName
			, stateId
			, stateCode
			, stateName
			, countryId
		);
	}

	/**
	 * Constructor.
	 * 
	 * @param id
	 * @param contextName
	 * @param userId
	 * @param entityAlias
	 * @param installDate
	 * @param summary
	 * @param entityDomain
	 * @param externalLogoUrl
	 * @param customProperties
	 * @param activityState
	 * @param entityType
	 * @param cityId
	 * @param cityName
	 * @param stateId
	 * @param stateCode
	 * @param stateName
	 * @param countryId
	 * 
	 * @deprecated
	 */
	public EntityReadAdapter(Integer id
			, String contextName
			, Integer userId
			, String entityAlias
			, Date installDate
			, String summary
			, String entityDomain
			, String externalLogoUrl
			, String customProperties
			, String activityState
			, String entityType
			, Integer cityId
			, String cityName
			, Integer stateId
			, String stateCode
			, String stateName
			, Integer countryId
			) {
		this(id
			, contextName
			, userId
			, entityAlias
			, installDate
			, summary
			, entityDomain
			, externalLogoUrl
			, customProperties
			, activityState!=null  && activityState.length()>0 ? activityState.charAt(0) : 'I'
			, entityType!=null  && entityType.length()>0 ? entityType.charAt(0) : 'C'
			, cityId
			, cityName
			, stateId
			, stateCode
			, stateName
			, countryId
		);
	}

	public Integer getId() {
		return id;
	}

	public String getContextName() {
		return contextName;
	}

	public Integer getUserId() {
		return userId;
	}

	public String getEntityAlias() {
		return entityAlias;
	}
	
	public String getEntityCode() {
		return entityCode;
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
	
	public Integer getCityId() {
		return cityId;
	}
	
	public String getCityName() {
		return cityName;
	}
	
	public Integer getStateId() {
		return stateId;
	}
	
	public String getStateCode() {
		return stateCode;
	}
	
	public String getStateName() {
		return stateName;
	}
	
	public Integer getCountryId() {
		return countryId;
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
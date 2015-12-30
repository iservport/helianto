package org.helianto.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Features suggest information to be shared among entities.
 * 
 * @author mauriciofernandesdecastro
 */
@javax.persistence.Entity
@Table(name="core_feature",
    uniqueConstraints = {@UniqueConstraint(columnNames={"contextId", "featureCode"})}
)
public class Feature 
	implements Serializable
{

	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Version
	private int version;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="contextId")
	private Operator context;
	
	@Transient
	private Integer contextId;

	@Column(length=32)
	private String featureCode;

	@Column(length=128)
	private String featureName;

	@Column(length=1024)
	private String featureDesc;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="contextGroupId")
	private ContextGroup contextGroup;
	
	@Transient
	private Integer contextGroupId;

	private Character featureType = 'S';

	/**
	 * @deprecated
	 */
	@Column(length=32)
	private int osConstraints;
	
	@Column(length=128)
	private String constraints;
	
	@Column(length=512)
	private String policies;
	
	/**
	 * Constructor.
	 */
	public Feature() {
		super();
	}

	/**
	 * Constructor.
	 * 
	 * @param context
	 * @param featureCode
	 */
	public Feature(Operator context, String featureCode) {
		this();
		setContext(context);
		setFeatureCode(featureCode);
	}

	/**
	 * Constructor.
	 * 
	 * @param context
	 * @param featureCode
	 * @param featureType
	 */
	public Feature(Operator context, String featureCode, Character featureType) {
		this(context, featureCode);
		setFeatureType(featureType);
	}

	/**
	 * Constructor.
	 * 
	 * @param context
	 * @param featureCode
	 * @param featureType
	 * @param featureName
	 */
	public Feature(Operator context, String featureCode, Character featureType, String featureName) {
		this(context, featureCode, featureType);
		setFeatureName(featureName);
	}

	/**
	 * Constructor.
	 * 
	 * @param id
	 * @param contextId
	 * @param featureCode
	 * @param featureName
	 * @param featureType
	 */
	public Feature(int id, int contextId, String featureCode, String featureName, Character featureType) {
		this();
		setId(id);
		setContextId(contextId);
		setFeatureCode(featureCode);
		setFeatureName(featureName);
		setFeatureType(featureType);
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}

	@JsonIgnore
	public Operator getContext() {
		return context;
	}
	public void setContext(Operator context) {
		this.context = context;
	}
	
	public Integer getContextId() {
		if (getContext()!=null) {
			return getContext().getId();
		}
		return contextId;
	}
	public void setContextId(Integer contextId) {
		this.contextId = contextId;
	}

	public String getFeatureCode() {
		return featureCode;
	}
	public void setFeatureCode(String featureCode) {
		this.featureCode = featureCode;
	}

	public String getFeatureName() {
		return featureName;
	}
	public void setFeatureName(String featureName) {
		this.featureName = featureName;
	}
	
	public String getFeatureDesc() {
		return featureDesc;
	}
	public void setFeatureDesc(String featureDesc) {
		this.featureDesc = featureDesc;
	}
	
	@JsonIgnore
	public ContextGroup getContextGroup() {
		return contextGroup;
	}
	public void setContextGroup(ContextGroup contextGroup) {
		this.contextGroup = contextGroup;
	}
	
	/**
	 * <<Transient>> context group id.
	 * @return
	 */
	public Integer getContextGroupId() {
		if (getContextGroup()!=null) {
			return getContextGroup().getId();
		}
		return contextGroupId;
	}
	public void setContextGroupId(Integer contextGroupId) {
		this.contextGroupId = contextGroupId;
	}

	/**
	 * @deprecated use groupType in ContextGroup
	 */
	public Character getFeatureType() {
		return featureType;
	}
	public void setFeatureType(Character featureType) {
		this.featureType = featureType;
	}
	
	public int getOsConstraints() {
		return osConstraints;
	}
	public void setOsConstraints(int osConstraints) {
		this.osConstraints = osConstraints;
	}
	
	public String getConstraints() {
		return constraints;
	}
	public void setConstraints(String constraints) {
		this.constraints = constraints;
	}
	
	public String getPolicies() {
		return policies;
	}
	public void setPolicies(String policies) {
		this.policies = policies;
	}
	
	/**
	 * Merger.
	 * 
	 * @param command
	 */
	public Feature merge(Feature command) {
		setFeatureDesc(command.getFeatureDesc());
		setFeatureName(command.getFeatureName());
		setFeatureType(command.getFeatureType());
		setConstraints(command.getConstraints());
		setPolicies(command.getPolicies());
		return this;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((context == null) ? 0 : context.hashCode());
		result = prime * result
				+ ((featureCode == null) ? 0 : featureCode.hashCode());
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
		Feature other = (Feature) obj;
		if (context == null) {
			if (other.context != null)
				return false;
		} else if (!context.equals(other.context))
			return false;
		if (featureCode == null) {
			if (other.featureCode != null)
				return false;
		} else if (!featureCode.equals(other.featureCode))
			return false;
		return true;
	}

}

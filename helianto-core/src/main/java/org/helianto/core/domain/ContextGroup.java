package org.helianto.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import org.helianto.core.def.ContextGroupType;

/**
 * Master groups to be replicated across entities.
 * 
 * @author mauriciofernandesdecastro
 */
@javax.persistence.Entity
@Table(name="core_contextGroup",
    uniqueConstraints = {@UniqueConstraint(columnNames={"contextId", "contextGroupCode"})}
)
public class ContextGroup 
	implements Serializable
{

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Version
	private int version;
	
	@ManyToOne
	@JoinColumn(name="contextId")
	private Operator context;
	
	@Column(length=32)
	private String contextGroupCode;

	@Column(length=128)
	private String contextGroupName;
	
	@Enumerated(EnumType.STRING)
	private ContextGroupType contextGroupType;
	
    private Character groupType = ' ';
    
    private Integer priority = 0;
	
	/**
	 * Constructor.
	 */
	public ContextGroup() {
		super();
	}

	/**
	 * Constructor.
	 * 
	 * @param context
	 * @param contextGroupCode
	 */
	public ContextGroup(Operator context, String contextGroupCode) {
		this();
		setContext(context);
		setContextGroupCode(contextGroupCode);
	}

	/**
	 * Constructor.
	 * 
	 * @param context
	 * @param contextGroupCode
	 * @param contextGroupName
	 */
	public ContextGroup(Operator context, String contextGroupCode, String contextGroupName) {
		this(context, contextGroupCode);
		setContextGroupName(contextGroupName);
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

	public Operator getContext() {
		return context;
	}
	public void setContext(Operator context) {
		this.context = context;
	}

	public String getContextGroupCode() {
		return contextGroupCode;
	}
	public void setContextGroupCode(String contextGroupCode) {
		this.contextGroupCode = contextGroupCode;
	}

	public String getContextGroupName() {
		return contextGroupName;
	}
	public void setContextGroupName(String contextGroupName) {
		this.contextGroupName = contextGroupName;
	}
	
	public ContextGroupType getContextGroupType() {
		return contextGroupType;
	}
	public void setContextGroupType(ContextGroupType contextGroupType) {
		this.contextGroupType = contextGroupType;
	}
	
	public Character getGroupType() {
		return groupType;
	}
	public void setGroupType(Character groupType) {
		this.groupType = groupType;
	}
	
	/**
	 * @deprecated see groupType
	 */
	public Character getUserType() {
		return groupType;
	}
	public void setUserType(Character groupType) {
		this.groupType =groupType;
	}
	
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	
	public boolean isAutoCreate() {
		if (getContextGroupType()!=null) {
			return getContextGroupType().isAutoCreate();
		}
		return false;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((context == null) ? 0 : context.hashCode());
		result = prime
				* result
				+ ((contextGroupCode == null) ? 0 : contextGroupCode.hashCode());
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
		ContextGroup other = (ContextGroup) obj;
		if (context == null) {
			if (other.context != null)
				return false;
		} else if (!context.equals(other.context))
			return false;
		if (contextGroupCode == null) {
			if (other.contextGroupCode != null)
				return false;
		} else if (!contextGroupCode.equals(other.contextGroupCode))
			return false;
		return true;
	}

}

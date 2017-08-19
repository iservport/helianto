package org.helianto.core.domain;

import org.helianto.core.domain.enums.ContextGroupType;

import javax.persistence.*;
import java.io.Serializable;

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

	private String contextName;

	private Integer contextId;

	@Column(length=32)
	private String contextGroupCode;

	@Column(length=128)
	private String contextGroupName;
	
	@Column(length=2048)
	private String contextGroupDesc;
	
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
	 * @param contextName
	 * @param contextGroupCode
	 */
	public ContextGroup(String contextName, String contextGroupCode) {
		this();
		setContextName(contextName);
		setContextGroupCode(contextGroupCode);
	}

	/**
	 * Constructor.
	 * 
	 * @param contextName
	 * @param contextGroupCode
	 * @param contextGroupName
	 */
	public ContextGroup(String contextName, String contextGroupCode, String contextGroupName) {
		this(contextName, contextGroupCode);
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

	public String getContextName() {
		return contextName;
	}
	public void setContextName(String contextName) {
		this.contextName = contextName;
	}

	public Integer getContextId() {
		return contextId;
	}
	public void setContextId(Integer contextId) {
		this.contextId = contextId;
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
	
	public String getContextGroupDesc() {
		return contextGroupDesc;
	}
	public void setContextGroupDesc(String contextGroupDesc) {
		this.contextGroupDesc = contextGroupDesc;
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

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof ContextGroup)) return false;
        final ContextGroup other = (ContextGroup) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getId() != other.getId()) return false;
        if (this.getVersion() != other.getVersion()) return false;
        final Object this$contextName = this.getContextName();
        final Object other$contextName = other.getContextName();
        if (this$contextName == null ? other$contextName != null : !this$contextName.equals(other$contextName))
            return false;
        final Object this$contextId = this.getContextId();
        final Object other$contextId = other.getContextId();
        if (this$contextId == null ? other$contextId != null : !this$contextId.equals(other$contextId)) return false;
        final Object this$contextGroupCode = this.getContextGroupCode();
        final Object other$contextGroupCode = other.getContextGroupCode();
        if (this$contextGroupCode == null ? other$contextGroupCode != null : !this$contextGroupCode.equals(other$contextGroupCode))
            return false;
        final Object this$contextGroupName = this.getContextGroupName();
        final Object other$contextGroupName = other.getContextGroupName();
        if (this$contextGroupName == null ? other$contextGroupName != null : !this$contextGroupName.equals(other$contextGroupName))
            return false;
        final Object this$contextGroupDesc = this.getContextGroupDesc();
        final Object other$contextGroupDesc = other.getContextGroupDesc();
        if (this$contextGroupDesc == null ? other$contextGroupDesc != null : !this$contextGroupDesc.equals(other$contextGroupDesc))
            return false;
        final Object this$contextGroupType = this.getContextGroupType();
        final Object other$contextGroupType = other.getContextGroupType();
        if (this$contextGroupType == null ? other$contextGroupType != null : !this$contextGroupType.equals(other$contextGroupType))
            return false;
        final Object this$groupType = this.getGroupType();
        final Object other$groupType = other.getGroupType();
        if (this$groupType == null ? other$groupType != null : !this$groupType.equals(other$groupType)) return false;
        final Object this$priority = this.getPriority();
        final Object other$priority = other.getPriority();
        if (this$priority == null ? other$priority != null : !this$priority.equals(other$priority)) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getId();
        result = result * PRIME + this.getVersion();
        final Object $contextName = this.getContextName();
        result = result * PRIME + ($contextName == null ? 43 : $contextName.hashCode());
        final Object $contextId = this.getContextId();
        result = result * PRIME + ($contextId == null ? 43 : $contextId.hashCode());
        final Object $contextGroupCode = this.getContextGroupCode();
        result = result * PRIME + ($contextGroupCode == null ? 43 : $contextGroupCode.hashCode());
        final Object $contextGroupName = this.getContextGroupName();
        result = result * PRIME + ($contextGroupName == null ? 43 : $contextGroupName.hashCode());
        final Object $contextGroupDesc = this.getContextGroupDesc();
        result = result * PRIME + ($contextGroupDesc == null ? 43 : $contextGroupDesc.hashCode());
        final Object $contextGroupType = this.getContextGroupType();
        result = result * PRIME + ($contextGroupType == null ? 43 : $contextGroupType.hashCode());
        final Object $groupType = this.getGroupType();
        result = result * PRIME + ($groupType == null ? 43 : $groupType.hashCode());
        final Object $priority = this.getPriority();
        result = result * PRIME + ($priority == null ? 43 : $priority.hashCode());
        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof ContextGroup;
    }

    public String toString() {
        return "org.helianto.core.domain.ContextGroup(id=" + this.getId() + ", version=" + this.getVersion() + ", contextName=" + this.getContextName() + ", contextId=" + this.getContextId() + ", contextGroupCode=" + this.getContextGroupCode() + ", contextGroupName=" + this.getContextGroupName() + ", contextGroupDesc=" + this.getContextGroupDesc() + ", contextGroupType=" + this.getContextGroupType() + ", groupType=" + this.getGroupType() + ", priority=" + this.getPriority() + ")";
    }
}

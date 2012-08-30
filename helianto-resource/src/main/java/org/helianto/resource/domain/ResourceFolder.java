package org.helianto.resource.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.helianto.core.Entity;
import org.helianto.core.base.AbstractFolder;
import org.helianto.resource.def.ResourceType;

/**
 * Resource folder.
 * 
 * @author mauriciofernandesdecastro
 */
@javax.persistence.Entity
@Table(name="rsrc_folder",
    uniqueConstraints = {@UniqueConstraint(columnNames={"entityId", "builderCode"})}
)
public class ResourceFolder 

	extends AbstractFolder 

{

	private static final long serialVersionUID = 1L;
	private char resourceType;
	private Set<Resource> resources = new HashSet<Resource>();

	/**
	 * Default constructor.
	 */
	public ResourceFolder() {
		super();
	}

	/**
	 * Key constructor.
	 * 
	 * @param entity
	 * @param folderCode
	 */
	public ResourceFolder(Entity entity, String folderCode) {
		super(entity, folderCode);
		setResourceTypeAsEnum(ResourceType.EQUIPMENT);
	}
	
    /**
     * Resource type.
     */
    public char getResourceType() {
        return this.resourceType;
    }
    public void setResourceType(char resourceType) {
        this.resourceType = resourceType;
    }
    public void setResourceTypeAsEnum(ResourceType resourceType) {
        this.resourceType = resourceType.getValue();
    }
    
	/**
	 * Resource set.
	 */
	@OneToMany(mappedBy="resourceFolder")
	public Set<Resource> getResources() {
		return resources;
	}
	public void setResources(Set<Resource> resources) {
		this.resources = resources;
	}

	/**
	 * equals
	 */
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof ResourceFolder)) return false;
		return super.equals(other);
	}

}

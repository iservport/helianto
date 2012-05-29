package org.helianto.resource.domain;

import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.helianto.core.Entity;
import org.helianto.document.base.AbstractSerializer;

/**
 * Resource folder.
 * 
 * @author mauriciofernandesdecastro
 */
@javax.persistence.Entity
@Table(name="rsrc_folder",
    uniqueConstraints = {@UniqueConstraint(columnNames={"entityId", "builderCode"})}
)
public class ResourceFolder extends AbstractSerializer<Resource> {

	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public ResourceFolder() {
		super(null, "");
	}

	/**
	 * Key constructor.
	 * 
	 * @param entity
	 * @param folderCode
	 */
	public ResourceFolder(Entity entity, String folderCode) {
		super(entity, folderCode);
	}

}

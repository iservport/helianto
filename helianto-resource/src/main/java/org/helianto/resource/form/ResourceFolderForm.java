package org.helianto.resource.form;

import org.helianto.core.filter.form.FolderForm;

/**
 * Resource folder form.
 * 
 * @author mauriciofernandesdecastro
 */
public interface ResourceFolderForm extends FolderForm {
	
    /**
     * Resource type.
     */
    char getResourceType();

}

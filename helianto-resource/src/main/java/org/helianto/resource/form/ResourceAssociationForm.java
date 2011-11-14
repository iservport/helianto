package org.helianto.resource.form;

import org.helianto.core.Resettable;
import org.helianto.core.filter.form.ChildForm;
import org.helianto.core.filter.form.ParentForm;
import org.helianto.resource.domain.ResourceGroup;

/**
 * Resource association form.
 * 
 * @author mauriciofernandesdecastro
 */
public interface ResourceAssociationForm extends ParentForm<ResourceGroup>, ChildForm<ResourceGroup>, Resettable {
	
}

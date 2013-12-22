package org.helianto.partner.form;

import org.helianto.core.form.ParentForm;
import org.helianto.core.form.PriorityForm;
import org.helianto.core.form.PrivateEntityIdForm;
import org.helianto.partner.domain.PrivateEntity;

/**
 * Partner form interface.
 * 
 * @author mauriciofernandesdecastro
 */
public interface PartnerForm 
	extends 
	  PrivateEntityForm
	, ParentForm<PrivateEntity>
	, PrivateEntityIdForm
	, PriorityForm 

{
	
	/**
	 * Partner state.
	 */
	char getPartnerState();
	
}

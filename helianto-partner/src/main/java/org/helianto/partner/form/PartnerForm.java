package org.helianto.partner.form;

import org.helianto.core.Prioritizable;
import org.helianto.core.form.ParentForm;
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
	, Prioritizable 

{
	
	/**
	 * Partner state.
	 */
	char getPartnerState();
	
}

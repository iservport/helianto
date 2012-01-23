package org.helianto.partner.form;

import org.helianto.core.Prioritizable;
import org.helianto.core.filter.form.ParentForm;
import org.helianto.partner.domain.PrivateEntity2;

/**
 * Partner form interface.
 * 
 * @author mauriciofernandesdecastro
 */
public interface PartnerForm 

	extends 
	  PrivateEntityForm
	, ParentForm<PrivateEntity2>
	, Prioritizable 

{
	
	/**
	 * Partner state.
	 */
	char getPartnerState();
	
}

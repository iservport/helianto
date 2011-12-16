package org.helianto.partner.form;

import org.helianto.core.Prioritizable;

/**
 * Partner form interface.
 * 
 * @author mauriciofernandesdecastro
 */
public interface PartnerForm extends PrivateEntityForm, Prioritizable {
	
	/**
	 * Partner state.
	 */
	char getPartnerState();
	
}

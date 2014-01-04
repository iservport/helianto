package org.helianto.partner.form;

import org.helianto.core.form.KeyStringForm;

/**
 * Partner key form interface.
 * 
 * @author mauriciofernandesdecastro
 */
public interface PartnerKeyForm 
	extends KeyStringForm
	, PartnerIdForm
{
	
	/**
	 * Key code.
	 */
	String getKeyCode();
	
}

package org.helianto.inventory.form;

import org.helianto.core.form.KeyTypeIdForm;

/**
 * Tax form interface.
 * 
 * @author mauriciofernandesdecastro
 */
public interface TaxForm 
	extends KeyTypeIdForm
{

	/**
	 * Process agreement.
	 */
	int getProcessAgreementId();
	
	/**
	 * Key code.
	 */
	String getKeyCode();

}

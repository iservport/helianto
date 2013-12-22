package org.helianto.partner.form;

import org.helianto.core.form.EntityIdForm;

/**
 * Account form.
 * 
 * @author mauriciofernandesdecastro
 */
public interface AccountForm 
	extends EntityIdForm 
{

	/**
	 * Account code.
	 */
	String getAccountCode();

	/**
	 * Account name.
	 */
	String getAccountName();

	/**
	 * Account type.
	 */
	char getAccountType();

}

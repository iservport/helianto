package org.helianto.partner.form;

import org.helianto.core.domain.type.TrunkEntity;

/**
 * Account form.
 * 
 * @author mauriciofernandesdecastro
 */
public interface AccountForm extends TrunkEntity {

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

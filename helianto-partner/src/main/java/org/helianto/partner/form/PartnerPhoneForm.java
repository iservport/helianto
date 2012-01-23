package org.helianto.partner.form;

import org.helianto.core.filter.form.ParentForm;
import org.helianto.partner.domain.PrivateEntity2;

/**
 * Classes implementing this interface represent a partner phone.
 * 
 * @author mauriciofernandesdecastro
 */
public interface PartnerPhoneForm 

	extends 
	  ParentForm<PrivateEntity2>
	
{
	/**
	 * Sequence.
	 */
	int getSequence();
	
	/**
	 * Area code.
	 */
	String getAreaCode();
	
	/**
	 * Phone type.
	 */
	char getPhoneType();

}

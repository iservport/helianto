package org.helianto.partner.form;

import org.helianto.core.form.ParentForm;
import org.helianto.core.form.PrivateEntityIdForm;
import org.helianto.partner.domain.PrivateEntity;

/**
 * Classes implementing this interface represent a partner phone.
 * 
 * @author mauriciofernandesdecastro
 */
public interface PartnerPhoneForm 

	extends 
	  ParentForm<PrivateEntity>
	, PrivateEntityIdForm
	
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

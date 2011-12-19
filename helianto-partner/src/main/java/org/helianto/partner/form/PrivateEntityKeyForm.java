package org.helianto.partner.form;

import org.helianto.core.filter.form.KeyStringForm;
import org.helianto.core.filter.form.ParentForm;
import org.helianto.partner.domain.PrivateEntity;

/**
 * Classes implementing this interface represent a private entity key.
 * 
 * @author mauriciofernandesdecastro
 */
public interface PrivateEntityKeyForm 

	extends 
	  ParentForm<PrivateEntity>
	, KeyStringForm
	
{

}

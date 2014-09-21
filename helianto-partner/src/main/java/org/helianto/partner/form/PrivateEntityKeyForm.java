package org.helianto.partner.form;

import org.helianto.core.form.KeyStringForm;
import org.helianto.core.form.ParentForm;
import org.helianto.core.form.PrivateEntityIdForm;
import org.helianto.partner.domain.PrivateEntity;

/**
 * Classes implementing this interface represent a private entity key.
 * 
 * @author mauriciofernandesdecastro
 */
public interface PrivateEntityKeyForm 
	extends 
	  ParentForm<PrivateEntity>
	, PrivateEntityIdForm
	, KeyStringForm
{

}

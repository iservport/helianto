package org.helianto.partner.form;

import java.io.Serializable;

import org.helianto.core.domain.Category;
import org.helianto.core.filter.form.ParentForm;
import org.helianto.partner.domain.Partner;
import org.helianto.partner.domain.PrivateEntity2;

/**
 * Classes implementing this interface represent a partner category.
 * 
 * @author mauriciofernandesdecastro
 */
public interface PartnerCategoryForm 

	extends 
	  Serializable
	, ParentForm<PrivateEntity2>
	
{
	
	/**
	 * Partner.
	 */
	Partner getPartner();

	/**
	 * Category.
	 */
	Category getCategory();

}

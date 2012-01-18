package org.helianto.partner.form;

import java.io.Serializable;

import org.helianto.core.Category;
import org.helianto.partner.domain.Partner;

/**
 * Classes implementing this interface represent a partner category.
 * 
 * @author mauriciofernandesdecastro
 */
public interface PartnerCategoryForm 

	extends 
	  Serializable
	
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

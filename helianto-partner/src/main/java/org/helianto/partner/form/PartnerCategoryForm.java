package org.helianto.partner.form;

import java.io.Serializable;

import org.helianto.core.domain.Category;
import org.helianto.core.form.CategoryIdForm;
import org.helianto.core.form.ParentForm;
import org.helianto.core.form.PrivateEntityIdForm;
import org.helianto.partner.domain.Partner;
import org.helianto.partner.domain.PrivateEntity;

/**
 * Classes implementing this interface represent a partner category.
 * 
 * @author mauriciofernandesdecastro
 */
public interface PartnerCategoryForm 

	extends 
	  Serializable
	, ParentForm<PrivateEntity>
	, CategoryIdForm
	, PrivateEntityIdForm
	
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

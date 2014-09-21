package org.helianto.partner.form;

import org.helianto.core.form.AddressForm;
import org.helianto.core.form.EntityIdForm;
import org.helianto.core.form.SearchForm;
import org.helianto.partner.PrivateAlias;

/**
 * Private entity form interface.
 * 
 * @author mauriciofernandesdecastro
 */
public interface PrivateEntityForm 

	extends 
	  EntityIdForm
	, AddressForm
	, PrivateAlias 
	, SearchForm

{
	
	/**
	 * Private entity name.
	 */
	String getEntityName();
	
	/**
	 * Partner type.
	 */
	char getPartnerType();
	
	/**
	 * Partner type as R/W property.
	 * 
	 * @param partnerType
	 */
	void setPartnerType(char partnerType);
	
}

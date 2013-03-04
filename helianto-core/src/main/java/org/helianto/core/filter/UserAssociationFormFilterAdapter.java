package org.helianto.core.filter;

import org.helianto.core.filter.base.AssociationFilterAdapter;
import org.helianto.core.form.AssociationForm;

/**
 * User association form filter adapter.
 * 
 * @author mauriciofernandesdecastro
 */
public class UserAssociationFormFilterAdapter 

	extends AssociationFilterAdapter
	
{

	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 * 
	 * @param form
	 */
	public UserAssociationFormFilterAdapter(AssociationForm form) {
		super(form);
	}

}

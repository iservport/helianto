package org.helianto.document.form;

import org.helianto.core.filter.DateForm;
import org.helianto.core.form.EntityIdForm;
import org.helianto.core.form.PriorityForm;
import org.helianto.core.form.SearchForm;

/**
 * Document form.
 * 
 * @author mauriciofernandesdecastro
 */
public interface DocumentForm 
	extends EntityIdForm
	, DateForm
	, SearchForm
	, PriorityForm {

	/**
	 * Document code.
	 */
	public String getDocCode();

	/**
	 * Document name.
	 */
	public String getDocName();

}

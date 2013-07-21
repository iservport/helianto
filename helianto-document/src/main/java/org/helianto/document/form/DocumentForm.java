package org.helianto.document.form;

import org.helianto.core.domain.type.TrunkEntity;
import org.helianto.core.form.PriorityForm;
import org.helianto.core.form.SearchForm;

/**
 * Document form.
 * 
 * @author mauriciofernandesdecastro
 */
public interface DocumentForm 
	extends TrunkEntity
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

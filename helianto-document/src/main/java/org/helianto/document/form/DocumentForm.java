package org.helianto.document.form;

import org.helianto.core.Prioritizable;
import org.helianto.core.domain.type.TrunkEntity;
import org.helianto.core.form.SearchForm;

/**
 * Document form.
 * 
 * @author mauriciofernandesdecastro
 */
public interface DocumentForm 
	extends TrunkEntity
	, SearchForm
	, Prioritizable {

	/**
	 * Document code.
	 */
	public String getDocCode();

	/**
	 * Document name.
	 */
	public String getDocName();

}

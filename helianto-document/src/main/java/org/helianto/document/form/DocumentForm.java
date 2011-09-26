package org.helianto.document.form;

import org.helianto.core.Prioritizable;
import org.helianto.core.Resettable;
import org.helianto.core.TrunkEntity;
import org.helianto.core.filter.form.SearchForm;

/**
 * Document form.
 * 
 * @author mauriciofernandesdecastro
 */
public interface DocumentForm extends TrunkEntity, SearchForm, Prioritizable, Resettable {

	/**
	 * Document code.
	 */
	public String getDocCode();

	/**
	 * Document name.
	 */
	public String getDocName();

}

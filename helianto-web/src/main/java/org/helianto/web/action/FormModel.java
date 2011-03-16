package org.helianto.web.action;

import java.io.Serializable;

/**
 * Interface to form models to be used in selection flows.
 * 
 * @author mauriciofernandesdecastro
 */
public interface FormModel<F> extends Serializable {
	
	/**
	 * Form getter.
	 */
	public F getForm();
	
	/**
	 * Form setter.
	 */
	public void setForm(F form);
	
	/**
	 * Add a search String to be used with the form.
	 */
	public String getSearchString();


}

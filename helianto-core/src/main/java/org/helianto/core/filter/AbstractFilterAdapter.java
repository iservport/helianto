package org.helianto.core.filter;


/**
 * Base class to filters that wrap a form.
 * 
 * @author mauriciofernandesdecastro
 *
 * @param <F>
 */
public abstract class AbstractFilterAdapter<F> extends AbstractFilter {
	
	private static final long serialVersionUID = 1L;
	private F form; 

	/**
	 * Constructor.
	 * 
	 * @param form
	 */
	public AbstractFilterAdapter(F form) {
		this.form = form;
		reset();
	}

	/**
	 * The form.
	 */
	public F getForm() {
		return form;
	}
	
	/**
	 * The filter.
	 * @deprecated use getForm()
	 */
	public F getFilter() {
		return form;
	}
	
	/**
	 * Internal filter setter.
	 * 
	 * @param form
	 */
	protected void setForm(F form) {
		this.form = form;
	}

}

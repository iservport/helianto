package org.helianto.core.form;


/**
 * Country form interface.
 * 
 * @author mauriciofernandesdecastro
 */
public interface CountryForm 
	extends ContextIdForm, SearchForm
{
	/**
	 * Country code.
	 */
	String getCountryCode();

}

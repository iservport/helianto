package org.helianto.core.form;


/**
 * Category form.
 * 
 * @author mauriciofernandesdecastro
 */
public interface CategoryForm 
	extends EntityIdForm
{
	
	/**
	 * Category code.
	 */
	public String getCategoryCode();
	
	/**
	 * Category name.
	 */
	public String getCategoryName();
	
	/**
	 * Category group.
	 */
	public char getCategoryGroup();

}

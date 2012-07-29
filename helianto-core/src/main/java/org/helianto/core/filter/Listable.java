package org.helianto.core.filter;

import java.util.List;

/**
 * Define list behavior.
 *  
 * @author Mauricio Fernandes de Castro
 */
public interface Listable {

	/**
	 * Item list getter.
	 */
	public List<?> getList();

	/**
	 * Item list setter.
	 * 
	 * @param itemList
	 */
	public void setList(List<?> itemList);

	/**
	 * Resulting list size.
	 */
	public int getListSize();
	
	/**
	 * List index getter.
	 */
	public int getIndex();
	
	/**
	 * List index setter.
	 * 
	 * @param index
	 */
	public void setIndex(int index);
	
	/**
	 * Current item.
	 */
	public Object getItem();
	
	/**
	 * Current item setter.
	 */
	public void setItem(Object item);
	
	/**
	 * Increase index by one, if has next, to return the next item.
	 */
	public Object next();
	
	/**
	 * Decrease index by one, if has previous, to return the previous item.
	 */
	public Object previous();
	
	/**
	 * True if getItem() return null.
	 */
	public boolean isClear();
	
}

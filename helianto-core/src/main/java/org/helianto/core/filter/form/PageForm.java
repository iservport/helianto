package org.helianto.core.filter.form;

/**
 * Page form interface.
 * 
 * @author mauriciofernandesdecastro
 */
public interface PageForm {
	
	/**
	 * Page size.
	 */
	public int getMaxRows();
	
	/**
	 * Page size as a R/W property.
	 * 
	 * @param maxRows
	 */
	public void setMaxRows(int maxRows);
	
	/**
	 * First row.
	 */
	public int getFirstRow();

	/**
	 * First row as a R/W property.
	 * 
	 * @param firstRow
	 */
	public void setFirstRow(int firstRow);
}

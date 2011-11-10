package org.helianto.core.filter.form;

/**
 * Class form interface.
 * 
 * @author mauriciofernandesdecastro
 */
public interface ControlForm<C> {
	
	/**
	 * Control.
	 */
	public C getControl();
	
	/**
	 * Control as a R/W propoerty.
	 * 
	 * @param control
	 */
	public void setControl(C control);

	/**
	 * The control filter name.
	 */
	public String getControlName();

	/**
	 * The control filter id field.
	 */
	public long getControlId();
}

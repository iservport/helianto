package org.helianto.core.filter.form;

/**
 * Class form interface.
 * 
 * @author mauriciofernandesdecastro
 */
public interface ClassForm<C> {
	
    /**
     * Class constraint to polimorphic filters.
     */
	public Class<? extends C> getClazz();
	
	/**
	 * Class is a R/W property.
	 * 
	 * @param clazz
	 */
	public void setClazz(Class<? extends C> clazz);

}

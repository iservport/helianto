package org.helianto.core.filter.form;

import java.util.Collection;

/**
 * Exclusion form interface.
 * 
 * @author mauriciofernandesdecastro
 */
public interface ExclusionForm<E> {
	
	/**
     * Collection of <E> to exclude on result set.
     */
    public Collection<E> getExclusions();
    
	/**
	 * Exclusions is a R/W property.
	 * 
	 * @param exclusions
	 */
    public void setExclusions(Collection<E> exclusions);

}

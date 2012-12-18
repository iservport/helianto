package org.helianto.core.form;

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
    
}

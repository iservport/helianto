package org.helianto.document;

import org.helianto.core.number.Sequenceable;

/**
 * Extends <code>Sequenceable</code> to create
 * custom codes for documents.
 * 
 * @author mauriciofernandesdecastro
 */
public interface Customizable extends Sequenceable {
	
	/**
	 * The custom series.
	 */
	public Customizer getSeries();
	
}

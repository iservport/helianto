package org.helianto.core.internal;

import java.io.Serializable;

/**
 * A simple adapter interface to classes exposing key and name.
 * 
 * @author mauriciofernandesdecastro
 */
public interface KeyNameAdapter {

	/**
	 * Key.
	 */
	Serializable getKey();
	
	/**
	 * Name.
	 */
	String getName();
}

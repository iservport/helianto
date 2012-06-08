package org.helianto.document;

/**
 * Extends <code>Customizable</code> to create
 * custom codes for documents.
 * 
 * @author mauriciofernandesdecastro
 */
public interface DocumentCustomizable 

	extends Customizable
	, Documentable 

{
	
	/**
	 * Custom (optional) prefix.
	 */
	String getCustomPrefix();
	
	/**
	 * True if custom prefix is not empty.
	 */
	boolean isCustomPrefixValid();
	
	/**
	 * Custom (optional) suffix.
	 */
	String getSuffix();

}

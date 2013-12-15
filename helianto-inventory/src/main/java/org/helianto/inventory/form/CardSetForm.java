package org.helianto.inventory.form;

import org.helianto.core.filter.InternalNumberForm;

/**
 * CardSet form.
 * 
 * @author mauriciofernandesdecastro
 */
public interface CardSetForm 
	extends InternalNumberForm
{
	
	/**
	 * Process document id.
	 */
	int getProcessDocumentId();

	/**
	 * Process document code.
	 */
	String getProcessDocumentCode();
	
	/**
	 * Card type.
	 */
	char getCardType();
	
}

package org.helianto.core.form;

import org.helianto.core.domain.type.TrunkEntity;

/**
 * Private sequence form interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface PrivateSequenceForm 
	extends TrunkEntity, EntityIdForm, SearchForm 
{
	
	/**
	 * Type name filter.
	 */
	String getTypeName();

}

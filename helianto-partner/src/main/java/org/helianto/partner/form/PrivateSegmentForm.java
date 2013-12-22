package org.helianto.partner.form;

import org.helianto.core.form.EntityIdForm;

/**
 * Private segment form.
 * 
 * @author mauriciofernandesdecastro
 */
public interface PrivateSegmentForm 
	extends EntityIdForm 
{
	
	/**
	 * Segment alias.
	 */
	String getSegmentAlias();

	/**
	 * Segment name.
	 */
	String getSegmentName();
	
	/**
	 * Segment type.
	 */
	char getSegmentType();

}

package org.helianto.partner.form;

import org.helianto.core.domain.type.TrunkEntity;

/**
 * Private segment form.
 * 
 * @author mauriciofernandesdecastro
 */
public interface PrivateSegmentForm extends TrunkEntity {
	
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

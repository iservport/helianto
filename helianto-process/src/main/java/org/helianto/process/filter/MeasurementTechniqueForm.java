package org.helianto.process.filter;

import org.helianto.core.TrunkEntity;

/**
 * Measurement technique filter form interface.
 * 
 * @author mauriciofernandesdecastro
 */
public interface MeasurementTechniqueForm extends TrunkEntity {
	
	/**
	 * Measurement technique code.
	 */
	public String getMeasurementTechniqueCode();
	
	/**
	 * Measurement technique name.
	 */
	public String getMeasurementTechniqueName();

}

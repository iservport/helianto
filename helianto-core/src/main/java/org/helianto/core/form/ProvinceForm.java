package org.helianto.core.form;

import org.helianto.core.domain.Province;
import org.helianto.core.domain.type.RootEntity;
import org.helianto.core.filter.form.SearchForm;
import org.helianto.core.filter.form.TypeForm;

/**
 * Province form.
 * 
 * @author mauriciofernandesdecastro
 */
public interface ProvinceForm 

	extends 
	  RootEntity
	, TypeForm 
	, SearchForm

{
	
	/**
	 * Parent province.
	 */
	Province getParentProvince();
	
	/**
	 * Province code.
	 */
	String getProvinceCode();
	
	/**
	 * State code.
	 */
	String getStateCode();
	
	/**
	 * City code.
	 */
	String getCityCode();
	
	/**
	 * Operator name.
	 */
	String getOperatorName();
	
}

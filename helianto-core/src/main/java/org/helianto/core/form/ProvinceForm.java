package org.helianto.core.form;

import org.helianto.core.domain.Province;
import org.helianto.core.domain.type.RootEntity;

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
	, ContextForm

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
	
}

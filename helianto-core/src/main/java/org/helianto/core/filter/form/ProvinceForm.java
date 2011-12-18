package org.helianto.core.filter.form;

import org.helianto.core.Province;
import org.helianto.core.RootEntity;

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
	
}

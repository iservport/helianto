package org.helianto.core.filter.form;

import org.helianto.core.Province;
import org.helianto.core.Resettable;
import org.helianto.core.RootEntity;

/**
 * Province form.
 * 
 * @author mauriciofernandesdecastro
 */
public interface ProvinceForm extends RootEntity, TypeForm, Resettable {
	
	/**
	 * Parent province.
	 */
	Province getParentProvince();
	
	/**
	 * Province code.
	 */
	String getProvinceCode();
	
	/**
	 * Province name.
	 */
	String getProvinceName();

}

package org.helianto.partner.form;

import org.helianto.core.Entity;
import org.helianto.core.Province;
import org.helianto.core.filter.form.CompositeEntityForm;

/**
 * Composite partner form.
 * 
 * @author mauriciofernandesdecastro
 */
public class CompositePartnerForm

	extends CompositeEntityForm
	
	implements 
	  PrivateEntityForm
	  
{

	private static final long serialVersionUID = 1L;
	private char addressType;
	private String postalCode;
	private Province province;
	private String cityName;
	private char partnerType;
	private String searchString;
	private char searchMode;
	private String searchList;
	
	/**
	 * Entity constructor.
	 * 
	 * @param entity
	 */
	public CompositePartnerForm(Entity entity) {
		setEntity(entity);
	}
	
	public char getAddressType() {
		return addressType;
	}
	public void setAddressType(char addressType) {
		this.addressType = addressType;
	}
	
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	
	public Province getProvince() {
		return province;
	}
	public void setProvince(Province province) {
		this.province = province;
	}
	
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	public char getPartnerType() {
		return partnerType;
	}
	public void setPartnerType(char partnerType) {
		this.partnerType = partnerType;
	}
	
	public String getSearchString() {
		return searchString;
	}
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}
	
	public char getSearchMode() {
		return searchMode;
	}
	public void setSearchMode(char searchMode) {
		this.searchMode = searchMode;
	}
	
	public String getSearchList() {
		return searchList;
	}
	public void setSearchList(String searchList) {
		this.searchList = searchList;
	}
	
}

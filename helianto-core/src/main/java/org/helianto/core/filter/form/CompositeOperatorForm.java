package org.helianto.core.filter.form;

import org.helianto.core.Operator;
import org.helianto.core.Province;

/**
 * Composite operator form.
 * 
 * @author mauriciofernandesdecastro
 *
 */
public class CompositeOperatorForm 

	extends AbstractRootForm 
	
	implements 
	  ProvinceForm
	, PublicAddressForm

{

	private static final long serialVersionUID = 1L;
	private char type;
    private Province province;
    private Province parentProvince;
    private String provinceCode;
    private String stateCode;
    private String cityCode;
	private String postalCode;
    
    /**
     * Operator constructor.
     * 
     * @param operator
     */
    public CompositeOperatorForm(Operator operator) {
    	super();
		setOperator(operator);
	}
    
    public void reset() {
    	setType(' ');
    }

    public char getType() {
		return type;
	}
    public void setType(char type) {
		this.type = type;
	}
    
	public Province getProvince() {
		return province;
	}
	public void setProvince(Province province) {
		this.province = province;
	}
	
	public Province getParentProvince() {
		return parentProvince;
	}
	public void setParentProvince(Province parentProvince) {
		this.parentProvince = parentProvince;
	}
	
    public String getProvinceCode() {
        return this.provinceCode;
    }
    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }
    
    public String getStateCode() {
		return stateCode;
	}
    public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
    
    public String getCityCode() {
		return cityCode;
	}
    public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
    
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	
}

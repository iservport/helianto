package org.helianto.core.form;

import org.helianto.core.domain.Operator;
import org.helianto.core.domain.Province;
import org.helianto.core.filter.form.AbstractRootForm;
import org.helianto.core.filter.form.PublicAddressForm;

/**
 * Composite operator form.
 * 
 * @author mauriciofernandesdecastro
 *
 */
public class CompositeContextForm 

	extends AbstractRootForm 
	
	implements 
	  ProvinceForm
	, PublicAddressForm
	, Cloneable

{

	private static final long serialVersionUID = 1L;
	private char type;
	private String operatorName;
    private Province province;
    private Province parentProvince;
    private String provinceCode;
    private String stateCode;
    private String cityCode;
	private String postalCode;
	private boolean selection = false;
    
    /**
     * Operator constructor.
     * 
     * @param operator
     */
    public CompositeContextForm(Operator operator) {
    	super();
		setOperator(operator);
	}
    
    /**
     * Province constructor.
     * 
     * @param operator
     * @param provinceCode
     */
    public CompositeContextForm(Operator operator, String provinceCode) {
    	this(operator);
    	setProvinceCode(provinceCode);
	}
    
    public char getType() {
		return type;
	}
    public void setType(char type) {
		this.type = type;
	}
    
    public String getOperatorName() {
		return operatorName;
	}
    public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
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
	
	public boolean isSelection() {
		return selection;
	}
	public void setSelection(boolean selection) {
		this.selection = selection;
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
}

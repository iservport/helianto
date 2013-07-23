package org.helianto.core.form;

import org.helianto.core.domain.Operator;
import org.helianto.core.domain.Province;
import org.helianto.core.form.internal.AbstractAddressForm;

/**
 * Composite operator form.
 * 
 * @author mauriciofernandesdecastro
 *
 */
public class CompositeContextForm 
	extends AbstractAddressForm 
	implements 
	  ContextForm
	, PublicAddressForm
	, Cloneable {

	private static final long serialVersionUID = 1L;
	private char type;
	private String contextName;
    private Province province;
    private Province parentProvince;
    private String provinceCode;
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
    
    public String getContextName() {
		return contextName;
	}
    public void setContextName(String contextName) {
		this.contextName = contextName;
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

package org.helianto.core.filter.form;

import org.helianto.core.Operator;
import org.helianto.core.Province;

/**
 * Composite operator form.
 * 
 * @author mauriciofernandesdecastro
 *
 */
public class CompositeOperatorForm extends AbstractRootForm implements ProvinceForm {

	private static final long serialVersionUID = 1L;
	private char type;
    private Province parentProvince;
    private String provinceCode;
    private String provinceName;
    
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
    
    public String getProvinceName() {
        return this.provinceName;
    }
    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }
}

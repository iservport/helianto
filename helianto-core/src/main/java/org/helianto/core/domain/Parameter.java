package org.helianto.core.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;

/**
 * Parameter.
 * 
 * @author mauriciofernandesdecastro
 */
@javax.persistence.Entity
@DiscriminatorValue("P")
public class Parameter 
	extends ParameterGroup 
{

	private static final long serialVersionUID = 1L;
	
    @Column(length=32)
	private String parameterType;
	
    @Column(length=128)
	private String parameterPattern;
	
    /** 
     * Default constructor.
     */
    public Parameter() {
    	super();
    }

    /** 
     * Key constructor.
     * 
     * @param operator
     * @param parameterName
     */
    public Parameter(Operator operator, String parameterName) {
    	this();
    	setOperator(operator);
    	setParameterName(parameterName);
    }
    
    /**
     * Parameter type.
     */
    public String getParameterType() {
		return parameterType;
	}
    public void setParameterType(String parameterType) {
		this.parameterType = parameterType;
	}
    
    /**
     * Parameter pattern.
     */
    public String getParameterPattern() {
		return parameterPattern;
	}
    public void setParameterPattern(String parameterPattern) {
		this.parameterPattern = parameterPattern;
	}
    
    @Override
    public boolean equals(Object other) {
    	if (other instanceof Parameter) return super.equals(other);
    	return false;
    }


}

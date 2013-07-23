package org.helianto.core.form.internal;

import org.helianto.core.domain.Country;
import org.helianto.core.domain.Operator;
import org.helianto.core.domain.State;
import org.helianto.core.form.CityForm;
import org.helianto.core.form.StateForm;

/**
 * Abstract address form.
 * 
 * @author mauriciofernandesdecastro
 */
public class AbstractAddressForm
	extends AbstractSearchForm
	implements StateForm
	, CityForm {

	private static final long serialVersionUID = 1L;
	private Operator operator;
	private Country country;
	private String stateCode;
	private State state;
	private String cityCode;
	private char priority;
	
	public Operator getOperator() {
		return operator;
	}
	public void setOperator(Operator operator) {
		this.operator = operator;
	}
	
	public int getContextId() {
		if (getOperator()!=null) {
			return getOperator().getId();
		}
		return 0;
	}
	
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	
	public int getCountryId() {
		if (getCountry()!=null) {
			return getCountry().getId();
		}
		return 0;
	}
	
	public String getStateCode() {
		return stateCode;
	}
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	
	public int getStateId() {
		if (getState()!=null) {
			return getState().getId();
		}
		return 0;
	}
	
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public char getPriority() {
		return priority;
	}
	public void setPriority(char priority) {
		this.priority = priority;
	}
	
}

package org.helianto.partner.filter.classic;

import java.io.Serializable;

import org.helianto.core.Operator;
import org.helianto.core.Province;
import org.helianto.core.criteria.CriteriaBuilder;
import org.helianto.core.filter.classic.AbstractOperatorBackedCriteriaFilter;

/**
 * Public address database filter.
 * 
 * @author Maurício Fernandes de Castro
 * @deprecated
 */
public class PublicAddressFilter extends AbstractOperatorBackedCriteriaFilter implements Serializable {

	private static final long serialVersionUID = 1L;
	private String postalCode;
	private Province province;
	private String provinceCode;
	private String addressLike;
	
	/**
	 * Operator constructor.
	 */
	public PublicAddressFilter(Operator operator) {
		super(operator);
		setOrderByString("province.provinceCode ASC,address1 ASC ");
		reset();
	}
	
	/**
	 * Selection constructor.
	 * 
	 * @param operator
	 * @param postalCode
	 */
	public PublicAddressFilter(Operator operator, String postalCode) {
		this(operator);
		setPostalCode(postalCode);
	}
	
	/**
	 * Postal code filter.
	 */
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	
	/**
	 * Province filter.
	 */
	public Province getProvince() {
		return province;
	}
	public void setProvince(Province province) {
		this.province = province;
	}
	
	/**
	 * Province code filter.
	 */
	public String getProvinceCode() {
		return provinceCode;
	}
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
	
	/**
	 * Address filter.
	 */
	public String getAddressLike() {
		return addressLike;
	}
	public void setAddressLike(String addressLike) {
		this.addressLike = addressLike;
	}
	
	/**
	 * True (return a single <code>PostalCodeAddressDatabase<code> instance) if
	 * the posta code filter has content.
	 */
	@Override
	public boolean isSelection() {
		return postalCode!=null && postalCode.length()>0;
	}

	@Override
	public void doFilter(CriteriaBuilder mainCriteriaBuilder) {
		if (getProvince()!=null) {
			appendEqualFilter("province.id", getProvince().getId(), mainCriteriaBuilder);
		}
		appendEqualFilter("province.provinceCode", getProvinceCode(), mainCriteriaBuilder);
		appendLikeFilter("address1", getAddressLike(), mainCriteriaBuilder);
	}
	
	@Override
	protected void doSelect(CriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("postalCode", getPostalCode(), mainCriteriaBuilder);
	}

	public void reset() {
		setPostalCode("");
		setProvince(new Province(getOperator(), ""));
		setProvinceCode("");
		setAddressLike("");
	}
	
}

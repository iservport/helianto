package org.helianto.partner;

import java.io.Serializable;

import org.helianto.core.Operator;
import org.helianto.core.Province;
import org.helianto.core.filter.AbstractOperatorBackedCriteriaFilter;
import org.helianto.core.filter.CriteriaBuilder;

/**
 * Public address database filter.
 * 
 * @author Maur�cio Fernandes de Castro
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
	protected void doFilter(CriteriaBuilder mainCriteriaBuilder) {
		if (getProvince()!=null) {
			appendEqualFilter("province.id", getProvince().getId(), mainCriteriaBuilder);
		}
		appendEqualFilter("province.provinceCode", getProvinceCode(), mainCriteriaBuilder);
		appendLikeFilter("address1", getAddressLike(), mainCriteriaBuilder);
	}
	
	@Override
	protected String getOrderByString() {
		return "province.provinceCode ASC,address1 ASC ";
	}

	@Override
	protected void doSelect(CriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("postalCode", getPostalCode(), mainCriteriaBuilder);
	}

	public String getObjectAlias() {
		return "publicaddress";
	}

	public void reset() {
		setPostalCode("");
		setProvince(new Province(getOperator()));
		setProvinceCode("");
		setAddressLike("");
	}
	
}

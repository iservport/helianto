package org.helianto.partner.filter;

import org.helianto.core.Operator;
import org.helianto.core.criteria.CriteriaBuilder;
import org.helianto.core.filter.base.AbstractRootFilterAdapter;
import org.helianto.partner.PublicAddress;

/**
 * Public address adapter database filter.
 * 
 * @author Maurício Fernandes de Castro
 */
public class PublicAddressFilterAdapter extends AbstractRootFilterAdapter<PublicAddress> {

	private static final long serialVersionUID = 1L;
	private String provinceCode;
	private String addressLike;
	
	/**
	 * Default constructor.
	 * 
	 * @param sample
	 */
	public PublicAddressFilterAdapter(PublicAddress sample) {
		super(sample);
		reset();
		setOrderByString("province.provinceCode ASC,address1 ASC ");
	}
	
	/**
	 * Key constructor.
	 * 
	 * @param operator
	 * @param postalCode
	 */
	public PublicAddressFilterAdapter(Operator operator, String postalCode) {
		this(new PublicAddress(operator, postalCode));
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
		return getFilter().getPostalCode()!=null && getFilter().getPostalCode().length()>0;
	}

	@Override
	public void doFilter(CriteriaBuilder mainCriteriaBuilder) {
		if (getFilter().getProvince()!=null) {
			appendEqualFilter("province.id", getFilter().getProvince().getId(), mainCriteriaBuilder);
		}
		appendEqualFilter("province.provinceCode", getProvinceCode(), mainCriteriaBuilder);
		appendLikeFilter("address1", getAddressLike(), mainCriteriaBuilder);
	}
	
	@Override
	protected void doSelect(CriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("postalCode", getFilter().getPostalCode(), mainCriteriaBuilder);
	}

	public void reset() {
		setProvinceCode("");
		setAddressLike("");
	}
	
}
